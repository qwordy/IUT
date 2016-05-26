package com.weizhaoy.cdtdemo.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Utils {

	
	public static void writeStringToFile (String content, File file) throws IOException {
		PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
		writer.print(content);
		writer.close();
		
	}
}
