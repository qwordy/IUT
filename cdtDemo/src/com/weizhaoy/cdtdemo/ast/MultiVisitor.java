package com.weizhaoy.cdtdemo.ast;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTAttribute;
import org.eclipse.cdt.core.dom.ast.IASTComment;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTInitializer;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTToken;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTFunctionDefinition;

public class MultiVisitor extends ASTVisitor{

	
	@Override
	public int visit(IASTAttribute attribute) {
		// TODO Auto-generated method stub
		System.out.println("IASTAttribute: "+attribute.getRawSignature());
		return super.visit(attribute);
	}

	@Override
	public int visit(IASTComment comment) {
		// TODO Auto-generated method stub
		System.out.println("IASTComment: "+comment.getRawSignature());

		return super.visit(comment);
	}

	@Override
	public int visit(IASTDeclaration declaration) {
		// TODO Auto-generated method stub
		if(declaration instanceof CPPASTFunctionDefinition){//cpp function 
			System.out.println("Function: "+ ((CPPASTFunctionDefinition) declaration).getDeclSpecifier().getRawSignature());
			System.out.println("                    "+ ((CPPASTFunctionDefinition) declaration).getDeclarator().getRawSignature());
			
		}
			
		System.out.println("IASTDeclaration: "+declaration.toString() + "\n\t"+declaration.getRawSignature());

		return super.visit(declaration);
	}

	@Override
	public int visit(IASTDeclarator declarator) {
		// TODO Auto-generated method stub
		System.out.println("IASTDeclarator: "+declarator.getName());

		return super.visit(declarator);
	}

	@Override
	public int visit(IASTDeclSpecifier declSpec) {
		// TODO Auto-generated method stub
		System.out.println("IASTDeclSpecifier: "+declSpec.getRawSignature());

		return super.visit(declSpec);
	}

	@Override
	public int visit(IASTExpression expression) {
		// TODO Auto-generated method stub
		System.out.println("IASTExpression: "+expression.getRawSignature());

		return super.visit(expression);
	}

	@Override
	public int visit(IASTInitializer initializer) {
		// TODO Auto-generated method stub
		System.out.println("IASTInitializer: "+initializer.getRawSignature());

		return super.visit(initializer);
	}

	@Override
	public int visit(IASTName name) {
		// TODO Auto-generated method stub
		System.out.println("IASTName: "+name.getRawSignature());

		return super.visit(name);
	}

	@Override
	public int visit(IASTParameterDeclaration parameterDeclaration) {
		// TODO Auto-generated method stub
		System.out.println("IASTParameterDeclaration: "+parameterDeclaration.getRawSignature());

		return super.visit(parameterDeclaration);
	}

	@Override
	public int visit(IASTStatement statement) {
		// TODO Auto-generated method stub
		System.out.println("IASTStatement: "+statement.getRawSignature());

		return super.visit(statement);
	}

	@Override
	public int visit(IASTToken token) {
		// TODO Auto-generated method stub
		System.out.println("IASTToken: "+token.getTokenCharImage().toString());

		return super.visit(token);
	}

	@Override
	public int visit(IASTTranslationUnit tu) {
		// TODO Auto-generated method stub
		System.out.println("IASTTranslationUnit: "+tu.getRawSignature());

		return super.visit(tu);
	}

}
