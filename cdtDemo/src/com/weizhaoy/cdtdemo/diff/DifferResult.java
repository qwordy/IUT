package com.weizhaoy.cdtdemo.diff;

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
}
