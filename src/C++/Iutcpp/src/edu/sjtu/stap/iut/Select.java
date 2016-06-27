package edu.sjtu.stap.iut;

import edu.sjtu.stap.config.Config;

import java.io.File;
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
  public static void select(Set<String> diffFuncs) throws Exception {
    String dbFile = Config.getBaseDirInst() + File.separatorChar + "coverage.db";
    Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
    Statement stmt = conn.createStatement();
    Statement stmt2 = conn.createStatement();
    String sql = "select test, testCase from cov group by test, testCase;";
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      String test = rs.getString("test");
      String testCase = rs.getString("testCase");
      System.out.println(test + ' ' + testCase);

      sql = "select func from cov where test = \"" +
          test + "\" and testCase = \"" + testCase + "\";";
      ResultSet rs2 = stmt2.executeQuery(sql);
      while (rs2.next()) {
        String func = rs2.getString("func");
        System.out.println("  " + func);
      }

    }
  }
}
