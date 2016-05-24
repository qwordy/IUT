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

				" "+funcDef.getDeclSpecifier() +

				//function name
				" "+funcDef.getDeclarator().getName();

		if(funcDef.getDeclarator() instanceof IASTStandardFunctionDeclarator){
			IASTStandardFunctionDeclarator standardFunctionDeclarator = (IASTStandardFunctionDeclarator) funcDef.getDeclarator();
			IASTParameterDeclaration[] paras = standardFunctionDeclarator.getParameters();
			info += "(";
			for(IASTParameterDeclaration para : paras){
				// parameters' type
				info += para.getDeclSpecifier() + " ";
			}
			info += ")";
		}
		
//		System.out.println("Debug: info ===> "+info);
		return info;
	}

}
