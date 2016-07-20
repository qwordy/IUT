package edu.sjtu.stap.diff.ast;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTAttribute;
import org.eclipse.cdt.core.dom.ast.IASTAttributeSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTComment;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTInitializer;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTStandardFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTToken;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTDecltypeSpecifier;
import org.eclipse.cdt.core.model.IFunctionDeclaration;
import org.eclipse.cdt.internal.core.dom.parser.ASTAmbiguousNode;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTFunctionDefinition;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTProblemDeclaration;
import org.w3c.dom.Attr;

public class MultiVisitor extends ASTVisitor{

	public MultiVisitor(){
		super();
	}
	public MultiVisitor(boolean visitAll) {
		// TODO Auto-generated constructor stub
		super(visitAll);
	}
	
	
	
	@Override
	public int visit(ASTAmbiguousNode astAmbiguousNode) {
		// TODO Auto-generated method stub
		System.out.println("ASTAmbiguousNode: "+astAmbiguousNode.getRawSignature() );
		return super.visit(astAmbiguousNode);
	}
	
	
	@Override
	public int visit(ICPPASTDecltypeSpecifier decltypeSpecifier) {
		// TODO Auto-generated method stub
		System.out.println("ICPPASTDecltypeSpecifier: "+decltypeSpecifier.getRawSignature() );
		return super.visit(decltypeSpecifier);
	}
	
	@Override
	public int visit(IASTAttribute attribute) {
		// TODO Auto-generated method stub
		System.out.println("IASTAttribute: "+attribute.getRawSignature() );
		return super.visit(attribute);
	}

	

	@Override
	public int visit(IASTDeclaration declaration) {
		// TODO Auto-generated method stub

//		if(declaration instanceof CPPASTFunctionDefinition){//cpp function 
//			System.out.println("CPPFunction: "+ ((CPPASTFunctionDefinition) declaration).getDeclSpecifier().getRawSignature());
//			System.out.println("                    "+ ((CPPASTFunctionDefinition) declaration).getDeclarator().getRawSignature());
//
//		}

		System.out.println("DECLARATION: "+declaration.toString());

		if(declaration instanceof IASTFunctionDefinition){
			IASTFunctionDefinition iastFunctionDefinition = (IASTFunctionDefinition) declaration;
			String info = "ASTFunction:"
					//file path
					+"\n\t"+iastFunctionDefinition.getFileLocation().getStartingLineNumber() 
					+"\n\t"+iastFunctionDefinition.getFileLocation().getEndingLineNumber() 
					
					//return type
					+"\n\t"+iastFunctionDefinition.getDeclSpecifier() 
					//function name
					+"\n\t"+iastFunctionDefinition.getDeclarator().getName(); 

			if(iastFunctionDefinition.getDeclarator() instanceof IASTStandardFunctionDeclarator){
				IASTStandardFunctionDeclarator standardFunctionDeclarator = (IASTStandardFunctionDeclarator) iastFunctionDefinition.getDeclarator();
				IASTParameterDeclaration[] paras = standardFunctionDeclarator.getParameters();
				for(IASTParameterDeclaration para : paras){
					// parameters' type
					info += "\n\t"+para.getDeclSpecifier();
				}
			}
			//				IASTAttribute[] attrs = iastFunctionDefinition.getDeclarator().getAttributes();
			//				if(attrs.length != 0){
			//					for(int i = 0; i< attrs.length;i++){
			//						info += ("/n/t"+attrs[i]);
			//					}
			//				}
			//				
			//				IASTAttributeSpecifier[] attrSps = iastFunctionDefinition.getDeclarator().getAttributeSpecifiers();
			//				if(attrSps.length != 0){
			//					for(int i = 0; i< attrSps.length;i++){
			//						info += ("/n/t"+attrSps[i]);
			//					}
			//				}
			info += "\n\t"+iastFunctionDefinition.getBody().getRawSignature() ;
			System.out.println(info);
			//				System.out.println("ASTFunction: "+ ((IASTFunctionDefinition) declaration).getDeclSpecifier().getRawSignature());
			//				System.out.println("                    "+ ((IASTFunctionDefinition) declaration).getDeclarator().getRawSignature());
		}else if(declaration instanceof CPPASTProblemDeclaration){
			CPPASTProblemDeclaration problem = (CPPASTProblemDeclaration) declaration;
			System.out.println("*****CPPASTProblemDeclaration: "+ problem.getRawSignature());
		}



		//		System.out.println("IASTDeclaration: "+declaration.toString() + "\n\t"+declaration.getRawSignature());

		return super.visit(declaration);
	}

	@Override
	public int visit(IASTDeclarator declarator) {
		// TODO Auto-generated method stub
		System.out.println("IASTDeclarator: "+declarator.getName());
		System.out.println("IASTDeclarator: "+declarator.toString());

		return super.visit(declarator);
	}

	@Override
	public int visit(IASTDeclSpecifier declSpec) {
		// TODO Auto-generated method stub
		System.out.println("IASTDeclSpecifier: "+declSpec.getRawSignature() );
		System.out.println("IASTDeclSpecifier: "+declSpec.toString() );

		return super.visit(declSpec);
	}

	@Override
	public int visit(IASTExpression expression) {
		// TODO Auto-generated method stub
		System.out.println("IASTExpression: "+expression.getRawSignature() );

		return super.visit(expression);
	}

	@Override
	public int visit(IASTInitializer initializer) {
		// TODO Auto-generated method stub
		System.out.println("IASTInitializer: "+initializer.getParent().getRawSignature() );

		return super.visit(initializer);
	}

	@Override
	public int visit(IASTName name) {
		// TODO Auto-generated method stub
		//System.out.println("IASTName: "+name.getRawSignature() );

		return super.visit(name);
	}

	@Override
	public int visit(IASTParameterDeclaration parameterDeclaration) {
		// TODO Auto-generated method stub
		System.out.println("IASTParameterDeclaration: "+parameterDeclaration.getRawSignature() );

		return super.visit(parameterDeclaration);
	}

	@Override
	public int visit(IASTStatement statement) {
		// TODO Auto-generated method stub
		System.out.println("IASTStatement: "+statement.getRawSignature() );
//		System.out.println("IASTStatement: "+statement.toString() );

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
		//System.out.println("IASTTranslationUnit: "+tu.getRawSignature() );

		return super.visit(tu);
	}

}
