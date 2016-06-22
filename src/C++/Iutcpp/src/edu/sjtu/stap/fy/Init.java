package edu.sjtu.stap.fy;

import edu.sjtu.stap.config.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

/**
 * Created by yfy on 6/22/16.
 * Initialization
 */
public class Init {
  public static void init() throws Exception {
    BufferedWriter bw = new BufferedWriter(new FileWriter(Config.configFile));
    //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(
        "{\n" +
        "  \"baseDir\": \"\",\n" +
        "  \"baseDirNew\": \"\",\n" +
        "  \"make\": \"\",\n" +
        "  \"testCmd\": []\n" +
        "}\n"
    );
    bw.close();
  }
}
