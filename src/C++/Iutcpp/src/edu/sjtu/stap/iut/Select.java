package edu.sjtu.stap.iut;

import edu.sjtu.stap.config.Config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;

/**
 * Created by yfy on 6/22/16.
 * Select test cases.
 */
public class Select {
  public static void select(Set<String> diffFuncs) throws Exception {
    String dbFile = Config.getBaseDirInst() + File.separatorChar + "coverage.db";
    Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
  }
}
