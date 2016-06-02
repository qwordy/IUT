package edu.sjtu.stap.inst;

import edu.sjtu.stap.config.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by yfy on 6/2/16.
 * MakeAndRun
 */
public class MakeAndRun {
  public MakeAndRun() {
    execute(Config.make, null);
    execute(Config.run, new LogParser());
  }

  /**
   * @param cmd command
   * @return 0 on success, -1 otherwise
   */
  private int execute(String cmd, ITaskAfterRun task) {
    try {
      Process process = Runtime.getRuntime().exec(cmd, null, new File(Config.baseDir));
      if (task != null) {
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        task.run(br);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
    return 0;
  }
}
