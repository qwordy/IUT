package com.weizhaoy.cdtdemo.diff;

import java.io.File;
import java.io.FileFilter;

public class CPPFileFilter implements FileFilter{

	

	@Override
	public boolean accept(File file) {
		if( (file.isFile() )){
			if(file.getName().endsWith(".cpp")){
				return true;
			}else if(file.getName().endsWith(".c")){
				return true;
			}else if(file.getName().endsWith(".h")){
				return true;
			}
		}
		return false;
	}
		

}
