package cn.edu.sjtu.stap.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Parser {
	List<XMethodDeclaration> xMethodDecls;
	
	public Parser(String source) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(source.toCharArray());
		// parser.
		CompilationUnit result = (CompilationUnit)parser.createAST(null);
		// CanonicalVisitor visitor = new CanonicalVisitor();
		// result.accept(visitor);
		
		xMethodDecls = new ArrayList<XMethodDeclaration>();
		
		List<AbstractTypeDeclaration> typeList = result.types();
		
		if (typeList != null) {
			for (AbstractTypeDeclaration typeDecl : typeList) {
				List<BodyDeclaration> bodyDecls = typeDecl.bodyDeclarations();
				
				for (BodyDeclaration bodyDecl : bodyDecls) {
					if (bodyDecl instanceof MethodDeclaration) {
						MethodDeclaration methodDecl = (MethodDeclaration)bodyDecl;
						xMethodDecls.add(new XMethodDeclaration(methodDecl));
						
					}
				}
			}
		}
	}
	
	public List<XMethodDeclaration> getCanonicalMethodDecls () {
		return this.xMethodDecls;
	}
}
