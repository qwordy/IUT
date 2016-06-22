package edu.sjtu.stap;

import edu.sjtu.stap.config.Argument;
import edu.sjtu.stap.config.Config;
import edu.sjtu.stap.diff.Diff;
import edu.sjtu.stap.fy.Help;
import edu.sjtu.stap.fy.Init;
import edu.sjtu.stap.fy.Inst;
import edu.sjtu.stap.fy.MakeRunSelectRerun;

public class Main {
  public static void main(String[] args) {
    long t0 = System.currentTimeMillis();
    try {
      Argument argument = new Argument(args);
      switch (argument.type) {
        case ALL:
          Config.init();
          new Inst();
          MakeRunSelectRerun.exec(Diff.diff());
          break;
        case INIT:
          Init.init();
          break;
        case COV:
          Config.init();
          break;
        case SELECT:
          break;
        case HELP:
          Help.help();
          break;
      }

//      Config.init();
//      new Inst();
//      new MakeRunSelectRerun(Diff.diff());
    } catch (Exception e) {
      e.printStackTrace();
    }
    long t1 = System.currentTimeMillis();
    System.out.printf("Time: %d ms", t1 - t0);
  }
}
