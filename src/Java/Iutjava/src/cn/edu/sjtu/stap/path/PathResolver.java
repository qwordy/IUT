package cn.edu.sjtu.stap.path;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import cn.edu.sjtu.stap.Config;
import cn.edu.sjtu.stap.db.Database;
import cn.edu.sjtu.stap.db.entity.Method;

/**
 * Parse the execution path file & update database
 * @author yejiabin
 *
 */

public class PathResolver {

	String project, testCase;
	

	public PathResolver (String project, String fileName) throws Exception {
		this.project = project;
		
		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(
				new FileReader(file.getCanonicalPath()));
		
		String line;
		StringBuilder sb = new StringBuilder();
		
		String testCase = reader.readLine();
		
		if (testCase == null) {
			reader.close();
			throw new Exception("the file is empty!");
		}
		
		List<Method> methods = new ArrayList<Method>();
		//use List to acquire all method
		int lineNo = 2;
		
		while ((line = reader.readLine()) != null) {
			if (line.startsWith(Config.instPrefix)) {
				String path = line.substring(Config.instPrefix.length());
				String [] segs = path.split(Config.instSeperator);
				if (segs.length == 3) {
					methods.add(new Method(segs[0], segs[1], segs[2]));
				} else {
					System.out.println("line " + lineNo + " is broken!");
				}
				lineNo ++;
			}
		}
		
		reader.close();
		// this.testCase = testCase;
		
		try {
			Connection conn = new Database().getConnection();
			//conn.setAutoCommit(false);
			
			Statement stmt = conn.createStatement();
			
			int projectId, testCaseId;			
			// ResultSet rs = stmt.executeQuery("SELECT * FROM Project");
			
			// check if project existence
			PreparedStatement pProjectExistence =
					conn.prepareStatement("SELECT * FROM Project WHERE name = ? LIMIT 1");
			pProjectExistence.setString(1, project);
			ResultSet rsProjectCount = pProjectExistence.executeQuery();
			if (rsProjectCount.next()) {
				projectId = rsProjectCount.getInt(1);
			} else {
				PreparedStatement pCreateProject = 
						conn.prepareStatement("INSERT INTO Project VALUES (DEFAULT, ?, NULL)", 
								Statement.RETURN_GENERATED_KEYS);
				pCreateProject.setString(1, project);
				pCreateProject.executeUpdate();
				ResultSet rs = pCreateProject.getGeneratedKeys();
				rs.next();
				projectId = rs.getInt(1);
			}
			
			// These codes can be optimized
			PreparedStatement pTestCaseExistence =
					conn.prepareStatement("SELECT * FROM TestCase WHERE name = ? LIMIT 1");
			pTestCaseExistence.setString(1, testCase);
			ResultSet rsTestCaseCount = pTestCaseExistence.executeQuery();
			if (rsTestCaseCount.next()) {
				testCaseId = rsTestCaseCount.getInt(1);
			} else {
				PreparedStatement pCreateTestCase = 
						conn.prepareStatement("INSERT INTO TestCase VALUES (DEFAULT, ?, NULL, ?)",
								Statement.RETURN_GENERATED_KEYS);
				pCreateTestCase.setString(1, testCase);
				pCreateTestCase.setInt(2, projectId);
				
				pCreateTestCase.executeUpdate();
				ResultSet rs = pCreateTestCase.getGeneratedKeys();
				rs.next();
				testCaseId = rs.getInt(1);
			}
			
			conn.setAutoCommit(false);
			for (Method m : methods) {
				PreparedStatement ps = conn.prepareStatement(
						"INSERT IGNORE INTO MethodMap VALUES (?, ?)");
				ps.setString(1, m.toString());
				ps.setInt(2, testCaseId);
				ps.executeUpdate();
			}
			conn.commit();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
