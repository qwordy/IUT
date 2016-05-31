package edu.sjtu.stap.ast;

import edu.sjtu.stap.diff.ast.ASTTranslationUnitCore;
import edu.sjtu.stap.diff.ast.MyASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNamespaceDefinition;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.internal.core.dom.parser.ASTTranslationUnit;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTSimpleDeclaration;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by yfy on 5/30/16.
 * Ast
 */
public class Ast {

  IASTTranslationUnit ast;
  File file;
  MyASTVisitor myASTVisitor;

  /**
   * Constructor
   * @param file the file to parse
   */
  public Ast(File file) throws IOException {
    this.file = file;
    ASTTranslationUnitCore astTranslationUnitCore = new ASTTranslationUnitCore();
    ast = astTranslationUnitCore.parseFile(file.getCanonicalPath(),ParserLanguage.CPP, false, false);
    myASTVisitor = new MyASTVisitor();
    ast.accept(myASTVisitor);
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
