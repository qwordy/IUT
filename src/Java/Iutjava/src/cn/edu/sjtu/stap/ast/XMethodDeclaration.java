package cn.edu.sjtu.stap.ast;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import cn.edu.sjtu.stap.Util;

/**
 * The wrapper class for the method declaration
 * @author yejiabin
 *
 */
public class XMethodDeclaration {

	MethodDeclaration methodDecl;
	ASTNode canonicalAST;
	String id;
	
	public XMethodDeclaration (MethodDeclaration methodDecl) {
		this.methodDecl = methodDecl;
		Block block = this.methodDecl.getBody();
		block.accept(new CanonicalVisitor());
		id = this._getId();
	}
	
	public String getId () {
		return id;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.methodDecl.getStartPosition())
			.append(":")
			.append(id);
		return sb.toString();
	}
	
	private String _getId () {
		// this.methodDecl.get
		return Util.getMethodSignature(methodDecl);
	}
	
	public ASTNode getCanonical() {
		return this.methodDecl;
	}
	
	public MethodDeclaration getOrigin() {
		return this.methodDecl;
	}
	
	public ASTNode canonicalAST () {
		// this.methodDe
		StringBuilder sb = new StringBuilder();
		
		

		// this.methodDecl.
		
		// String methodContent = formattedText.toString();
		//sb.append(methodContent);
		
		/*
		boolean lastBlank = false;
		for (int i = 0; i < methodContent.length(); i++) {
			char ch = methodContent.charAt(i);
			if (ch != ' ' && ch != '\t' && ch != '\n') {
				if (lastBlank) {
					sb.append(' ');
				}
				sb.append(ch);
				lastBlank = false;
			} else {
				lastBlank = ch != '\n';
			}
		}*/
		
		// this.methodDecl.
		return this.methodDecl;
	}
}
