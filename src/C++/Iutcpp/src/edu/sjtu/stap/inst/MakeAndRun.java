package edu.sjtu.stap.inst;

import edu.sjtu.stap.config.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by yfy on 6/2/16.
 * MakeAndRun
 */
public class MakeAndRun {
  public MakeAndRun() {
    execute(Config.make);
    execute(Config.run);
  }

  /**
   * @param cmd
   * @return 0 on success, -1 otherwise
   */
  private int execute(String cmd) {
    try {
      Process process = Runtime.getRuntime().exec(cmd, null, new File(Config.baseDir));
      BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = br.readLine()) != null)
        System.out.println(line);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
    return 0;
  }
}
