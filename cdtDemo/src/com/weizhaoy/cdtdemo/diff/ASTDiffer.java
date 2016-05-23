package com.weizhaoy.cdtdemo.diff;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorFunctionStyleMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.parser.ParserLanguage;

import com.weizhaoy.cdtdemo.ast.ASTTranslationUnitCore;

public class ASTDiffer {

	List<String> funcAdded;
	List<String> funcModified;
	List<String> funcDeleted;
//	private boolean isModified = false;
	String diffResult = "";
	
	public String getDiffResult() {
		return diffResult;
	}

	public ASTDiffer(){
		//TODO: keep it or cut it?
	}

	public ASTDiffer(IASTTranslationUnit oldAST, IASTTranslationUnit newAST){//Diff AST
		
		diff(oldAST, newAST);

	}

	public ASTDiffer(String oldPath, String newPath, boolean isCode){//Diff FilePath
		diff(oldPath, newPath, isCode);
	}

	public ASTDiffer(File oldFile, File newFile)  {
		try {
			diff(oldFile.getCanonicalPath(), newFile.getCanonicalPath(), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isModified(){
		return funcAdded.size()+funcModified.size()+funcDeleted.size() != 0;
	}
	
	
	public List<String> getFuncAdded() {
		return funcAdded;
	}



	public List<String> getFuncModified() {
		return funcModified;
	}



	public List<String> getFuncDeleted() {
		return funcDeleted;
	}



	
	
	public String diff(String oldPath, String newPath, boolean isCode){
		/**
		 *Focus on:
		 *CPPASTFunctionDefinition
		 *IASTPreprocessorFunctionStyleMacroDefinition
		 *...
		 **/
		

		ASTTranslationUnitCore astTranslationUnitCore = new ASTTranslationUnitCore();
		IASTTranslationUnit oldAST;
		IASTTranslationUnit newAST;
		if(!isCode){
			 oldAST = astTranslationUnitCore.parseFile(oldPath, ParserLanguage.CPP, false, false);
			 newAST = astTranslationUnitCore.parseFile(newPath, ParserLanguage.CPP, false, false);
		}else{
			 oldAST = astTranslationUnitCore.parseCode(oldPath, ParserLanguage.CPP, false, false);
			 newAST = astTranslationUnitCore.parseCode(newPath, ParserLanguage.CPP, false, false);
		}
		

		
		return diff(oldAST, newAST);
	}

	/**
	 * Core diff method
	 * @param oldAST
	 * @param newAST
	 * @return
	 */
	public String diff (IASTTranslationUnit oldAST, IASTTranslationUnit newAST){
		
		funcAdded = new ArrayList<>();
		funcModified = new ArrayList<>();
		funcDeleted = new ArrayList<>();
		String result = "AST Diff Result: "+" \n";//TODO: StringBuilder
		HashMap<String, IASTFunctionDefinition> newFuncDefsMap = new HashMap<>();

		//get all new
		IASTDeclaration[] newDecls = newAST.getDeclarations();
		//put into map
		for(IASTDeclaration nw : newDecls){
			if(nw instanceof IASTFunctionDefinition){
				IASTFunctionDefinition iastFunctionDefinition = (IASTFunctionDefinition) nw;
				newFuncDefsMap.put(DiffUtils.getFunctionId(iastFunctionDefinition), iastFunctionDefinition);
			}
		}


		//get all old
		IASTDeclaration[] oldDecls = oldAST.getDeclarations();

		for(IASTDeclaration od : oldDecls){
			if(od instanceof IASTFunctionDefinition){
				IASTFunctionDefinition funcInOld = (IASTFunctionDefinition) od;
				IASTFunctionDefinition funcInNew = newFuncDefsMap.get(DiffUtils.getFunctionId(funcInOld));
				if(funcInNew != null){
					if(!funcInNew.getBody().getRawSignature().equals(funcInOld.getBody().getRawSignature()) ){ //modified
						funcModified.add(DiffUtils.getFunctionId(funcInOld));
						
					}
					newFuncDefsMap.remove(DiffUtils.getFunctionId(funcInNew));
				}else{//deleted
					funcDeleted.add(DiffUtils.getFunctionId(funcInOld));
				}
				
			}
		}

		for(IASTFunctionDefinition func : newFuncDefsMap.values()){//added
			funcAdded.add(DiffUtils.getFunctionId(func));
		}

		if(funcAdded.size() != 0){
			result += "Added: ";
			for(String str : funcAdded){
				result += "\n\t"+ str;
			}
			result += "\n";
		}
		if(funcModified.size() != 0){
			result += "Modified: ";
			for(String str : funcModified){
				result += "\n\t"+ str;
			}
			result += "\n";
		}
		if(funcDeleted.size() != 0){
			result += "Deleted: ";
			for(String str : funcDeleted){
				result += "\n\t"+ str;
			}
			result += "\n";
		}

		//TODO: add IASTPreprocessorFunctionStyleMacroDefinition
		diffResult = result;
		return result;
	}
}
