package edu.sjtu.stap.diff.ast;

import org.eclipse.cdt.core.dom.ast.*;
import org.eclipse.cdt.core.dom.ast.cpp.*;
import org.eclipse.cdt.core.parser.IProblem;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTProblemDeclaration;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTSimpleDeclaration;

/**
 * for test purpose
 */
public class MyCPPASTVisitor extends ASTVisitor {

    public MyCPPASTVisitor(boolean visitNodes) {
        super(visitNodes);
    }

    public MyCPPASTVisitor() {
    }

//    @Override
//    public int visit(IASTDeclaration declaration) {
//        if (declaration instanceof IASTSimpleDeclaration) {
//            IASTSimpleDeclaration simpleDeclaration = (IASTSimpleDeclaration) declaration;
////            System.out.println(simpleDeclaration.getRawSignature());
//        }else if(declaration instanceof CPPASTProblemDeclaration){
//            CPPASTProblemDeclaration problem = (CPPASTProblemDeclaration) declaration;
//            System.out.println("*****CPPASTProblemDeclaration: "+ problem.getRawSignature());
//            System.out.println(problem.getProblem().isError());
//        }
//        return super.visit(declaration);
//    }


    @Override
    public int visit(IASTDeclaration declaration) {
        if (declaration instanceof IASTFunctionDefinition) {
            //1. cpp function

        } else if (declaration instanceof CPPASTSimpleDeclaration && ((CPPASTSimpleDeclaration) declaration).getRawSignature().startsWith("class")) {
            CPPASTSimpleDeclaration cppastSimpleDeclaration = (CPPASTSimpleDeclaration) declaration;
            //TODO: maybe inaccurate
            //2. class

        }else if(declaration instanceof CPPASTProblemDeclaration){
            CPPASTProblemDeclaration problem = (CPPASTProblemDeclaration) declaration;
            //3. problem declaration, such as unidentified macro expansion whose macro definition is in a different file
//            System.out.println("*****CPPASTProblemDeclaration: "+ problem.getRawSignature());
//            System.out.println(problem.getProblem().isError());
        }
        else {//other declaration
            System.out.println(declaration.toString());

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
                System.out.println("FuncMacro:");
                System.out.println(functionStyleMacroDefinition.getRawSignature());
            }
//				System.out.println(macroDefinition.getName() );
//				System.out.println(macroDefinition.getExpansion());
        }

        return super.visit(tu);
    }
}