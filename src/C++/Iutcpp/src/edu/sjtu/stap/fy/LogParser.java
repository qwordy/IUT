package edu.sjtu.stap.fy;

import java.io.BufferedReader;
import java.util.List;
import java.util.Set;

/**
 * Created by yfy on 6/3/16.
 * LogParser
 */
public class LogParser implements ITaskAfterRun {

  private Set<String> diffFuncs;

  private List<String> testcases;

  public LogParser(Set<String> diffFuncs, List<String> testcases) {
    this.diffFuncs = diffFuncs;
    this.testcases = testcases;
  }

  @Override
  public void run(BufferedReader br) throws Exception {
    String testcase = null, line, func;
    boolean selected = false;
    //int count = 0;
    while ((line = br.readLine()) != null) {
      //System.out.println(line);
      if (line.startsWith("[ RUN      ] ")) {
        testcase = line.substring(13);
        selected = false;
        //System.out.println(line.substring(13));
      } else if (line.startsWith("IUTLOG ") && testcase != null && !selected) {
        func = line.substring(7);
        //System.out.println("  " + func);

        if (diffFuncs.contains(func)) {
          //System.out.println(testcase);
          testcases.add(testcase);
          selected = true;
        }
      }
      //count++;
    }
    //System.out.println("Lines: " + count);
  }
}
