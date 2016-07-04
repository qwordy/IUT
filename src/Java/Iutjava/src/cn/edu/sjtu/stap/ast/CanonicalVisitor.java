package cn.edu.sjtu.stap.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LineComment;

public class CanonicalVisitor extends ASTVisitor {
	
	public CanonicalVisitor () {
		super(false);
		
	}

	boolean visit (ASTNode node) {
		if (node instanceof LineComment ||
				node instanceof BlockComment ||
				node instanceof Javadoc) {
			node.delete(); //delete all kinds of comments
			return false;
		} else {
			// node.
			return true;
		}
	}
}
