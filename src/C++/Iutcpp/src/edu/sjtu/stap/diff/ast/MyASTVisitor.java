package edu.sjtu.stap.diff.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNamespaceDefinition;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTSimpleDeclaration;

public class MyASTVisitor extends ASTVisitor {

	private List<IASTDeclaration> decls =  new ArrayList<>();
	private List<IASTFunctionDefinition> funcs = new ArrayList<>();
	private List<ICPPASTNamespaceDefinition> namesps = new ArrayList<>();
	private List<CPPASTSimpleDeclaration> classes = new ArrayList<>();


	public MyASTVisitor() {

		super.shouldVisitDeclarations = true;
		super.shouldVisitNamespaces = true;
	}


	@Override
	public int visit(IASTDeclaration declaration) {
		// TODO Auto-generated method stub
		if(declaration instanceof IASTFunctionDefinition){
			funcs.add((IASTFunctionDefinition) declaration);
		}else if(declaration instanceof CPPASTSimpleDeclaration){
			CPPASTSimpleDeclaration cppastSimpleDeclaration = (CPPASTSimpleDeclaration) declaration;
			//System.out.println(cppastSimpleDeclaration.getRawSignature().startsWith("class"));
			if(cppastSimpleDeclaration.getRawSignature().startsWith("class")){//TODO: maybe inaccurate
				//System.out.println(cppastSimpleDeclaration.getRawSignature() );
				classes.add(cppastSimpleDeclaration);
			}else {
				decls.add(declaration);
			}

		}else{
			decls.add(declaration);
		}

		return super.visit(declaration);
	}

	@Override
	public int visit(ICPPASTNamespaceDefinition namespaceDefinition) {
		namesps.add(namespaceDefinition);
		return super.visit(namespaceDefinition);
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

	public List<ICPPASTNamespaceDefinition> getNamesps() {
		return namesps;
	}

	public List<CPPASTSimpleDeclaration> getClasses() {
		return classes;
	}
}
