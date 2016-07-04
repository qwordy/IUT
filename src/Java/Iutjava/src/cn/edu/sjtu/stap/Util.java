package cn.edu.sjtu.stap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Util {
	
	public static class JavaFileFilter implements FilenameFilter {
		public boolean accept(File file, String name) {
			//filter all the "*.java" files 
			return name != null && name.endsWith(".java");
		}		
	}
	
	public static String fileToString (File file) throws IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader(file.getCanonicalPath()));
		String line;
		StringBuilder sb = new StringBuilder();
		
		while ((line = reader.readLine()) != null) {
			sb.append(line).append('\n');
		}
		
		reader.close();
		return sb.toString();
	}
	
	public static void writeStringToFile (String content, File file) throws IOException {
		PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
		writer.print(content);
		writer.close();
		
	}
	
	public static String getMethodSignature (MethodDeclaration methodDecl) {
		StringBuilder sb = new StringBuilder();

		for (Object modifier : methodDecl.modifiers()) {
			sb.append(modifier).append(' ');
		}
		
		if (!methodDecl.isConstructor()) {
			sb.append(methodDecl.getReturnType2()).append(' ');
		}
		sb.append(methodDecl.getName()).append(" (");
		
		List<?> parameters = methodDecl.parameters();
		for (int i = 0; i < parameters.size(); i++) {
			Object parameter = parameters.get(i);
			sb.append(parameter);
			if (i != parameters.size() - 1) {
				sb.append(", ");
			}
		}
		
		if (methodDecl.isVarargs()) {
			sb.append(",...");
		}
		
		sb.append(')');
		
		List<?> exceptions =  methodDecl.thrownExceptions();
		if (!exceptions.isEmpty()) {
			sb.append(" throws ");
			
			for (int i = 0; i < exceptions.size(); i++) {
				Object exception = exceptions.get(i);		
				sb.append(exception);
				if (i != exceptions.size() - 1) {
					sb.append(", ");
				}
			}
		}
		
		// sb.append(this.methodDecl.resolveBinding().toString());
		return sb.toString();
	}
}
