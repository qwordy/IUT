package edu.sjtu.stap.iut;

import edu.sjtu.stap.config.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
        "  \"baseVersion\": \"\",\n" +
        "  \"baseDirNew\": \"\",\n" +
        "  \"buildCmd\": \"\",\n" +
        "  \"testCmd\": []\n" +
        "}\n"
    );
    bw.close();
  }
}
