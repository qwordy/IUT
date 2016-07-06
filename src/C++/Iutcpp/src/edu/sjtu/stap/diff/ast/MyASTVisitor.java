package edu.sjtu.stap.diff.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.*;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNamespaceDefinition;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTProblemDeclaration;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTSimpleDeclaration;

public class MyASTVisitor extends ASTVisitor {

    private List<IASTDeclaration> decls = new ArrayList<>();//other declarations
    private List<IASTFunctionDefinition> funcs = new ArrayList<>();// functions
    private List<ICPPASTNamespaceDefinition> namesps = new ArrayList<>();//namespaces
    private List<CPPASTSimpleDeclaration> classes = new ArrayList<>();//classes


    public MyASTVisitor() {

        super.shouldVisitDeclarations = true;
        super.shouldVisitNamespaces = true;
    }


    @Override
    public int visit(IASTDeclaration declaration) {
        if (declaration instanceof IASTFunctionDefinition) {
            //cpp function
            funcs.add((IASTFunctionDefinition) declaration);
        } else if (declaration instanceof CPPASTSimpleDeclaration && ((CPPASTSimpleDeclaration) declaration).getRawSignature().startsWith("class")) {
            CPPASTSimpleDeclaration cppastSimpleDeclaration = (CPPASTSimpleDeclaration) declaration;
            //TODO: maybe inaccurate
            //class
            classes.add(cppastSimpleDeclaration);
        }else if(declaration instanceof CPPASTProblemDeclaration){
            //todo: problem declaration, such as unidentified macro expansion whose macro definition is in a different file
            CPPASTProblemDeclaration problem = (CPPASTProblemDeclaration) declaration;
//            System.out.println("*****CPPASTProblemDeclaration: "+ problem.getRawSignature());
//            System.out.println(problem.getProblem().isError());
        }else if(declaration instanceof IASTSimpleDeclaration){
            IASTSimpleDeclaration simpleDeclaration = (IASTSimpleDeclaration) declaration;

            if(simpleDeclaration.getDeclarators()[0].getName().resolveBinding() instanceof IField){
                //todo: the other way to determine whether is a field member of a class
                System.out.println("IField");
            }else{
                //todo: other simpleDeclaration such as global variable etc.
            }
            System.out.println("simple: "+simpleDeclaration.getRawSignature());
//            System.out.println(simpleDeclaration.getDeclSpecifier().getRawSignature());
            for (IASTDeclarator iastDeclarator : simpleDeclaration.getDeclarators()) {
                System.out.println(iastDeclarator.getName().getRawSignature());
                //the way above is preferred to the below one
//                System.out.println(iastDeclarator.getRawSignature());
            }
        }
        else {//todo: other declaration
            System.out.println("other:");
            System.out.println(declaration.toString());
            System.out.println(declaration.getRawSignature());
            decls.add(declaration);
        }

        return super.visit(declaration);
    }

    @Override
    public int visit(ICPPASTNamespaceDefinition namespaceDefinition) {
        namesps.add(namespaceDefinition);
        return super.visit(namespaceDefinition);
    }

    @Override
    public int visit(IASTTranslationUnit tu) {
        IASTPreprocessorMacroDefinition[] macroDefinitions = tu.getMacroDefinitions();
        for (IASTPreprocessorMacroDefinition macroDefinition : macroDefinitions){
            if(macroDefinition instanceof IASTPreprocessorFunctionStyleMacroDefinition){
                IASTPreprocessorFunctionStyleMacroDefinition functionStyleMacroDefinition = (IASTPreprocessorFunctionStyleMacroDefinition) macroDefinition;
//                IASTFunctionStyleMacroParameter[] macroParameters = functionStyleMacroDefinition.getParameters();
//                for(IASTFunctionStyleMacroParameter mp : macroParameters){
//						System.out.println("macroprp: "+mp.getParameter());
//                }
                //todo function style macro
                System.out.println(functionStyleMacroDefinition.getRawSignature());
            }
//				System.out.println(macroDefinition.getName() );
//				System.out.println(macroDefinition.getExpansion());
        }


        return super.visit(tu);
    }

    /**
     * @return the decls
     */
    public List<IASTDeclaration> getDecls() {
        return decls;
    }

    public List<IASTFunctionDefinition> getFuncs() {
        return funcs;
    }

    public List<ICPPASTNamespaceDefinition> getNamesps() {
        return namesps;
    }

    public List<CPPASTSimpleDeclaration> getClasses() {
        return classes;
    }
}
