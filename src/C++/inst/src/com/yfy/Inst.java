package com.yfy;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yfy on 5/26/16.
 * Inst
 */
public class Inst {
  private ExecutorService executor;

  public Inst() {
    executor = Executors.newCachedThreadPool();
    //executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    //executor = Executors.newFixedThreadPool(1);
    ls(new File(Config.srcdir));
    executor.shutdown();
    try {
      executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void ls(File dir) {
    File[] files = dir.listFiles();
    if (files == null) return;
    for (File file : files) {
      if (file.isDirectory()) {
        ls(file);
      } else {
        if (isCFile(file)) inst(file);
      }
    }
  }

  private void inst(File file) {
    executor.execute(new ThreadInst(file));
  }

  // c, cc, cpp, h
  private boolean isCFile(File file) {
    String name = file.getName();
    String suffix = name.substring(name.lastIndexOf('.') + 1);
    return suffix.equals("c") ||
        suffix.equals("cc") ||
        suffix.equals("cpp") ||
        suffix.equals("h");
  }
}
