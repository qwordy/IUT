package edu.sjtu.stap.iut;

import edu.sjtu.stap.ast.Ast;
import edu.sjtu.stap.ast.AstWarehouse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by yfy on 6/29/16.
 * AbstractThreadInst
 */
public abstract class AbstractThreadInst implements Runnable {

  private File file;

  public AbstractThreadInst() {}

  public AbstractThreadInst(File file) {
    this.file = file;
  }

  @Override
  public void run() {
    try {
      Ast ast = AstWarehouse.getAst(file);
      String instedBuf = inst(ast);

      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write(instedBuf);
      bw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected abstract String inst(Ast ast) throws Exception;
}
