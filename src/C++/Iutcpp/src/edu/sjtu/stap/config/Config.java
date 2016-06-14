package edu.sjtu.stap.config;

/**
 * Created by yfy on 5/26/16.
 * Config
 */
public class Config {
  public static String baseDir = "/home/yfy/iut/benchmarks/cctz";

  public static String baseDirInst = baseDir + ".inst";

  public static String baseDirNew = "/home/yfy/iut/benchmarks/cctz2";

//  public static String srcRelativeDir = "/src";
//
//  public static String srcDir = baseDir + srcRelativeDir;
//
//  public static String srcDirInst = baseDirInst + srcRelativeDir;

  //public static String srcDir = "/home/yfy/iut/src/C++/copy of test/";

  public static String make = "make";

  public static String[] tests = {
      "./civil_time_test",
      "./cctz_v1_test",
      "./time_zone_lookup_test"};
}
