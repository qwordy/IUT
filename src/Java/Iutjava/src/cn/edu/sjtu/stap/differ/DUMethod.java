package cn.edu.sjtu.stap.differ;

import cn.edu.sjtu.stap.ast.XMethodDeclaration;

public class DUMethod extends DifferUnit {
	
	protected XMethodDeclaration method;
	public DUMethod (XMethodDeclaration method) {
		this.method = method;
	}

	public static class Added extends DUMethod {
		
		public Added (XMethodDeclaration method) {
			super(method);
		}
		
		public String toString() {
			return "+ " + method;
		}
	}
	
	public static class Deleted extends DUMethod {
		public Deleted (XMethodDeclaration method) {
			super(method);
		}
		
		public String toString() {
			return "- " + method;
		}
	}

	public static class Modified extends DUMethod {
		
		public Modified (XMethodDeclaration method) {
			super(method);
		}
		
		public String toString() {
			return "* " + method;
		}
	
	}
}
