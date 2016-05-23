package com.weizhaoy.cdtdemo.diff;

import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTStandardFunctionDeclarator;

public class DiffUtils {

	public static String getFunctionId(IASTFunctionDefinition funcDef) { //function tostring
		System.out.println(funcDef.getRawSignature());
		String info = 
				//file path
//				funcDef.getContainingFilename() +
				//return type
				
				"\t"+funcDef.getDeclSpecifier() +
				//function name
				"\t"+funcDef.getDeclarator().getName(); 

		if(funcDef.getDeclarator() instanceof IASTStandardFunctionDeclarator){
			IASTStandardFunctionDeclarator standardFunctionDeclarator = (IASTStandardFunctionDeclarator) funcDef.getDeclarator();
			IASTParameterDeclaration[] paras = standardFunctionDeclarator.getParameters();
			for(IASTParameterDeclaration para : paras){
				// parameters' type
				info += "\t"+para.getDeclSpecifier();
			}
		}
		
//		System.out.println("Debug: info ===> "+info);
		return info;
	}

}
