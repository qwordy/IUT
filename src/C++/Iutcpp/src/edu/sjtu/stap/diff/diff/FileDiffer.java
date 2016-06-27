package edu.sjtu.stap.diff.diff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileDiffer {

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

		File oldFolder = new File(oldDir + base);
		File newFolder = new File(newDir + base);


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

					dirMap.put(file.getName(), file);// dir getName()
				}
			}

			for(File file : oldFolder.listFiles()){
				if(cppFileFilter.accept(file)){
					oldFiles.add(file);
				}else if(file.isDirectory()){
					File dirInNew = dirMap.get(file.getName());
					if (dirInNew == null){//dir deleted

						System.out.println("\nDir Deleted: "+file.getAbsolutePath());
						//differResult.appendfileDeleted(file.getName());//to be changed
						differResult.appendfileDeleted(file.getAbsolutePath());
					}else{
//						System.out.println("filename: "+oldDir+file.getName());
						//Fixed: BUG: oldDir should be oldFolder's path, new* too

//diff this dir
						this.diff(oldDir, newDir, base+file.getName()+"/");// keep this "getName()"
						dirMap.remove(file.getName());
					}
				}
			}

			for(File file : dirMap.values()){
				//dir added
				System.out.println("\nDir Added: "+file.getAbsolutePath());
				//differResult.appendfileAdded(file.getName());//to be changed
				differResult.appendfileAdded(file.getAbsolutePath());
			}

			diffFiles(oldFiles, newFiles);

			return differResult;


		}else if(!oldFolder.isDirectory()){

			System.out.println("ERROR:"+oldFolder.getName()+ " is not a DIRECTORY!");
			return null;//TODO: better to raise an exception
		}else{
			System.out.println("ERROR:"+newFolder.getName()+ " is not a DIRECTORY!");
			return null;//TODO: better to raise an exception
		}
	}

	private void diffFiles(List<File> oldFiles, List<File> newFiles) throws IOException {

		HashMap<String, File> newMap = new HashMap<>();

		for(File file : newFiles){
			newMap.put(file.getName(), file);// getName()
		}

		for(File file : oldFiles){
			String filename = file.getName();// getName()
			File fileInNew =  newMap.get(filename);

			if(fileInNew == null){
				//file deleted

				System.out.println("\n\tFile Deleted: "+file.getAbsolutePath());
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
//					ASTDiffer astDiffer = new ASTDiffer(contentOld, contentNew);
					ASTDiffer astDiffer = null;
					try {
						astDiffer = new ASTDiffer(file, fileInNew);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("\n\tFile Modified: "+file.getAbsolutePath());
					//differResult.appendfileModified(filename, astDiffer); // to be changed
					differResult.appendfileModified(file.getAbsolutePath(), astDiffer);// to
				}
//				}

				newMap.remove(filename);
			}
		}

		for(String filename : newMap.keySet()){
			//file added
			System.out.println("\n\tFile Added: "+newMap.get(filename).getAbsolutePath());
			//differResult.appendfileAdded(filename);// to be changed
			differResult.appendfileAdded(newMap.get(filename).getAbsolutePath());
		}

	}


}