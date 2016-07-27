package edu.sjtu.stap.diff.diff;

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

public class CPPFileFilter implements FileFilter{


	/**
	 * decide which kind of source file to diff
	 * feel free to add cases of extension names of source files
	 * @param file
	 * @return
	 */
	@Override
	public boolean accept(File file) {
		if( (file.isFile() )){
			if(file.getName().endsWith(".cpp")){
				return true;
			}else if(file.getName().endsWith(".c")){
				return true;
			}else if(file.getName().endsWith(".h")){
				return true;
			}else if(file.getName().endsWith(".hpp")){
				return true;
			}else if(file.getName().endsWith(".cc")){
				return true;
			}
		}
		return false;
	}
		

}
