package edu.sjtu.stap.diff.diff;

import edu.sjtu.stap.diff.ast.MyASTVisitor;
import org.apache.commons.io.FileUtils;
import org.eclipse.cdt.core.dom.ast.*;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTUsingDirective;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiffUtils {


    public static String getFunctionId(IASTFunctionDefinition functionDefinition) { //function tostring
//		System.out.println(functionDefinition.getRawSignature());

        String info =
                //file path
//				funcDef.getContainingFilename() +
                //return type

                " " + functionDefinition.getDeclSpecifier() +

                        //function name
                        " " + functionDefinition.getDeclarator().getName();

        if (functionDefinition.getDeclarator() instanceof IASTStandardFunctionDeclarator) {
            IASTStandardFunctionDeclarator standardFunctionDeclarator = (IASTStandardFunctionDeclarator) functionDefinition.getDeclarator();
            IASTParameterDeclaration[] paras = standardFunctionDeclarator.getParameters();
            info += "(";
            for (IASTParameterDeclaration para : paras) {
                // parameters' type
//				info += para.getDeclSpecifier() + " ";
                info += para.getRawSignature() + " ";
            }
            info += ")";
        }

//		System.out.println("Debug: info ===> "+info);
        return info;
    }

    public static String getFunctionSignature(IASTFunctionDefinition functionDefinition) {
        StringBuilder sb = new StringBuilder();
        sb.append(functionDefinition.getDeclSpecifier()).append(" ");//return type
        sb.append(functionDefinition.getDeclarator().getName()).append(" ");//function name

        if (functionDefinition.getDeclarator() instanceof IASTStandardFunctionDeclarator) {
            IASTStandardFunctionDeclarator standardFunctionDeclarator = (IASTStandardFunctionDeclarator) functionDefinition.getDeclarator();
            IASTParameterDeclaration[] paras = standardFunctionDeclarator.getParameters();

            sb.append("(");
            for (IASTParameterDeclaration para : paras) {
                // parameters' type
                sb.append(para.getRawSignature() + " ");//TODO: add ","
            }
            sb.append(")");
        }

        return sb.toString();
    }

    public static String getFileContent(String filename) {
        try {
            return FileUtils.readFileToString(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * get a unique string for a non-func declaration
     * @param declaration
     * @return
     */
    public static String getDeclarationStr(IASTDeclaration declaration) {
        StringBuilder sb = new StringBuilder();
        sb.append(declaration.getFileLocation().getStartingLineNumber());
        //here can add cases by adding "else if" clause
        if (declaration instanceof IASTSimpleDeclaration) {

            IASTSimpleDeclaration simpleDeclaration = (IASTSimpleDeclaration) declaration;
            sb.append(simpleDeclaration.getDeclSpecifier().getRawSignature());
            IASTDeclarator[] declarators = simpleDeclaration.getDeclarators();
            for (IASTDeclarator declarator : declarators) {
                sb.append(declarator.getName());
            }

        } else if (declaration instanceof CPPASTUsingDirective) {
//            CPPASTUsingDirective usingDirective = (CPPASTUsingDirective) declaration;
//            sb.append(usingDirective.getQualifiedName());
            return null;
        } else {
            System.out.println(declaration.toString());
            return null;
        }
        return sb.toString();
    }


    /**
     * compare other declarations besides functions
     * @param oldDeclarations
     * @param newDeclarations
     * @return true if there's change, false if there's none
     */
    public static boolean compareDeclarations(List<IASTDeclaration> oldDeclarations, List<IASTDeclaration> newDeclarations) {

        HashMap<String, IASTDeclaration> mapNew;
        mapNew = new HashMap<>();

        List<IASTDeclaration> declAdded = new ArrayList<>();
        List<IASTDeclaration> declModified = new ArrayList<>();
        List<IASTDeclaration> declDeleted = new ArrayList<>();

        for (IASTDeclaration decl : newDeclarations) {
            mapNew.put(DiffUtils.getDeclarationStr(decl), decl);
        }

        for (IASTDeclaration decl : oldDeclarations) {
            IASTDeclaration declInNew = mapNew.get(DiffUtils.getDeclarationStr(decl));
            if (declInNew != null) {
                if (!declInNew.getRawSignature().equals(decl.getRawSignature())) { //modified
                    declModified.add(decl);

                }
                mapNew.remove(DiffUtils.getDeclarationStr(decl));
            } else {//deleted
                declDeleted.add(decl);
            }

        }
        //TODO high priority for debugging
        for (IASTDeclaration decl : mapNew.values()) {
            //added
            declAdded.add(decl);
        }
        return (declAdded.size() + declModified.size() + declDeleted.size() != 0);

    }


    /**
     * determine whether Other elements besides functions are Changed
     * @param oldAstVisitor
     * @param newAstVisitor
     * @return
     */
    public static Boolean whetherOtherChanged(MyASTVisitor oldAstVisitor, MyASTVisitor newAstVisitor) {
        return null;
    }
}
