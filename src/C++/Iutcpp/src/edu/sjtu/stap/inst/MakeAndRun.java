package edu.sjtu.stap.inst;

import edu.sjtu.stap.config.Config;

/**
 * Created by yfy on 6/2/16.
 * MakeAndRun
 */
public class MakeAndRun {
  public MakeAndRun() {
    Execute.exec(Config.make, Config.baseDirInst, null);
    Execute.exec(Config.run, Config.baseDirInst, new LogParser());
  }
}
