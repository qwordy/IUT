package com.weizhaoy.cdtdemo.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.parser.ParserLanguage;

public class Parser {

	List<XFunctionDeclaration> xFunctionDeclarations;
	
	public Parser(String source){
		
				ASTTranslationUnitCore astTranslationUnitCore = new ASTTranslationUnitCore();
				IASTTranslationUnit tu = astTranslationUnitCore.parseCode(source, ParserLanguage.CPP, false, false);
				MyASTVisitor visitor = new MyASTVisitor();
				tu.accept(visitor);
				
				xFunctionDeclarations = new ArrayList<>();
				
				for(IASTFunctionDefinition fun : visitor.getFuncs()){
					xFunctionDeclarations.add(new XFunctionDeclaration(fun));
				}
	}
	
	public List<XFunctionDeclaration> getFunctionDefinitions(){
		return this.xFunctionDeclarations;
	}
}
