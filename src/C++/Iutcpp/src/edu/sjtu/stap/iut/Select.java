package edu.sjtu.stap.iut;

import edu.sjtu.stap.config.Config;
import org.eclipse.cdt.utils.coff.Exe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

/**
 * Created by yfy on 6/22/16.
 * Select test cases.
 */
public class Select {

  private static Connection conn;

  private static boolean checkVersion() throws Exception {
    Statement stmt = conn.createStatement();
    String sql = "select * from version";
    ResultSet rs = stmt.executeQuery(sql);
    rs.next();
    String version = rs.getString("version");
    return version.equals(Config.getBaseVersion());
  }

  public static void select(Set<String> diffFuncs) throws Exception {
    String dbFile = Config.getBaseDirInst() + File.separatorChar + "coverage.db";
    conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

    if (!checkVersion())
      throw new Exception("Project version unmatch");

    String runFileStr = Config.getBaseDirNew() + File.separatorChar + "run.sh";
    File runFile = new File(runFileStr);
    runFile.setExecutable(true);
    BufferedWriter bw = new BufferedWriter(new FileWriter(runFile));

    System.out.println("Selecting test cases");

    Statement stmt = conn.createStatement();
    Statement stmt2 = conn.createStatement();
    String sql = "select test, testCase from cov group by test, testCase;";
    ResultSet rs = stmt.executeQuery(sql);
    String lastTest = "";
    StringBuilder cmd = new StringBuilder();
    while (rs.next()) {
      String test = rs.getString("test");
      String testCase = rs.getString("testCase");
      //System.out.println(test + ' ' + testCase);

      if (!test.equals(lastTest)) {
        bw.write(cmd.toString() + '\n');
        lastTest = test;
        cmd = new StringBuilder(test + " --gtest_filter=");
      }

      sql = "select func from cov where test = \"" +
          test + "\" and testCase = \"" + testCase + "\";";
      ResultSet rs2 = stmt2.executeQuery(sql);
      while (rs2.next()) {
        String func = rs2.getString("func");
        //System.out.println("  " + func);
        if (diffFuncs == null || diffFuncs.contains(func)) {
          cmd.append(':').append(testCase);
          //System.out.println(test + '|' + testCase);
          break;
        }
      }
    }
    bw.write(cmd.toString() + '\n');
    bw.close();
    System.out.println("Done\nRunning run.sh");
    Execute.exec("./run.sh", Config.getBaseDirNew(), new EchoParser());
  }
}
