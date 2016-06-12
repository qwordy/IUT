package edu.sjtu.stap.diff.diff;

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
			sb.append("* ").append(filename);
			
			if(astDiffer.isModified()){
				sb.append('\n');
			}
			
			for(DUFunction.Added e : astDiffer.getFunctionAdded()){
				sb.append('\t').append(e).append('\n');
			}
			
			for(DUFunction.Deleted e : astDiffer.getFunctionDeleted()){
				sb.append('\t').append(e).append('\n');
			}
			
			for(DUFunction.Modified e : astDiffer.getFunctionModified()){
				sb.append('\t').append(e).append('\n');
			}
			
			return sb.toString();
		}

		public String getFileContent() {
			return DiffUtils.getFileContent(filename); //FIXME filename maybe invalid
		}
	}
}
