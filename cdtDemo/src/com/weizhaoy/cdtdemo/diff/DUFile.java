package com.weizhaoy.cdtdemo.diff;

public class DUFile extends DifferUnit{

	protected String filename;
	
	public DUFile (String filename){
		this.filename = filename;
	}
	
	public static class Added extends DUFile {

		public Added(String filename) {
			super(filename);
		}
		
		public String toString(){
			return "+ " + filename;
		}
		
	}
	
	public static class Deleted extends DUFile {

		public Deleted(String filename) {
			super(filename);
		}
		
		public String toString(){
			return "- " + filename;
		}
		
	}
	
	public static class Modified extends DUFile {

		ASTDiffer astDiffer;
		public Modified(String filename, ASTDiffer astDiffer) {
			super(filename);
			this.astDiffer = astDiffer;
		}
		
		public String toString(){
			StringBuilder sb = new StringBuilder();
			//TODO
			
			return sb.toString();
		}
		
	}
}
