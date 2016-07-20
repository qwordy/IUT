package edu.sjtu.stap.diff;

import edu.sjtu.stap.config.Config;
import edu.sjtu.stap.diff.diff.FileDiffer;
import edu.sjtu.stap.diff.diff.IDifferResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yfy on 6/14/16.
 * Diff
 */
public class Diff {
  /**
   * @return Set of different functions. Or null means choosing all.
   * @throws Exception
   */
  public static Set<String> diff() throws Exception {
    System.out.println("Diff");
    FileDiffer differ = new FileDiffer();
    IDifferResult result = differ.diff(Config.getBaseDir(), Config.getBaseDirNew());
    if (result.ifChooseAll())
      return null;
    List<String> list = result.getModifiedFunctions();
    //List<String> list = Arrays.asList("civil_time_detail.h: cctz::detail::civil_time::civil_time::day() const");
    Set<String> set = new HashSet<>();
    set.addAll(list);
    return set;
  }
}
