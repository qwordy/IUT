package edu.sjtu.stap.iut;

import java.io.BufferedReader;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by yfy on 6/22/16.
 * LogParserCoverage.
 * Analyse log, get coverage, write db.
 */
public class LogParserCoverage implements ITaskAfterRun {

  private Connection conn;

  private Statement stmt;

  public LogParserCoverage() throws Exception {
    new File("coverage.db").delete();

    conn = DriverManager.getConnection("jdbc:sqlite:coverage.db");
    conn.setAutoCommit(false);

    stmt = conn.createStatement();
    String sql = "create table cov (t text, f text);";
    stmt.executeUpdate(sql);
    sql = "insert into cov values(\"t1\", \"f1\");";
    stmt.executeUpdate(sql);
  }

  @Override
  public void run(BufferedReader br) throws Exception {
    String testCase, line, func, sql;
    testCase = null;
    while ((line = br.readLine()) != null) {
      if (line.startsWith("[ RUN      ] ")) {
        testCase = line.substring(13);
      } else if (line.startsWith("IUTLOG ") && testCase != null) {
        func = line.substring(7);
        sql = "insert into cov values(\"" + testCase + "\", " + "\"" + func + "\");";
        stmt.executeUpdate(sql);
      }
    }
  }

  public void close() throws Exception {
    stmt.close();
    conn.commit();
    conn.close();
  }
}
