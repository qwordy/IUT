package edu.sjtu.stap;

import edu.sjtu.stap.inst.Inst;
import edu.sjtu.stap.inst.MakeAndRun;

public class Main {
  public static void main(String[] args) {
    long t0 = System.currentTimeMillis();
    //new Inst();
    new MakeAndRun();
    long t1 = System.currentTimeMillis();
    System.out.printf("Time: %d ms", t1 - t0);
  }
}
