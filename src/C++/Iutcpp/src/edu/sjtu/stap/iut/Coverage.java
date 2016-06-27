package edu.sjtu.stap.iut;

import edu.sjtu.stap.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yfy on 6/2/16.
 * Get coverage. Select test cases optionally.
 */
public class Coverage {

  /**
   * Coverage, select.
   * @param diffFuncs different functions
   */
  public static void covSlt(Set<String> diffFuncs) {
    System.out.println("Coverage, select");

    Execute.exec(Config.getMake(), Config.getBaseDirInst(), null);
    //Execute.exec(Config.getMakeClean(), Config.getBaseDirNew(), null);
    Execute.exec(Config.getMake(), Config.getBaseDirNew(), null);

    List<String> testCases;
    for (String test : Config.getTestCmd()) {
      testCases = new ArrayList<>();
      Execute.exec(test, Config.getBaseDirInst(),
          new LogParserSelect(diffFuncs, testCases));
      if (testCases.isEmpty())
        continue;
      new Thread(new Rerun(test, testCases)).start();
    }
  }

  /**
   * Write coverage to database.
   */
  public static void cov() throws Exception {
    System.out.println("Coverage");
    Execute.exec(Config.getMake(), Config.getBaseDirInst(), null);
    LogParserCoverage task = new LogParserCoverage();
    for (String test : Config.getTestCmd())
      Execute.exec(test, Config.getBaseDirInst(), task);
    task.close();
  }
}
