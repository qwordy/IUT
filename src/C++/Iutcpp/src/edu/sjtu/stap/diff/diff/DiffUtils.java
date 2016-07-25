package edu.sjtu.stap.diff.diff;

import edu.sjtu.stap.diff.ast.MyASTVisitor;
import org.apache.commons.io.FileUtils;
import org.eclipse.cdt.core.dom.ast.*;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNamespaceDefinition;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTUsingDirective;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiffUtils {

//    private static int NAMESPACE_COUNT;

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
//        StringBuilder sb = new StringBuilder();
////        sb.append(declaration.getFileLocation().getStartingLineNumber());
//        //here can add cases by adding "else if" clause
//        if (declaration instanceof IASTSimpleDeclaration) {
//
//            IASTSimpleDeclaration simpleDeclaration = (IASTSimpleDeclaration) declaration;
//            sb.append(simpleDeclaration.getDeclSpecifier().getRawSignature());
//            IASTDeclarator[] declarators = simpleDeclaration.getDeclarators();
//            for (IASTDeclarator declarator : declarators) {
//                sb.append(declarator.getName());
//            }
//
//        } else if (declaration instanceof CPPASTUsingDirective) {
////            CPPASTUsingDirective usingDirective = (CPPASTUsingDirective) declaration;
////            sb.append(usingDirective.getQualifiedName());
//            return null;
//        } else {
//            System.out.println(declaration.toString());
//            return null;
//        }
//        return sb.toString();
        return declaration.getRawSignature();
    }


    /**
     * compare other declarations besides functions
     * @param oldDeclarations
     * @param newDeclarations
     * @return true if there's change, false if there's none
     */
    private static boolean compareDeclarations(List<IASTDeclaration> oldDeclarations, List<IASTDeclaration> newDeclarations) {

        if(oldDeclarations.size() != newDeclarations.size())
            return true;
        HashMap<String, IASTDeclaration> mapNew;
        mapNew = new HashMap<>();


        for (IASTDeclaration decl : newDeclarations) {
            if(DiffUtils.getDeclarationStr(decl) != null){
                mapNew.put(DiffUtils.getDeclarationStr(decl), decl);
            }
        }

        for (IASTDeclaration decl : oldDeclarations) {
            IASTDeclaration declInNew = mapNew.get(DiffUtils.getDeclarationStr(decl));
            if (declInNew != null) {
                if (!declInNew.getRawSignature().equals(decl.getRawSignature())) { //modified
                    System.out.println("decl: "+decl.getRawSignature());
                    return true;

                }
                mapNew.remove(DiffUtils.getDeclarationStr(decl));
            } else {//deleted
                System.out.println("decl: "+decl.getRawSignature());
                return true;
            }

        }
        //high priority for debugging
        for (IASTDeclaration decl : mapNew.values()) {
            //added
            System.out.println("decl: "+decl.getRawSignature());
            return true;
        }
        return false;

    }


    private static boolean compareMacros(List<IASTPreprocessorFunctionStyleMacroDefinition> oldFuncMacros, List<IASTPreprocessorFunctionStyleMacroDefinition> newFuncMacros) {
        if (oldFuncMacros.size() != newFuncMacros.size()){
            System.out.println("FuncMacros sizes inconsistent");
            return true;
        }

        HashMap<String, IASTPreprocessorFunctionStyleMacroDefinition> mapNew;
        mapNew = new HashMap<>();

//        List<IASTDeclaration> declAdded = new ArrayList<>();
//        List<IASTDeclaration> declModified = new ArrayList<>();
//        List<IASTDeclaration> declDeleted = new ArrayList<>();

        for (IASTPreprocessorFunctionStyleMacroDefinition macro : newFuncMacros) {
            if(DiffUtils.getMacroStr(macro) != null){
                mapNew.put(DiffUtils.getMacroStr(macro), macro);
            }
        }

        for (IASTPreprocessorFunctionStyleMacroDefinition macro : oldFuncMacros) {
            IASTPreprocessorFunctionStyleMacroDefinition macroInNew = mapNew.get(DiffUtils.getMacroStr(macro));
            if (macroInNew != null) {
                if (!macroInNew.getRawSignature().equals(macro.getRawSignature())) { //modified
//                    declModified.add(decl);
                    return true;

                }
                mapNew.remove(DiffUtils.getMacroStr(macro));
            } else {//deleted
//                declDeleted.add(decl);
                return true;
            }

        }
        //high priority for debugging
        for (IASTPreprocessorFunctionStyleMacroDefinition macro : mapNew.values()) {
            //added
//            declAdded.add(decl);
            return true;
        }

        return false;
    }

    private static String getMacroStr(IASTPreprocessorFunctionStyleMacroDefinition macro) {
        StringBuilder sb = new StringBuilder();

        sb.append(macro.getRawSignature());


        return sb.toString();
    }



    private static boolean compareNodes(List<IASTNode> oldNodes, List<IASTNode> newNodes) {
        if (oldNodes.size() != newNodes.size()){
            System.out.println("nodes sizes inconsistent");
            return true;
        }
        HashMap<String, IASTNode> mapNew;
        mapNew = new HashMap<>();


        for (IASTNode node : newNodes) {
            if(node.getRawSignature() != null){
                mapNew.put(node.getRawSignature(), node);
            }
        }

        for (IASTNode node : oldNodes) {
            IASTNode nodeInNew = mapNew.get(node.getRawSignature());
            if (nodeInNew != null) {
                if (!nodeInNew.getRawSignature().equals(node.getRawSignature())) { //modified
                    System.out.println("node: "+node.getRawSignature());
                    return true;

                }
                mapNew.remove(node.getRawSignature());
            } else {//deleted
                System.out.println("node: "+node.getRawSignature());
                return true;
            }

        }
        //high priority for debugging
        for (IASTNode node : mapNew.values()) {
            //added
            System.out.println("node: "+node.getRawSignature());
            return true;
        }
        return false;
    }


    private static boolean compareNamespaces(List<ICPPASTNamespaceDefinition> oldNamespaces, List<ICPPASTNamespaceDefinition> newNamespaces) {

        if(oldNamespaces.size() != newNamespaces.size()){
            System.out.println("Namespaces sizes inconsistent");
            return true;
        }

//        NAMESPACE_COUNT = 0;

        HashMap<String, ICPPASTNamespaceDefinition> mapNew;
        mapNew = new HashMap<>();


        for (ICPPASTNamespaceDefinition namespace : newNamespaces) {
            if(getNamespaceStr(namespace) != null){
                mapNew.put(getNamespaceStr(namespace), namespace);
            }
        }

        for (ICPPASTNamespaceDefinition namespace : oldNamespaces) {
            ICPPASTNamespaceDefinition namespaceInNew = mapNew.get(getNamespaceStr(namespace));
            if (namespaceInNew != null) {
                if (compareSingleNamespace(namespace, namespaceInNew)) { //modified
                    System.out.println("namespace: "+namespace.getRawSignature());
                    return true;

                }
                mapNew.remove(getNamespaceStr(namespace));
            } else {//deleted
                System.out.println("namespace: "+namespace.getRawSignature());
                return true;
            }

        }
        //high priority for debugging
        for (ICPPASTNamespaceDefinition namespace : mapNew.values()) {
            //added
            System.out.println("namespace: "+namespace.getRawSignature());
            return true;
        }
        return false;
    }

    private static boolean compareSingleNamespace(ICPPASTNamespaceDefinition namespace, ICPPASTNamespaceDefinition namespaceInNew) {
        List<IASTDeclaration> oldDeclarations = new ArrayList<>();
        List<ICPPASTNamespaceDefinition> oldDelcNamespaces = new ArrayList<>();
        List<IASTDeclaration> newDeclarations = new ArrayList<>();
        List<ICPPASTNamespaceDefinition> newDelcNamespaces = new ArrayList<>();

        for (IASTDeclaration oldDeclaration : namespace.getDeclarations()) {
            if(oldDeclaration instanceof IASTFunctionDefinition){
                //do nothing
            }else if(oldDeclaration instanceof ICPPASTNamespaceDefinition){
                oldDelcNamespaces.add((ICPPASTNamespaceDefinition) oldDeclaration);
            }else{
                oldDeclarations.add(oldDeclaration);
            }
        }

        for (IASTDeclaration newDeclaration : namespaceInNew.getDeclarations()) {
            if(newDeclaration instanceof IASTFunctionDefinition){
                //do nothing
            }else if(newDeclaration instanceof ICPPASTNamespaceDefinition){
                newDelcNamespaces.add((ICPPASTNamespaceDefinition) newDeclaration);
            }else{
                newDeclarations.add(newDeclaration);
            }
        }

        if(compareDeclarations(oldDeclarations,newDeclarations)){
            return true;
        }
        if(compareNamespaces(oldDelcNamespaces,newDelcNamespaces)){
            return true;
        }
        return false;
    }

    private static String getNamespaceStr(ICPPASTNamespaceDefinition namespace) {
        if(namespace.getName().toString().length() == 0){

            return "anonymous_namespace";
        }else{
            return namespace.getName().toString();
        }
    }


    /**
     * determine whether Other elements besides functions are Changed
     * @param oldAstVisitor
     * @param newAstVisitor
     * @return
     */
    public static Boolean whetherOtherChanged(MyASTVisitor oldAstVisitor, MyASTVisitor newAstVisitor) {
        //function style macros
        List<IASTPreprocessorFunctionStyleMacroDefinition> oldFuncMacros = oldAstVisitor.getFunctionMacros();
        List<IASTPreprocessorFunctionStyleMacroDefinition> newFuncMacros = newAstVisitor.getFunctionMacros();
        if(compareMacros(oldFuncMacros, newFuncMacros)){
            System.out.println("Function style macro changed.");
            return true;
        }
        //nodes
        //including class, global variable and other cases
        List<IASTNode> oldNodes = oldAstVisitor.getNodes();
        List<IASTNode> newNodes = newAstVisitor.getNodes();
        if (compareNodes(oldNodes, newNodes)){
            System.out.println("Other node changed.");
            return true;
        }

        //namespaces
        List<ICPPASTNamespaceDefinition> oldNamespaces = oldAstVisitor.getNamesps();
        List<ICPPASTNamespaceDefinition> newNamespaces = newAstVisitor.getNamesps();
        if(compareNamespaces(oldNamespaces, newNamespaces)){
            System.out.println("Namespace changed.");
            return true;
        }

        return false;
    }




    public static Boolean whetherOtherChanged_cases(MyASTVisitor oldAstVisitor, MyASTVisitor newAstVisitor) {
        //otherDecls
        List<IASTDeclaration> oldOtherDecls = oldAstVisitor.getOtherDecls();
        List<IASTDeclaration> newOtherDecls = newAstVisitor.getOtherDecls();
        if(compareDeclarations(oldOtherDecls,newOtherDecls)){
            System.out.println("Global variable changed.");
            return true;
        }
        //field members
        List<IASTDeclaration> oldFieldMembers = oldAstVisitor.getFields();
        List<IASTDeclaration> newFieldMembers = newAstVisitor.getFields();
        if(compareDeclarations(oldFieldMembers,newFieldMembers)){
            System.out.println("Field Member changed.");
            return true;
        }
        //function style macros
        List<IASTPreprocessorFunctionStyleMacroDefinition> oldFuncMacros = oldAstVisitor.getFunctionMacros();
        List<IASTPreprocessorFunctionStyleMacroDefinition> newFuncMacros = newAstVisitor.getFunctionMacros();
        if(compareMacros(oldFuncMacros, newFuncMacros)){
            System.out.println("Function style macro changed.");
            return true;
        }
        return false;
    }


}
