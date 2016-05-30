package edu.sjtu.stap.diff.diff;


import edu.sjtu.stap.diff.ast.XFunctionDeclaration;

public class DUFunction extends DifferUnit{

	protected XFunctionDeclaration function;
	
	public DUFunction (XFunctionDeclaration function){
		this.function = function;
	}
	
	public static class Added extends DUFunction{

		public Added(XFunctionDeclaration function) {
			super(function);
		}
		
		@Override
		public String toString() {
			return "+ "+function;
		}
		
		
		
	}
	
	public static class Deleted extends DUFunction{

		public Deleted(XFunctionDeclaration function) {
			super(function);
		}
		
		@Override
		public String toString() {
			return "- "+function;
		}
		
		
		
	}
	
	public static class Modified extends DUFunction{

		public Modified(XFunctionDeclaration function) {
			super(function);
		}
		
		@Override
		public String toString() {
			return "* "+function;
		}
		
		
		
	}
}
