package edu.sjtu.stap.ast;

import java.io.File;
import java.util.HashMap;

/**
 * Created by yfy on 5/31/16.
 * AstWarehouse
 */
public class AstWarehouse {
  private static HashMap<String, Ast> map = new HashMap<>();

  public static Ast getAst(String file) {
    return map.get(file);
  }

  public static Ast getAst(File file) {
    return getAst(file.getAbsolutePath());
  }
}
