package edu.sjtu.stap.diff.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.*;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNamespaceDefinition;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTProblemDeclaration;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTSimpleDeclaration;

public class MyASTVisitor extends ASTVisitor {

    private List<IASTFunctionDefinition> funcs = new ArrayList<>();// functions

    private List<CPPASTSimpleDeclaration> classes = new ArrayList<>();//classes
    private List<CPPASTProblemDeclaration> problems = new ArrayList<>();//problems
    private List<ICPPASTNamespaceDefinition> namesps = new ArrayList<>();//namespaces
    private List<IASTDeclaration> fields = new ArrayList<>();//fields members
    private List<IASTDeclaration> otherDecls = new ArrayList<>();//other declarations
    private List<IASTPreprocessorFunctionStyleMacroDefinition> IASTPreprocessorFunctionStyleMacroDefinition = new ArrayList<>();//other declarations


    public MyASTVisitor() {

        super.shouldVisitDeclarations = true;
        super.shouldVisitNamespaces = true;
        super.shouldVisitTranslationUnit = true;
    }


    @Override
    public int visit(IASTDeclaration declaration) {
        if (declaration instanceof IASTFunctionDefinition) {
            //cpp function
            funcs.add((IASTFunctionDefinition) declaration);
        } else if (declaration instanceof CPPASTSimpleDeclaration && ((CPPASTSimpleDeclaration) declaration).getRawSignature().startsWith("class")) {
            CPPASTSimpleDeclaration cppastSimpleDeclaration = (CPPASTSimpleDeclaration) declaration;
            //maybe inaccurate
            //class
            classes.add(cppastSimpleDeclaration);
        }else if(declaration instanceof CPPASTProblemDeclaration){
            //problem declaration, such as unidentified macro expansion whose macro definition is in a different file
            CPPASTProblemDeclaration problem = (CPPASTProblemDeclaration) declaration;
//            System.out.println("*****CPPASTProblemDeclaration: "+ problem.getRawSignature());
            problems.add(problem);
//            System.out.println(problem.getProblem().isError());
        }else if(declaration instanceof IASTSimpleDeclaration){
            IASTSimpleDeclaration simpleDeclaration = (IASTSimpleDeclaration) declaration;


            if(simpleDeclaration.getDeclarators().length > 0 && simpleDeclaration.getDeclarators()[0].getName().resolveBinding() instanceof IField){
                //todo: the other way to determine whether is a fields member of a class
//                System.out.println("IField");
                fields.add(simpleDeclaration);
            }else{
                //todo: other simpleDeclaration such as global variable etc.
                otherDecls.add(simpleDeclaration);
            }
//            System.out.println("simple: "+simpleDeclaration.getRawSignature());
//            System.out.println(simpleDeclaration.getDeclSpecifier().getRawSignature());
            for (IASTDeclarator iastDeclarator : simpleDeclaration.getDeclarators()) {
//                System.out.println(iastDeclarator.getName().getRawSignature());
                //the way above is preferred to the below one
//                System.out.println(iastDeclarator.getRawSignature());
            }
        }
        else {//todo: other declaration
            System.out.println("other:");
            System.out.println(declaration.toString());
            System.out.println(declaration.getRawSignature());
            otherDecls.add(declaration);
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



    public List<IASTFunctionDefinition> getFuncs() {
        return funcs;
    }

    public List<IASTDeclaration> getOtherDecls() {
        return otherDecls;
    }


    public List<ICPPASTNamespaceDefinition> getNamesps() {
        return namesps;
    }

    public List<CPPASTSimpleDeclaration> getClasses() {
        return classes;
    }

    public List<CPPASTProblemDeclaration> getProblems() {
        return problems;
    }

    public List<IASTDeclaration> getFields() {
        return fields;
    }
}
