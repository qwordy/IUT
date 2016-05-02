package com.weizhaoy.cdtdemo.main;

import java.io.File;

import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.parser.ParserLanguage;

import com.weizhaoy.cdtdemo.ast.ASTTranslationUnitCore;
import com.weizhaoy.cdtdemo.ast.MultiVisitor;
import com.weizhaoy.cdtdemo.ast.MyASTVisitor;


public class Demo {

	public static void main(String[] args) {
		String filePath = "testcode/main.cpp";
		if (args.length != 0)
			filePath = args[0];
		/*
		File cppfile = new File(filePath);
		System.out.println(cppfile.exists());
		*/
		ASTTranslationUnitCore astTranslationUnitCore = new ASTTranslationUnitCore();
		IASTTranslationUnit astTranslationUnit = astTranslationUnitCore.parseFile(filePath, ParserLanguage.CPP, false, false);
		System.out.println(astTranslationUnit.getFilePath());
		
//		MyASTVisitor astVisitor = new MyASTVisitor();
		
		MultiVisitor astVisitor = new MultiVisitor();
//		astVisitor.shouldVisitDeclSpecifiers = true;
//		astVisitor.shouldVisitDeclarators = true;
////		astVisitor.shouldVisitExpressions = true;
//		astVisitor.shouldVisitAttributes = true;
//		astVisitor.shouldVisitImplicitNames = true;
//		astVisitor.shouldVisitInitializers = true;
////		astVisitor.shouldVisitStatements = true;
//		astVisitor.shouldVisitTokens = true;
//		astVisitor.shouldVisitParameterDeclarations = true;
		astVisitor.shouldVisitDeclarations = true;
		astTranslationUnit.accept(astVisitor);
		
		
		
		/*
		for (IASTDeclaration declaration : astVisitor.getDecls()){
			System.out.println("Rawsig: "+declaration.getRawSignature());
		}
		*/

	}

}
