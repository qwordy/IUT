package edu.sjtu.stap.diff.ast;

import edu.sjtu.stap.diff.diff.DiffUtils;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;



/**
 *
 * @author weizhaoy
 *
 */
public class XFunctionDeclaration {

	IASTFunctionDefinition functionDefinition;
	String id;
	
	public XFunctionDeclaration (IASTFunctionDefinition functionDefinition){
		this.functionDefinition = functionDefinition;
		id = this._getId();
	}

	public String getId(){
		return id;
	}
	
	public IASTFunctionDefinition getOrigin(){
		return this.functionDefinition;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.functionDefinition.getFileLocation().getStartingLineNumber()+"")//line number
		.append(":")
		.append(id);//signature
		
		return sb.toString();
	}
	private String _getId() {
		
		return DiffUtils.getFunctionSignature(functionDefinition);
	}
}
