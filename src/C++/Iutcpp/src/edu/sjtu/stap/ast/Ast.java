package edu.sjtu.stap.ast;

import edu.sjtu.stap.diff.ast.ASTTranslationUnitCore;
import edu.sjtu.stap.diff.ast.MyASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNamespaceDefinition;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.internal.core.dom.parser.ASTTranslationUnit;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTSimpleDeclaration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by yfy on 5/30/16.
 * Ast
 */
public class Ast {

  private MyASTVisitor myASTVisitor;
  private String fileContent;

  /**
   * Constructor. Do not call it directly! Use AstWarehouse.
   * @param file the file to parse
   */
  public Ast(File file) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(file));
    char[] buf = new char[(int)file.length()];
    br.read(buf);
    fileContent = new String(buf);
    br.close();

    ASTTranslationUnitCore astTranslationUnitCore = new ASTTranslationUnitCore();
    IASTTranslationUnit ast = astTranslationUnitCore.parseAll(file.getAbsolutePath(), fileContent);
    myASTVisitor = new MyASTVisitor();
    ast.accept(myASTVisitor);
  }

  public Ast(String file) throws Exception {
    this(new File(file));
  }

  public String getFileContent() {
    return fileContent;
  }

  /**
   * @return list of function declarations
   */
  public List<IASTFunctionDefinition> getFunctionDecl() throws IOException {


    return myASTVisitor.getFuncs();
  }

  /**
   * @return list of namespace declarations
   */
  public List<ICPPASTNamespaceDefinition> getNamespaceDecl() throws IOException {

    return myASTVisitor.getNamesps();
  }

  /**
   * @return list of class declarations
   */
  public List<CPPASTSimpleDeclaration> getClassDecl() throws IOException {
    //TODO: may need to create a class like "ClassDeclaration" that extends "CPPASTSimpleDeclaration"

    return myASTVisitor.getClasses();
  }
}
