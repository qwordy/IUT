package edu.sjtu.stap.diff.diff;

import org.apache.commons.io.FileUtils;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTStandardFunctionDeclarator;

import java.io.File;
import java.io.IOException;

public class DiffUtils {


	public static String getFunctionId(IASTFunctionDefinition functionDefinition) { //function tostring
//		System.out.println(functionDefinition.getRawSignature());

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
				sb.append( para.getRawSignature() + " " );//TODO: add ","
			}
			sb.append(")");
		}
		
		return sb.toString();
	}

	public static String getFileContent(String filename) {
		try {
			return FileUtils.readFileToString(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
