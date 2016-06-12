package edu.sjtu.stap.diff.diff;

import edu.sjtu.stap.inst.ThreadInst;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;

import java.util.ArrayList;
import java.util.List;

public class DifferResult {

	List<DUFile.Added> fileAdded;
	List<DUFile.Deleted> fileDeleted;
	List<DUFile.Modified> fileModified;
	
	public DifferResult(){
		fileAdded = new ArrayList<>();
		fileDeleted = new ArrayList<>();
		fileModified = new ArrayList<>();
	}
	
	public void appendfileAdded (String file){
		fileAdded.add(new DUFile.Added(file));
	}
	
	public void appendfileDeleted (String file){
		fileDeleted.add(new DUFile.Deleted(file));
	}
	
	public void appendfileModified (String file, ASTDiffer differ){
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
}
