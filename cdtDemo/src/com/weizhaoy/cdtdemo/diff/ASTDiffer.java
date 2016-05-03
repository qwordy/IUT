package com.weizhaoy.cdtdemo.diff;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorFunctionStyleMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;

public class ASTDiffer {

	public String diff(String oldFile, String newFile){
		/**
		*Focus on:
		*CPPASTFunctionDefinition
		*IASTPreprocessorFunctionStyleMacroDefinition
		*...
		**/
		String result = "";
		return result;
	}
	
	private String diff (IASTTranslationUnit oldAST, IASTTranslationUnit newAST){
		
		//get all CPPASTFunctionDefinition
		IASTDeclaration[] oldDecls = oldAST.getDeclarations();
		
		for(IASTDeclaration od : oldDecls){
			if(od instanceof IASTFunctionDefinition){
				IASTFunctionDefinition iastFunctionDefinition = (IASTFunctionDefinition) od;
				String info = "";
			}
			
		}
		//put into map
		//get all IASTPreprocessorFunctionStyleMacroDefinition
		IASTDeclaration[] newDecls = newAST.getDeclarations();
		//put into map
		return "";
	}
}
