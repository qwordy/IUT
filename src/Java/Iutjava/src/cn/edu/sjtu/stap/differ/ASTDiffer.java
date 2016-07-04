package cn.edu.sjtu.stap.differ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTMatcher;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.internal.core.SetContainerOperation;

import cn.edu.sjtu.stap.ast.Parser;
import cn.edu.sjtu.stap.ast.XMethodDeclaration;

public class ASTDiffer {
	
	List<DUMethod.Added> methodAdded;
	List<DUMethod.Deleted> methodDeleted;
	List<DUMethod.Modified> methodModified;

	public List<DUMethod.Added> getMethodAdded() {
		return methodAdded;
	}

	public List<DUMethod.Deleted> getMethodDeleted() {
		return methodDeleted;
	}

	public List<DUMethod.Modified> getMethodModified() {
		return methodModified;
	}
	
	public boolean isModified () {
		return this.methodAdded.size() + this.methodDeleted.size() + this.methodModified.size() != 0;
	}

	public ASTDiffer (String oldContent, String newContent) throws Exception {
		Parser parserOld, parserNew;
		parserOld = new Parser(oldContent);
		parserNew = new Parser(newContent);
		
		List<XMethodDeclaration> oldMethods = parserOld.getCanonicalMethodDecls();
		List<XMethodDeclaration> newMethods = parserNew.getCanonicalMethodDecls();
		
		HashMap<String, XMethodDeclaration> mapNew;
		mapNew = new HashMap<String, XMethodDeclaration>();
		
		methodAdded = new ArrayList<DUMethod.Added>();
		methodDeleted = new ArrayList<DUMethod.Deleted>();
		methodModified = new ArrayList<DUMethod.Modified>();
		
		for (XMethodDeclaration method : newMethods) {
			mapNew.put(method.getId(), method);
		}
		
		for (XMethodDeclaration method : oldMethods) {
			XMethodDeclaration methodInNew = mapNew.get(method.getId());
			if (methodInNew != null) {
				ASTMatcher matcher = new ASTMatcher(false);
				if (!matcher.match(methodInNew.getOrigin(), method.getOrigin())) {
					methodModified.add(new DUMethod.Modified(method));
				}
			} else {
				// deleted
				methodDeleted.add(new DUMethod.Deleted(method));
			}
			mapNew.remove(method.getId());
		}
	
		for (XMethodDeclaration method : mapNew.values()) {
			methodAdded.add(new DUMethod.Added(method));
		}
	}
	
	
}
