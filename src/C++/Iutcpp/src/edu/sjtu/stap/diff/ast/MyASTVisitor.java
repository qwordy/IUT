package edu.sjtu.stap.diff.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;

public class MyASTVisitor extends ASTVisitor {

	private List<IASTDeclaration> decls =  new ArrayList<>();
	private List<IASTFunctionDefinition> funcs = new ArrayList<>();
	
	 public MyASTVisitor() {
		super.shouldVisitDeclarations = true;
	}
	@Override
	public int visit(IASTDeclaration declaration) {
		// TODO Auto-generated method stub
		if(declaration instanceof IASTFunctionDefinition){
			funcs.add((IASTFunctionDefinition) declaration);
		}else{
			decls.add(declaration);
		}
		
		return super.visit(declaration);
	}
	/**
	 * @return the decls
	 */
	public List<IASTDeclaration> getDecls() {
		return decls;
	}
	
	public List<IASTFunctionDefinition> getFuncs(){
		return funcs;
	}
	
}
