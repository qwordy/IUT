package edu.sjtu.stap.iut;

import edu.sjtu.stap.config.Config;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yfy on 5/26/16.
 * Instrumentation
 */
public class Inst {
  private ExecutorService executor;

  public Inst() throws Exception {
    System.out.println("Inst");

    // copy dir
    Execute.exec("rm -rf " + Config.getBaseDirInst(), null, null);
    Execute.exec("cp -rf " + Config.getBaseDir() + " " + Config.getBaseDirInst(), null, null);

    // inst
    executor = Executors.newCachedThreadPool();
    //executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    //executor = Executors.newFixedThreadPool(1);

    // inst regular file
    ls(new File(Config.getBaseDirInst()));

    // inst test file
    //instTestFile();

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

//  private void instTestFile() {
//    for (String file : Config.getTestFile())
//      executor.execute(new ThreadInstTestFile(new File(Config.getBaseDir(), file)));
//  }

  private void ls(File dir) throws Exception {
    File[] files = dir.listFiles();
    if (files == null)
      throw new Exception(dir.toString() + " does not exist");
    for (File file : files) {
      if (file.isDirectory()) {
        ls(file);
      } else {
        if (isCFile(file))
          executor.execute(new ThreadInst(file));
      }
    }
  }

  // c, cc, cpp, h, hpp
  private boolean isCFile(File file) {
    String name = file.getName();
    String suffix = name.substring(name.lastIndexOf('.') + 1);
    return suffix.equals("c") ||
        suffix.equals("cc") ||
        suffix.equals("cpp") ||
        suffix.equals("h") ||
        suffix.equals("hpp");
  }

  private static volatile String functionDefPattern;

  public static String functionDefPattern() {
    if (functionDefPattern == null) {
      synchronized (Config.class) {
        if (functionDefPattern == null) {
          String identifier = "[\\w:\\*&\\.\\[\\]<>]+";
          String funcName = "[\\w\\*&:]+";
          String varDec = String.format("\\s*(const\\s+)?%s\\s+%s\\s*", identifier, identifier);
          String varDecList = String.format("(%s(,\\s*%s)*)?", varDec, varDec);
          functionDefPattern = String.format("(%s\\s+)?%s\\(%s\\)\\s*\\{", identifier, funcName, varDecList);
        }
      }
    }
    return functionDefPattern;
  }
}
