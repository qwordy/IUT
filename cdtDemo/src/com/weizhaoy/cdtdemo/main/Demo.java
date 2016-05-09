package com.weizhaoy.cdtdemo.main;

import java.io.File;

import org.eclipse.cdt.core.dom.ast.ExpansionOverlapsBoundaryException;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionStyleMacroParameter;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorFunctionStyleMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorMacroExpansion;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IMacroBinding;
import org.eclipse.cdt.core.parser.ParserLanguage;

import com.weizhaoy.cdtdemo.ast.ASTTranslationUnitCore;
import com.weizhaoy.cdtdemo.ast.MultiVisitor;
import com.weizhaoy.cdtdemo.ast.MyASTVisitor;
import com.weizhaoy.cdtdemo.diff.ASTDiffer;


public class Demo {

	static int flag = 2;
	public static void main(String[] args) {
		if(flag == 2){
			String oldPath = "testcode/main.cpp";
			String newPath="testcode/mainNew.cpp";
			ASTDiffer astDiffer = new ASTDiffer();
			String result =  astDiffer.diff(oldPath, newPath);
			System.out.println(result);
		}
		if(flag == 1){
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
			
//			MyASTVisitor astVisitor = new MyASTVisitor();
			
			MultiVisitor astVisitor = new MultiVisitor();
//			astVisitor.shouldVisitDeclSpecifiers = true;
//			astVisitor.shouldVisitDeclarators = true;
////			astVisitor.shouldVisitExpressions = true;
//			astVisitor.shouldVisitAttributes = true;
//			astVisitor.shouldVisitImplicitNames = true;
//			astVisitor.shouldVisitInitializers = true;
////			astVisitor.shouldVisitStatements = true;
//			astVisitor.shouldVisitTokens = true;
//			astVisitor.shouldVisitParameterDeclarations = true;
			astVisitor.shouldVisitDeclarations = true;
			astTranslationUnit.accept(astVisitor);
			
			IASTPreprocessorMacroDefinition[] macroDefinitions = astTranslationUnit.getMacroDefinitions();
			for (IASTPreprocessorMacroDefinition macroDefinition : macroDefinitions){
//				System.out.println(macroDefinition);
				if(macroDefinition instanceof IASTPreprocessorFunctionStyleMacroDefinition){
					IASTPreprocessorFunctionStyleMacroDefinition fucker = (IASTPreprocessorFunctionStyleMacroDefinition) macroDefinition;
					IASTFunctionStyleMacroParameter[] macroParameters = fucker.getParameters();
					for(IASTFunctionStyleMacroParameter mp : macroParameters){
//						System.out.println("macroprp: "+mp.getParameter());
					}
				}
//				System.out.println(macroDefinition.getName() );			
//				System.out.println(macroDefinition.getExpansion());
			}
			
		
			IASTPreprocessorMacroExpansion[] macroExpansions = astTranslationUnit.getMacroExpansions();
			for(IASTPreprocessorMacroExpansion macroExpansion : macroExpansions){
//				System.out.println(macroExpansion.getRawSignature());
//				System.out.println(macroExpansion.getMacroReference());
//				System.out.println(macroExpansion.getMacroDefinition());
			}
			/*
			IASTPreprocessorStatement[] preprocessorStatements = astTranslationUnit.getAllPreprocessorStatements();
			for (IASTPreprocessorStatement ps : preprocessorStatements){
				try {
					System.out.println(ps.getSyntax().getNext().getCharImage());
				} catch (ExpansionOverlapsBoundaryException e) {
					
					e.printStackTrace();
				}
			
			}
			*/
			
			
			
			
			/*
			for (IASTDeclaration declaration : astVisitor.getDecls()){
				System.out.println("Rawsig: "+declaration.getRawSignature());
			}
			*/
		}
		

	}

	
}