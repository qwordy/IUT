package edu.sjtu.stap.iut;

import java.io.BufferedReader;
import java.util.List;
import java.util.Set;

/**
 * Created by yfy on 6/3/16.
 * LogParserSelect
 */
public class LogParserSelect implements ITaskAfterRun {

  private Set<String> diffFuncs;

  private List<String> testCases;

  /**
   * Save selected test cases to testCases in function run.
   * @param diffFuncs
   * @param testCases
   */
  public LogParserSelect(Set<String> diffFuncs, List<String> testCases) {
    this.diffFuncs = diffFuncs;
    this.testCases = testCases;
  }

  @Override
  public void run(BufferedReader br) throws Exception {
    String testcase, line, func;
    testcase = null;
    boolean selected = false;
    //int count = 0;
    while ((line = br.readLine()) != null) {
      //System.out.println(line);
      if (line.startsWith("[ RUN      ] ")) {
        testcase = line.substring(13);
        selected = false;
      } else if (line.startsWith("IUTLOG ") && testcase != null && !selected) {
        func = line.substring(7);
        if (diffFuncs.contains(func)) {
          testCases.add(testcase);
          selected = true;
        }
      }
      //count++;
    }
    //System.out.println("Lines: " + count);
  }
}
