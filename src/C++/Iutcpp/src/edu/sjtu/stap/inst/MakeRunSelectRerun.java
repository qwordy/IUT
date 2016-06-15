package edu.sjtu.stap.inst;

import edu.sjtu.stap.config.Config;

import java.util.*;

/**
 * Created by yfy on 6/2/16.
 * MakeRunSelectRerun. Make, run, select, rerun.
 */
public class MakeRunSelectRerun {
  public MakeRunSelectRerun(Set<String> diffFuncs) {
    System.out.println("MakeRunSelectRerun");
    Execute.exec(Config.make, Config.baseDirInst, null);
    Execute.exec(Config.makeClean, Config.baseDirNew, null);
    Execute.exec(Config.make, Config.baseDirNew, null);
    List<String> testcases;
    for (String test : Config.tests) {
      testcases = new ArrayList<>();
      Execute.exec(test, Config.baseDirInst, new LogParser(diffFuncs, testcases));
      if (testcases.isEmpty())
        continue;
      new Thread(new Rerun(test, testcases)).start();
    }
  }
}
