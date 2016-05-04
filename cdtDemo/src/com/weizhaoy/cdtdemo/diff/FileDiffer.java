package com.weizhaoy.cdtdemo.diff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileDiffer {
	public FileDiffer(){

	}

	public String diff(String oldDir, String newDir){
		if(!oldDir.endsWith("/")){
			oldDir += "/";
		}
		if(!newDir.endsWith("/")){
			newDir += "/";
		}
		return this.diff(oldDir, newDir, "");
	}

	public String diff(String oldDir, String newDir, String base){
		StringBuilder sb = new StringBuilder();
		File oldFolder = new File(oldDir + base);
		File newFolder = new File(newDir + base);
		CPPFileFilter cppFilter = new CPPFileFilter();

		if(oldFolder.isDirectory() && newFolder.isDirectory()){
			List<File> oldFiles = new ArrayList<>();
			List<File> newFiles = new ArrayList<>();

			HashMap<String, File> dirMap = new HashMap<>();

			for(File file : newFolder.listFiles()){
				if(cppFilter.accept(file)){
					newFiles.add(file);
				}else if(file.isDirectory()){
					dirMap.put(file.getName(), file);
				}
			}

			for(File file : oldFolder.listFiles()){
				if(cppFilter.accept(file)){
					oldFiles.add(file);
				}else if(file.isDirectory()){
					File dirInNew = dirMap.get(file.getName());
					if (dirInNew == null){//dir deleted
						sb.append("\nDir Deleted: "+file.getName());
					}else{
//						System.out.println("filename: "+oldDir+file.getName());
						sb.append(this.diff(oldDir, newDir, file.getName()+"/"));//diff this dir
						dirMap.remove(file.getName());
					}
				}
			}

			for(File file : dirMap.values()){
				sb.append("\nDir Added: "+file.getName());//dir added
			}

			String resultOfFileDiffInDir;
			try {
				resultOfFileDiffInDir = this.diffFiles(oldFiles, newFiles);
				sb.append("\nIN Dir: "+oldDir+base+"\n"+resultOfFileDiffInDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sb.toString();
	}

	private String diffFiles(List<File> oldFiles, List<File> newFiles) throws IOException {
		StringBuilder sb = new StringBuilder();
		HashMap<String, File> newMap = new HashMap<>();
		for(File file : newFiles){
			newMap.put(file.getName(), file);
		}

		for(File file : oldFiles){
			String filename = file.getName();
			File fileInNew =  newMap.get(filename);

			if(fileInNew == null){
				//file deleted
				sb.append("/n/tFile Deleted: "+filename);
			}else{
				if(file.lastModified() != fileInNew.lastModified()
						&& file.hashCode() != fileInNew.hashCode()){
					String contentOld = FileUtils.readFileToString(file);
					String contentNew = FileUtils.readFileToString(fileInNew);

					if(!contentOld.equals(contentNew)){
						ASTDiffer astDiffer = new ASTDiffer();
						sb.append("\nIn File: "+filename+"\n"+astDiffer.diff(contentOld, contentNew, true));
					}
				}

				newMap.remove(filename);
			}
		}

		for(String filename : newMap.keySet()){
			//file added
			sb.append("\nFile Added: "+filename);
		}

		return sb.toString();
	}
}
