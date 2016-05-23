package com.weizhaoy.cdtdemo.diff;

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

public class CPPFileFilter implements FileFilter{

	

	//TODO: obtain from config file
	//property.get
	@Override
	public boolean accept(File file) {
		if( (file.isFile() )){
			if(file.getName().endsWith(".cpp")){
				return true;
			}else if(file.getName().endsWith(".c")){
				return true;
			}else if(file.getName().endsWith(".h")){
				return true;
			}else if(file.getName().endsWith(".cc")){
				return true;
			}
		}
//		Properties property=new Properties("");
		return false;
	}
		

}
