package com.yfy;

import java.io.*;

/**
 * Created by yfy on 5/27/16.
 * ThreadInst
 */
public class ThreadInst implements Runnable {
  private File file;

  public ThreadInst(File file) {
    this.file = file;
  }

  @Override
  public void run() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      char[] buf = new char[(int)file.length()];
      br.read(buf);
      br.close();
      //StringBuilder sb = new StringBuilder();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
