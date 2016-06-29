package edu.sjtu.stap.diff.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IBinding;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNamespaceDefinition;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTSimpleDeclaration;

public class MyASTVisitor extends ASTVisitor {

	private List<IASTDeclaration> decls =  new ArrayList<>();//other declarations
	private List<IASTFunctionDefinition> funcs = new ArrayList<>();// functions
	private List<ICPPASTNamespaceDefinition> namesps = new ArrayList<>();//namespaces
	private List<CPPASTSimpleDeclaration> classes = new ArrayList<>();//classes


	public MyASTVisitor() {

		super.shouldVisitDeclarations = true;
		super.shouldVisitNamespaces = true;
	}


	@Override
	public int visit(IASTDeclaration declaration) {
		// TODO Auto-generated method stub
		if(declaration instanceof IASTFunctionDefinition){//cpp function
			funcs.add((IASTFunctionDefinition) declaration);
		}else if(declaration instanceof CPPASTSimpleDeclaration){
			CPPASTSimpleDeclaration cppastSimpleDeclaration = (CPPASTSimpleDeclaration) declaration;


			if(cppastSimpleDeclaration.getRawSignature().startsWith("class")){//TODO: maybe inaccurate
				//class
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
