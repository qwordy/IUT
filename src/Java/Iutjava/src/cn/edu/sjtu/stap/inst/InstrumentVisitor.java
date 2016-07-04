package cn.edu.sjtu.stap.inst;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cn.edu.sjtu.stap.Config;
import cn.edu.sjtu.stap.Util;

public class InstrumentVisitor extends ASTVisitor {
	
	ASTParser parser;
	String currentType, packageName;
	
	public InstrumentVisitor (String packageName) {
		super();
		currentType = "";
		this.packageName = packageName;
		//parser.setSource(source.toCharArray()););
	}
	
	public boolean visit (TypeDeclaration node) {
		// String name = node.getName().toString();
		// System.out.println("Type decl");
		// System.out.println(node);
		currentType = node.getName().toString();
		return true;
	}
	
	public boolean visit (MethodDeclaration node) {
		Block body = node.getBody();
		AST ast = node.getAST();
		
		StringBuilder sb = new StringBuilder();
		sb.append(Config.instPrefix);
		sb.append(this.packageName).append(Config.instSeperator);
		sb.append(this.currentType).append(Config.instSeperator);
		sb.append(Util.getMethodSignature(node));

		
		String logContent = sb.toString();

		try {
			MethodInvocation mi = ast.newMethodInvocation();
			StringLiteral info = ast.newStringLiteral();
			info.setLiteralValue(logContent);
			FieldAccess system = ast.newFieldAccess();
			system.setName(ast.newSimpleName("out"));
			system.setExpression(ast.newSimpleName("System"));
			
			mi.setName(ast.newSimpleName("println"));
			mi.setExpression(system);
			mi.arguments().add(info);
			// mi.setExpression(info);
			ExpressionStatement es = ast.newExpressionStatement(mi);
			
			if (body != null) {
				// System.out.println(logContent);
				// System.out.println(node.toString());
				if (node.isConstructor()) {
					body.statements().add(es);
				} else {
					body.statements().add(0, es);
				}
			}
		//ast.new
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// System.out.println(node);
		return true;
	}
}
