package edu.sjtu.stap.inst;

import edu.sjtu.stap.config.Config;

/**
 * Created by yfy on 6/2/16.
 * MakeRunSelect
 */
public class MakeRunSelect {
  public MakeRunSelect() {
    Execute.exec(Config.make, Config.baseDirInst, null);
    Execute.exec(Config.run, Config.baseDirInst, new LogParser());
  }
}
