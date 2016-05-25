package com.weizhaoy.cdtdemo.diff;

import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTStandardFunctionDeclarator;

public class DiffUtils {


	public static String getFunctionId(IASTFunctionDefinition functionDefinition) { //function tostring
		System.out.println(functionDefinition.getRawSignature());

		String info = 
				//file path
//				funcDef.getContainingFilename() +
				//return type

				" "+functionDefinition.getDeclSpecifier() +

				//function name
				" "+functionDefinition.getDeclarator().getName();

		if(functionDefinition.getDeclarator() instanceof IASTStandardFunctionDeclarator){
			IASTStandardFunctionDeclarator standardFunctionDeclarator = (IASTStandardFunctionDeclarator) functionDefinition.getDeclarator();
			IASTParameterDeclaration[] paras = standardFunctionDeclarator.getParameters();
			info += "(";
			for(IASTParameterDeclaration para : paras){
				// parameters' type
//				info += para.getDeclSpecifier() + " ";
				info += para.getRawSignature() + " ";
			}
			info += ")";
		}
		
//		System.out.println("Debug: info ===> "+info);
		return info;
	}

	public static String getFunctionSignature(IASTFunctionDefinition functionDefinition) {
		StringBuilder sb = new StringBuilder();
		sb.append(functionDefinition.getDeclSpecifier()).append(" ");//return type
		sb.append(functionDefinition.getDeclarator().getName()).append(" ");//function name
		
		if(functionDefinition.getDeclarator() instanceof IASTStandardFunctionDeclarator){
			IASTStandardFunctionDeclarator standardFunctionDeclarator = (IASTStandardFunctionDeclarator) functionDefinition.getDeclarator();
			IASTParameterDeclaration[] paras = standardFunctionDeclarator.getParameters();
			
			sb.append("(");
			for(IASTParameterDeclaration para : paras){
				// parameters' type
				sb.append( para.getDeclSpecifier() + " " );
			}
			sb.append(")");
		}
		
		return sb.toString();
	}

}
