package edu.sjtu.stap.diff.diff;

import edu.sjtu.stap.iut.ThreadInst;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;

import java.util.ArrayList;
import java.util.List;

public class DifferResult implements  IDifferResult{

	List<DUFile.Added> fileAdded;
	List<DUFile.Deleted> fileDeleted;
	List<DUFile.Modified> fileModified;


	private Boolean otherChanged = null;
	
	public DifferResult(){
		fileAdded = new ArrayList<>();
		fileDeleted = new ArrayList<>();
		fileModified = new ArrayList<>();
	}
	
	public void appendFileAdded(String file){
		fileAdded.add(new DUFile.Added(file));
	}
	
	public void appendFileDeleted(String file){
		fileDeleted.add(new DUFile.Deleted(file));
	}
	
	public void appendFileModified(String file, ASTDiffer differ){
		fileModified.add(new DUFile.Modified(file, differ));
	}


	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		for(DUFile.Added f : fileAdded){
			sb.append(f.toString()).append('\n');
		}
		
		for(DUFile.Deleted f : fileDeleted){
			sb.append(f.toString()).append('\n');
		}
		
		for(DUFile.Modified f : fileModified){
			sb.append(f.toString()).append('\n');
		}
		return sb.toString();
	}

	@Override
	public List<String > getModifiedFunctions(){

		ThreadInst threadInst = new ThreadInst();

		List<String> result  = new ArrayList<>();
		IASTFunctionDefinition func = null;
		List<DUFunction.Modified> funcs = new ArrayList<>();
		String filecontent = null;
		if (!fileModified.isEmpty()){
			for(DUFile.Modified modfile : fileModified){
				funcs  = modfile.astDiffer.getFunctionModified();
				for(DUFunction.Modified e : funcs){
					func = e.function.getOrigin();
					filecontent = modfile.getFileContent();
					try {
						result.add(threadInst.funcName(func, filecontent));//add modified function
					} catch (Exception e1) {
						e1.printStackTrace();
					}


				}
			}
		}

		return result;
	}

	public  void setOtherChanged(Boolean otherChanged){

			this.otherChanged = otherChanged;

	}

	private Boolean ifOtherChanged(){
		if(otherChanged == null){
//			System.out.println("DifferResult.otherChanged == null. Setting to false.");
			otherChanged = false;
		}

		return otherChanged;
	}


	@Override
	public Boolean ifChooseAll() {
		if(fileAdded.size() + fileDeleted.size() != 0){
			return true;
		}
		if(ifOtherChanged()){
			return true;
		}
		return false;
	}
}
