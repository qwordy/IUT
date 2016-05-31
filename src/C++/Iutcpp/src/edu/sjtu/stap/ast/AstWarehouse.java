package edu.sjtu.stap.ast;

import java.io.File;
import java.util.HashMap;

/**
 * Created by yfy on 5/31/16.
 * AstWarehouse
 */
public class AstWarehouse {
  private static HashMap<String, Ast> map = new HashMap<>();

  public static Ast getAst(String file) throws Exception {
    Ast ast = map.get(file);
    if (ast == null) {
      ast = new Ast(file);
      map.put(file, ast);
    }
    return ast;
  }

  public static Ast getAst(File file) throws Exception {
    return getAst(file.getAbsolutePath());
  }
}
