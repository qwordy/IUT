package edu.sjtu.stap;

import edu.sjtu.stap.diff.Diff;
import edu.sjtu.stap.inst.MakeRunSelectRerun;

public class Main {
  public static void main(String[] args) {
    long t0 = System.currentTimeMillis();
    try {
      //new Inst();
      new MakeRunSelectRerun(Diff.diff());
    } catch (Exception e) {
      e.printStackTrace();
    }
    long t1 = System.currentTimeMillis();
    System.out.printf("Time: %d ms", t1 - t0);
  }
}
