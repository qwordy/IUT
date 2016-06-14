package edu.sjtu.stap.diff.diff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileDiffer {
	//TODO: add a field to denote current File/Dir being compaired -- easy to record File/Dir name
	private CPPFileFilter cppFileFilter;
	private DifferResult differResult;

	public FileDiffer(){
		cppFileFilter = new CPPFileFilter();
		differResult = new DifferResult();
	}


	public DifferResult diff(String oldDir, String newDir) throws IOException{
		if(!oldDir.endsWith("/")){
			oldDir += "/";
		}
		if(!newDir.endsWith("/")){
			newDir += "/";
		}
		return this.diff(oldDir, newDir, "");
	}

	public DifferResult diff(String oldDir, String newDir, String base) throws IOException{
		System.out.println("FileDiffer.diff( "+oldDir+", "+newDir+", "+base+")");
//		StringBuilder sb = new StringBuilder();
		File oldFolder = new File(oldDir + base);
		File newFolder = new File(newDir + base);
		//TODO: String basePath = oldDir + base;
		String basePath = oldDir + base;

		if(oldFolder.isDirectory() && newFolder.isDirectory()){
//			System.out.println("good");
			List<File> oldFiles = new ArrayList<>();
			List<File> newFiles = new ArrayList<>();

			HashMap<String, File> dirMap = new HashMap<>();

			//compare files
			for(File file : newFolder.listFiles()){
				if(cppFileFilter.accept(file)){
					newFiles.add(file);
				}else if(file.isDirectory()){
//					System.out.println("basePath+file.getName() == file.getAbsolutePath():\t"+(basePath+file.getName() == file.getAbsolutePath()));
//					System.out.println("basePath+file.getName():\t"+basePath+file.getName()
//							+"\n"+"file.getAbsolutePath():\t"+file.getAbsolutePath());
					dirMap.put(file.getName(), file);//TODO: dir getName()
				}
			}

			for(File file : oldFolder.listFiles()){
				if(cppFileFilter.accept(file)){
					oldFiles.add(file);
				}else if(file.isDirectory()){
					File dirInNew = dirMap.get(file.getName());
					if (dirInNew == null){//dir deleted
//						sb.append("\nDir Deleted: "+file.getName());
						System.out.println("\nDir Deleted: "+file.getName());//TODO
						//differResult.appendfileDeleted(file.getName());//to be changed
						differResult.appendfileDeleted(file.getAbsolutePath());
					}else{
//						System.out.println("filename: "+oldDir+file.getName());
						//Fixed: BUG: oldDir should be oldFolder's path, new* too
//						sb.append(this.diff(oldDir, newDir, base+file.getName()+"/"));//diff this dir
						this.diff(oldDir, newDir, base+file.getName()+"/");//TODO: keep this "getName()"
						dirMap.remove(file.getName());
					}
				}
			}

			for(File file : dirMap.values()){
//				sb.append("\nDir Added: "+file.getName());
				//dir added
				System.out.println("\nDir Added: "+file.getName());//TODO
				//differResult.appendfileAdded(file.getName());//to be changed
				differResult.appendfileAdded(file.getAbsolutePath());
			}

			diffFiles(oldFiles, newFiles);

			return differResult;
			/*
			String resultOfFileDiffInDir;
			try {
				resultOfFileDiffInDir = this.diffFiles(oldFiles, newFiles);
				sb.append("\nIN Dir: "+oldDir+base+"\n"+resultOfFileDiffInDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/

		}else if(!oldFolder.isDirectory()){
//			sb.append("\nERROR: At least one of the paths is not a DIRECTORY! "+ oldFolder+" or "+newFolder);
			System.out.println("ERROR:"+oldFolder.getName()+ " is not a DIRECTORY!");
			return null;//TODO: better to raise an exception
		}else{
			System.out.println("ERROR:"+newFolder.getName()+ " is not a DIRECTORY!");
			return null;//TODO: better to raise an exception
		}
//		return sb.toString();
	}

	private void diffFiles(List<File> oldFiles, List<File> newFiles) throws IOException {
//		StringBuilder sb = new StringBuilder();
		HashMap<String, File> newMap = new HashMap<>();

		for(File file : newFiles){
			newMap.put(file.getName(), file);//TODO: getName()
		}

		for(File file : oldFiles){
			String filename = file.getName();//TODO: getName()
			File fileInNew =  newMap.get(filename);

			if(fileInNew == null){
				//file deleted
//				sb.append("/n/tFile Deleted: "+filename);
				System.out.println("\n\tFile Deleted: "+filename);//TODO
				//differResult.appendfileDeleted(filename);//to be changed
				differResult.appendfileDeleted(file.getAbsolutePath());
			}else{
//				if(file.lastModified() != fileInNew.lastModified()
//						&& file.hashCode() != fileInNew.hashCode()){
				//TODO: compare in stream
				String contentOld = FileUtils.readFileToString(file);
				String contentNew = FileUtils.readFileToString(fileInNew);
				//TODO		contentOld.replaceAll("*", "");
				if(!contentOld.equals(contentNew)){
					ASTDiffer astDiffer = new ASTDiffer(contentOld, contentNew);
//						sb.append("\nIn File: "+filename+"\n"+astDiffer.diff(contentOld, contentNew, true));
					System.out.println("\n\tFile Modified: "+filename);//TODO
					//differResult.appendfileModified(filename, astDiffer); // to be changed
					differResult.appendfileModified(file.getAbsolutePath(), astDiffer);// to
				}
//				}

				newMap.remove(filename);
			}
		}

		for(String filename : newMap.keySet()){
			//file added
//			sb.append("\nFile Added: "+filename);
			System.out.println("\n\tFile Added: "+filename);//TODO
			//differResult.appendfileAdded(filename);// to be changed
			differResult.appendfileAdded(newMap.get(filename).getAbsolutePath());
		}

//		return sb.toString();
	}


}