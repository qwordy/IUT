package edu.sjtu.stap.diff;

import edu.sjtu.stap.config.Config;
import edu.sjtu.stap.diff.diff.DifferResult;
import edu.sjtu.stap.diff.diff.FileDiffer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yfy on 6/14/16.
 * Diff
 */
public class Diff {
  /**
   * @return Set of different functions
   * @throws Exception
   */
  public static Set<String> diff() throws Exception {
    System.out.println("Diff");
    FileDiffer differ = new FileDiffer();
    DifferResult result = differ.diff(Config.baseDir, Config.baseDirNew);
    List<String> list = result.getModifiedFunctions();
    //List<String> list = Arrays.asList("civil_time_detail.h: cctz::detail::civil_time::civil_time::day() const");
    Set<String> set = list.stream().collect(Collectors.toSet());
    return set;
  }
}
