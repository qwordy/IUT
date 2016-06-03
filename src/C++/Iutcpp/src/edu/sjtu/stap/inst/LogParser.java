package edu.sjtu.stap.inst;

import java.io.BufferedReader;

/**
 * Created by yfy on 6/3/16.
 * LogParser
 */
public class LogParser implements ITaskAfterRun {
  @Override
  public void run(BufferedReader br) throws Exception {
    String testcase = null, line, func;
    while ((line = br.readLine()) != null) {
      //System.out.println(line);
      if (line.startsWith("[ RUN      ] ")) {
        testcase = line.substring(13);
        //System.out.println(line.substring(13));
      } else if (line.startsWith("IUTLOG ") && testcase != null) {
        func = line.substring(7);
        //System.out.println("  " + func);
      }
    }
  }
}
