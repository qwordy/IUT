package com.yfy;

/**
 * Created by yfy on 5/26/16.
 * Config
 */
public class Config {
  public static String srcdir = "/home/yfy/iut/benchmarks/cctz/src/";
//  public static String srcdir = "/home/yfy/iut/src/C++/test2/";

  private static String functionDef;

  public static String functionDef() {
    if (functionDef == null) {
      synchronized (Config.class) {
        if (functionDef == null) {
          String identifier = "[\\w:\\*&\\.\\[\\]<>]+";
          String funcName = "[\\w\\*&:]+";
          String varDec = String.format("\\s*(const\\s+)?%s\\s+%s\\s*", identifier, identifier);
          String varDecList = String.format("(%s(,\\s*%s)*)?", varDec, varDec);
          functionDef = String.format("(%s\\s+)?%s\\(%s\\)\\s*\\{", identifier, funcName, varDecList);
        }
      }
    }
    return functionDef;
  }
}

