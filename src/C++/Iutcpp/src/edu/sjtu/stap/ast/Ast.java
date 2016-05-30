package edu.sjtu.stap.ast;

import java.io.File;
import java.util.List;

/**
 * Created by yfy on 5/30/16.
 * Ast
 */
public class Ast {
  int ast;
  
  /**
   * Constructor
   * @param file the file to parse
   */
  public Ast(File file) {
  }

  /**
   * @return list of function declarations
   */
  public List<Integer> getFunctionDecl() {
    return null;
  }

  /**
   * @return list of namespace declarations
   */
  public List<Integer> getNamespaceDecl() {
    return null;
  }

  /**
   * @return list of class declarations
   */
  public List<Integer> getClassDecl() {
    return null;
  }
}
