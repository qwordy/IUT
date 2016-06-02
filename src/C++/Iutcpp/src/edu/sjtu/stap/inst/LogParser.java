package edu.sjtu.stap.inst;

import java.io.BufferedReader;

/**
 * Created by yfy on 6/3/16.
 * LogParser
 */
public class LogParser implements ITaskAfterRun {
  @Override
  public void run(BufferedReader br) throws Exception {
    String line;
    while ((line = br.readLine()) != null)
      System.out.println(line);
  }
}
