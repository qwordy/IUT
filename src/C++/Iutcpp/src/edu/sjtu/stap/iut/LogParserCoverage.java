package edu.sjtu.stap.iut;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by yfy on 6/22/16.
 * LogParserCoverage
 */
public class LogParserCoverage implements ITaskAfterRun {

  private Connection conn;

  public LogParserCoverage() throws Exception {
    conn = DriverManager.getConnection("jdbc:sqlite:coverage.db");
    Statement stmt = conn.createStatement();
    String sql = "";
    stmt.executeUpdate(sql);
    stmt.close();
  }

  @Override
  public void run(BufferedReader br) throws Exception {
    String testcase, line, func;
    testcase = null;
    while ((line = br.readLine()) != null) {
      if (line.startsWith("[ RUN      ] ")) {
        testcase = line.substring(13);
      } else if (line.startsWith("IUTLOG ") && testcase != null) {
        func = line.substring(7);
      }
    }
  }
}
