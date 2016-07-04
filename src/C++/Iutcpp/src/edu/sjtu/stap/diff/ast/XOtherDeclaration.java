package edu.sjtu.stap.diff.ast;

import org.eclipse.cdt.core.dom.ast.IASTDeclaration;

/**
 * Created by weizhaoy on 16/6/29.
 * for other declarations besides functions
 * not in use yet on 16/6/29
 */
public class XOtherDeclaration {
    public static final int USINGDECL = 0;
    public static final int CLASSDECL = 1;
    public static final int SIMPLEDECL = 2;
    int TYPE;
    IASTDeclaration origin;
}
