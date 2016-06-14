package edu.sjtu.stap.inst;

import edu.sjtu.stap.config.Config;

import java.util.List;

/**
 * Created by yfy on 6/14/16.
 * Rerun
 */
public class Rerun implements Runnable {

  private String test;

  private List<String> testcases;

  private EchoParser echoParser;

  /**
   *
   * @param test
   * @param testcases Not empty
   */
  public Rerun(String test, List<String> testcases) {
    this.test = test;
    this.testcases = testcases;
    echoParser = new EchoParser();
  }

  @Override
  public void run() {
    StringBuilder cmd = new StringBuilder(test + " --gtest_filter=");
    cmd.append(testcases.get(0));
    for (int i = 1; i < testcases.size(); i++)
      cmd.append(':').append(testcases.get(i));
    System.out.println(cmd);
    Execute.exec(cmd.toString(), Config.baseDirNew, echoParser);
  }
}
