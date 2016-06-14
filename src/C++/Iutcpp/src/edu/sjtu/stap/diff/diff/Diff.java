package edu.sjtu.stap.diff.diff;

import edu.sjtu.stap.config.Config;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yfy on 6/14/16.
 * Diff
 */
public class Diff {
  public static Set<String> set;

  public static void diff() throws Exception {
//    FileDiffer differ = new FileDiffer();
//    DifferResult result = differ.diff(Config.baseDir, Config.baseDirNew);
//    list = result.getModifiedFunctions();
    List<String> list = Arrays.asList("civil_time_detail.h: cctz::detail::civil_time::civil_time::day() const");
    set = list.stream().collect(Collectors.toSet());
  }
}
