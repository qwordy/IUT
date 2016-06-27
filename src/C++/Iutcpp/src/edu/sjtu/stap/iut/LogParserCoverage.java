package edu.sjtu.stap.iut;

import java.io.BufferedReader;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yfy on 6/22/16.
 * LogParserCoverage.
 * Analyse log, get coverage, write db.
 */
public class LogParserCoverage implements ITaskAfterRun {

  private Connection conn;

  private Statement stmt;

  private String test;

  public LogParserCoverage() throws Exception {
    new File("coverage.db").delete();

    conn = DriverManager.getConnection("jdbc:sqlite:coverage.db");
    conn.setAutoCommit(false);

    stmt = conn.createStatement();
    String sql = "create table cov (test text, testCase text, func text);";
    stmt.executeUpdate(sql);
//    sql = "insert into cov values(\"t0\", \"t1\", \"f1\");";
//    stmt.executeUpdate(sql);
  }

  @Override
  public void run(BufferedReader br) throws Exception {
    String testCase, line, func, sql;
    testCase = null;
    Set<String> funcs = new HashSet<>();
    while ((line = br.readLine()) != null) {
      if (line.startsWith("[ RUN      ] ")) {
        testCase = line.substring(13);
        funcs.clear();
      } else if (line.startsWith("IUTLOG ") && testCase != null) {
        func = line.substring(7);
        if (!funcs.contains(func)) {
          funcs.add(func);
          sql = String.format("insert into cov values(\"%s\", \"%s\", \"%s\")",
              test, testCase, func);
          stmt.executeUpdate(sql);
        }
      }
    }
  }

  public void setTest(String test) {
    this.test = test;
  }

  public void close() throws Exception {
    stmt.close();
    conn.commit();
    conn.close();
  }
}
