package edu.sjtu.stap.iut;

import java.io.BufferedReader;

/**
 * Created by yfy on 6/22/16.
 * LogParserCoverage
 */
public class LogParserCoverage implements ITaskAfterRun {
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
