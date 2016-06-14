package edu.sjtu.stap.inst;

import java.io.BufferedReader;

/**
 * Created by yfy on 6/15/16.
 * EchoParser
 */
public class EchoParser implements ITaskAfterRun {
  @Override
  public void run(BufferedReader br) throws Exception {
    String line;
    while ((line = br.readLine()) != null)
      System.out.println(line);
  }
}
