package edu.sjtu.stap.fy;

import edu.sjtu.stap.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yfy on 6/2/16.
 * MakeRunSelectRerun. Make, run, select, rerun.
 */
public class MakeRunSelectRerun {
  public MakeRunSelectRerun(Set<String> diffFuncs) {
    System.out.println("MakeRunSelectRerun");
    Execute.exec(Config.getMake(), Config.getBaseDirInst(), null);
    //Execute.exec(Config.getMakeClean(), Config.getBaseDirNew(), null);
    Execute.exec(Config.getMake(), Config.getBaseDirNew(), null);
    List<String> testcases;
    for (String test : Config.getTestCmd()) {
      testcases = new ArrayList<>();
      Execute.exec(test, Config.getBaseDirInst(), new LogParser(diffFuncs, testcases));
      if (testcases.isEmpty())
        continue;
      new Thread(new Rerun(test, testcases)).start();
    }
  }
}
