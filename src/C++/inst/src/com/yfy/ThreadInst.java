package com.yfy;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yfy on 5/27/16.
 * ThreadInst
 */
public class ThreadInst implements Runnable {
  private File file;

  public ThreadInst(File file) {
    //System.out.println(file);
    this.file = file;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(1000);
      BufferedReader br = new BufferedReader(new FileReader(file));
      char[] buf = new char[(int)file.length()];
      br.read(buf);
      br.close();
      inst(new String(buf));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void inst(String buf) {
//    String identifier = "[\\w:\\*&\\.\\[\\]<>]+";
//    String funcName = "[\\w\\*&:]+";
//    String varDec = String.format("\\s*(const\\s+)?%s\\s+%s\\s*", identifier, identifier);
//    String varDecList = String.format("(%s(,\\s*%s)*)?", varDec, varDec);
//    String functionDef = String.format("(%s\\s+)?%s\\(%s\\)\\s*\\{", identifier, funcName, varDecList);
    //System.out.println(functionDef);

    Pattern pattern = Pattern.compile(Config.functionDef());
    String test = "int a(const bool a){};";
    Matcher matcher = pattern.matcher(buf);
    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      //System.out.printf("%s\n(%d, %d): %s\n", file, start, end, buf.substring(start, end));
    }
  }
}
