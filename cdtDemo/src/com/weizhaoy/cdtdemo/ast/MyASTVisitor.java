package com.weizhaoy.cdtdemo.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;

public class MyASTVisitor extends ASTVisitor {

	List<IASTDeclaration> decls =  new ArrayList<>();
	@Override
	public int visit(IASTDeclaration declaration) {
		// TODO Auto-generated method stub
		decls.add(declaration);
		return super.visit(declaration);
	}
}
