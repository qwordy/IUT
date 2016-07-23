package edu.sjtu.stap.diff.ast;

import org.eclipse.cdt.core.dom.ast.ASTGenericVisitor;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizhaoy on 16/7/23.
 */
public class MyASTGenericVisitor extends ASTGenericVisitor {

    List<IASTNode> nodes = new ArrayList<IASTNode>();

    public MyASTGenericVisitor(boolean visitNodes) {
        super(visitNodes);
    }

    @Override
    protected int genericVisit(IASTNode node) {
        if( (!(node instanceof IASTTranslationUnit)) && node.toString().contains("org.eclipse.cdt") ){
            if(node.getParent() != null && (node.getParent() instanceof IASTTranslationUnit)){
//                (!nodes.contains(node.getParent()))
                System.out.println(node.getFileLocation().getStartingLineNumber()+" "+node.toString()+" :"+node.getRawSignature());
//                System.out.println("Parent: "+node.getParent());

                nodes.add(node);
            }

        }

        return super.genericVisit(node);
    }
}
