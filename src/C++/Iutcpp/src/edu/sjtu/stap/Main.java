package edu.sjtu.stap;

import edu.sjtu.stap.inst.Inst;

public class Main {
  public static void main(String[] args) {
    long t0 = System.currentTimeMillis();
    new Inst();
    long t1 = System.currentTimeMillis();
    System.out.printf("Time: %d ms", t1 - t0);
  }
}
