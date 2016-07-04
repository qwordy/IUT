package cn.edu.sjtu.stap.differ;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.sjtu.stap.Util;

/**
 * 
 * @author yejiabin
 *
 */
public class FileDiffer {
	
	private FilenameFilter javaFileFilter, directoryFilter;
	private DifferResult differResult;
	
	public FileDiffer () {
		javaFileFilter = new JavaFileFilter();
		directoryFilter = new DirectoryFilter();
		differResult = new DifferResult();
	}
	
	class JavaFileFilter implements FilenameFilter {
		public boolean accept(File file, String name) {
			//name.endsWith(".java)
			return name != null && name.endsWith(".java");
		}		
	}
	
	class DirectoryFilter implements FilenameFilter {
		public boolean accept(File file, String _) {
			return file.isDirectory();
		}
	}
	
	public DifferResult diff (String oldDir, String newDir) throws Exception {
		return this.diff(oldDir, newDir, "");
	}

	
	public DifferResult diff (String oldDir, String newDir, String base) throws Exception {
		File oldFolder = new File(oldDir + base);
		File newFolder = new File(newDir + base);
		if (oldFolder.isDirectory() && newFolder.isDirectory()) {
			List<File> oldFiles = new ArrayList<File>();
			List<File> newFiles = new ArrayList<File>();
			
			HashMap<String, File> dirMap = new HashMap<String, File>();
			
			for (File file : newFolder.listFiles()) {
				if (file.isFile() && file.getName().endsWith(".java")) {
					newFiles.add(file);
				}
				
				if (file.isDirectory()) {
					dirMap.put(file.getName(), file);
				}
			}
			
			for (File file : oldFolder.listFiles()) {
				if (file.isFile() && file.getName().endsWith(".java")) {
					oldFiles.add(file);
				}
				
				if (file.isDirectory()) {
					File dirInNew = dirMap.get(file.getName());
					if (dirInNew == null) {
						differResult.appendfileDeleted(file.getName());
					} else {
						this.diff(oldDir, newDir, file.getName() + "/");
					}
					dirMap.remove(file.getName());
				}
			}
			
			for (File file : dirMap.values()) {
				//TODO: maybe 
				differResult.appendfileAdded(file.getName());
//				differResult.appendfileDeleted(file.getName());
			}

			
			_compareDir(oldFiles, newFiles);

			return differResult;
		} else if (!oldFolder.isDirectory()) {
			throw new DifferException(oldFolder.getName() + " is not a directory");
		} else {
			throw new DifferException(newFolder.getName() + " is not a directory");
		}
	}
	
	private void _compareDir (List<File> oldFiles, List<File> newFiles) throws Exception {
		// dirOld.list
	
		
		Map<String, File> newMap = new HashMap<String, File>();
		for (File file : newFiles) {
			newMap.put(file.getName(), file);
		}
		
		for (File file : oldFiles) {
			String filename = file.getName();
			File fileInNew = newMap.get(filename);
			if (fileInNew == null) {
				// deleted
				differResult.appendfileDeleted(filename);
			} else {		
				// these files are modified
				if (file.lastModified() != fileInNew.lastModified()
						&& file.hashCode() != fileInNew.hashCode()) {
					
					String contentOld = Util.fileToString(file);
					String contentNew = Util.fileToString(fileInNew);
					
					if (!contentOld.equals(contentNew)) {
						ASTDiffer differ = new ASTDiffer(contentOld, contentNew);
						differResult.appendfileModified(filename, differ);
						
					}
				}
				newMap.remove(filename);
			}
		}
		
		// these files are added
		for (String filename : newMap.keySet()) {
			differResult.appendfileAdded(filename);
		}
		
		
	}
}
