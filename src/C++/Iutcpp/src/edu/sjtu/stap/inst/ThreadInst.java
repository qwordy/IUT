package edu.sjtu.stap.inst;

import edu.sjtu.stap.ast.Ast;
import edu.sjtu.stap.ast.AstWarehouse;
import javafx.util.Pair;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IScope;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNamespaceDefinition;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTFunctionDefinition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
    System.out.println(file);
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

  private String inst(Ast ast) throws Exception {
    String buf = ast.getFileContent();
    StringBuilder sb = new StringBuilder(ast.getFileContent());
    sb.insert(0, "#include <stdio.h>\n");
    int offset = 19;

    List<String> scopeList = new ArrayList<>();
    List<IASTFunctionDefinition> funcList = ast.getFunctionDecl();
    for (IASTFunctionDefinition func : funcList) {
      // file
      IASTFileLocation fileLocation = func.getFileLocation();
      String fileName = fileLocation.getFileName();
      int nodeOffset = fileLocation.getNodeOffset();
      int brOffset = buf.indexOf('{', nodeOffset);

      // scope
      IASTNode node = func.getParent();
      while (node != null) {
        IToken token = node.getSyntax();
        System.out.println(token);
        if (token.getImage().equals("class")) {
          System.out.println(token.getNext());
        } else if (token.getImage().equals("namespace")) {

        }

        node = node.getParent();
      }

      /*scopeList.clear();
      IScope scope = func.getScope();
      System.out.println(scope);
      System.out.println(scope.getParent());
      while (scope != null) {
        System.out.println(scope);
        //scopeList.add(scope.getScopeName().toString());
        scope = scope.getParent();
      }*/

      // signature
      String decl = func.getDeclarator().getRawSignature();

      String log = String.format("puts(\"%s: %s\");", fileName, decl);
      sb.insert(brOffset + 1 + offset, log);
      offset += log.length();
    }

    return sb.toString();
  }

  private String inst2(String buf) {
    Pattern pattern = Pattern.compile(Inst.functionDefPattern());
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
