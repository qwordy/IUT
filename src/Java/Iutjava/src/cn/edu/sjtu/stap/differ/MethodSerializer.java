package cn.edu.sjtu.stap.differ;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodSerializer extends ASTVisitor {
	
	
	@Override
	public boolean visit (MethodDeclaration node) {
		System.out.println(node.getName());
		// System.out.println(node.toString());
		
		// ASTMatcher
		return false;
	}
	
	
}
