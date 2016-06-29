package edu.sjtu.stap.diff.ast;

import org.eclipse.cdt.core.dom.ast.*;
import org.eclipse.cdt.core.dom.ast.cpp.*;

public class MyCPPASTVisitor extends ASTVisitor {

    public MyCPPASTVisitor(boolean visitNodes) {
        super(visitNodes);
    }

    public MyCPPASTVisitor() {
    }

    @Override
    public int visit(IASTDeclaration declaration) {
        if (declaration instanceof IASTSimpleDeclaration) {
            IASTSimpleDeclaration simpleDeclaration = (IASTSimpleDeclaration) declaration;
            System.out.println(simpleDeclaration.getRawSignature());
        }
        return super.visit(declaration);
    }

    @Override
    public int visit(IASTDeclarator declarator) {
        IASTName name = declarator.getName();
        IBinding binding = name.resolveBinding();
        if (binding instanceof ICPPClassType) {
            System.out.println("ICPPClassType: " + binding.toString());
        } else if (binding instanceof ICPPConstructor) {
            System.out.println("ICPPConstructor: " + binding.toString());
        } else if (binding instanceof ICPPField) {
            System.out.println("ICPPField: " + binding.toString());
        } else if (binding instanceof ICPPFunction) {
            System.out.println("ICPPFunction: " + binding.toString());
        } else if (binding instanceof ICPPMember) {
            System.out.println("ICPPMember: " + binding.toString());
        } else if (binding instanceof ICPPNamespace) {
            System.out.println("ICPPNamespace: " + binding.toString());
        } else if (binding instanceof ICPPVariable) {
            System.out.println("ICPPVariable: " + binding.toString());
        } else if (binding instanceof IParameter) {
            System.out.println("IParameter: " + binding.toString());
        }
        return super.visit(declarator);
    }
}