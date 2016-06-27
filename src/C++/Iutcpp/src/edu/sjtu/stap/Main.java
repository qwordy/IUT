package edu.sjtu.stap;

import edu.sjtu.stap.config.Argument;
import edu.sjtu.stap.config.Config;
import edu.sjtu.stap.diff.Diff;
import edu.sjtu.stap.iut.Coverage;
import edu.sjtu.stap.iut.Help;
import edu.sjtu.stap.iut.Init;
import edu.sjtu.stap.iut.Inst;

public class Main {
  public static void main(String[] args) {
    long t0 = System.currentTimeMillis();
    try {
      Argument argument = new Argument(args);
      switch (argument.type) {
        case ALL:
          Config.init();
          new Inst();
          Coverage.covSlt(Diff.diff());
          break;
        case INIT:
          Init.init();
          break;
        case COV:
          Config.init();
          //new Inst();
          Coverage.cov();
          break;
        case SELECT:
          Config.init();
          break;
        case HELP:
          Help.help();
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    long t1 = System.currentTimeMillis();
    System.out.printf("Time: %d ms", t1 - t0);
  }
}
