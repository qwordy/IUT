package edu.sjtu.stap.iut;

import edu.sjtu.stap.ast.Ast;

import java.io.File;


/**
 * Created by yfy on 6/29/16.
 * ThreadInstTestFile
 */
public class ThreadInstTestFile extends AbstractThreadInst implements Runnable {

  public ThreadInstTestFile(File file) {
    super(file);
  }

  @Override
  protected String inst(Ast ast) throws Exception {
    return ast.getFileContent();
  }
}
