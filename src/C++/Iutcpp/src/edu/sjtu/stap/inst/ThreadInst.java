package edu.sjtu.stap.inst;

import javafx.util.Pair;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yfy on 5/27/16.
 * ThreadInst
 */
public class ThreadInst implements Runnable {
  private File file;

  public ThreadInst(File file) {
    //System.out.println(file);
    this.file = file;
  }

  @Override
  public void run() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      char[] buf = new char[(int)file.length()];
      br.read(buf);
      br.close();

      String instedBuf = inst(new String(buf));

      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write(instedBuf);
      bw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String inst(String buf) {
    Pattern pattern = Pattern.compile(Config.functionDefPattern());
    //String test = "int a(const bool a){};";
    Matcher matcher = pattern.matcher(buf);
    ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      list.add(new Pair<>(start, end));
      //System.out.printf("%s\n(%d, %d): %s\n", file, start, end, buf.substring(start, end));
    }

    StringBuilder sb = new StringBuilder(buf);
    sb.insert(0, "#include <stdio.h>\n");
    int offset = 19;

    String log = "puts(\"heihei\");";
    for (Pair<Integer, Integer> pair : list) {
      sb.insert(pair.getValue() + offset, log);
      offset += log.length();
    }

    return sb.toString();
  }
}
