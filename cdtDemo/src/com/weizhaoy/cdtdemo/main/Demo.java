package com.weizhaoy.cdtdemo.main;

import java.io.File;

public class Demo {

	public static void main(String[] args) {
		String filePath = "testcode/main.cpp";
		if (args.length != 0)
			filePath = args[0];
		File cppfile = new File(filePath);
		System.out.println(cppfile.exists());

	}

}
