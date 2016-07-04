package cn.edu.sjtu.stap.inst;

import java.io.File;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cn.edu.sjtu.stap.Util;

public class Instrument {
	
	private File output, input;
	
	private void instFile (File file) throws IOException {
		// System.out.println("Instrumenting " + file.getName());
		String source = Util.fileToString(file);
		// System.out.println(source);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		// parser.setKind(ASTParser.);
		parser.setSource(source.toCharArray());
		
		CompilationUnit result = (CompilationUnit)parser.createAST(null);
		// System.out.println(result.getPackage());
		String packageName = "";
		if (result.getPackage() != null) {
			packageName = result.getPackage().getName().toString();
		}
		
		/*
		for (Object e : result.types() ) {
			TypeDeclaration typeDecl = (TypeDeclaration)e;
			System.out.println(typeDecl.toString());
		}
		*/
		result.accept(new InstrumentVisitor(packageName));
		//System.out.println("------------");
		// System.out.println(result.toString());
		String child = file.getCanonicalPath().substring(
				input.getCanonicalPath().length());
		File newFile = new File(output.getCanonicalPath() + child);
		Util.writeStringToFile(result.toString(), newFile);
		
	}
	
	private void instDir (File dir) throws Exception {
		for (File file : dir.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".java")) {
				try {
					instFile(file);
				} catch (IOException e) {
					System.err.println("ERROR when processing " + file 
							+ "(" + e.getMessage() + ")");
				}
			} else if (file.isDirectory()) {
				String child = file.getCanonicalPath().substring(
						input.getCanonicalPath().length());
				new File(output.getCanonicalPath() + child).mkdir();
				instDir(file);
			}
		}
	}
	
	public Instrument (String input, String output) throws Exception {
		File inputDir = new File(input);
		if (output == null) {
			output = input + ".out";
		}
		File outputDir = new File(output);
		// System.out.println("output dir" + output);
		if (outputDir.exists()) {
			throw new Exception("directory " + output + " exists!");
		} else if (!inputDir.isDirectory()) {
			throw new Exception(input + " is not a directory!");
		} else {
			String outputDirPath = outputDir.getCanonicalPath();
			// System.out.println(outputDirPath);
			if (outputDirPath.startsWith(inputDir.getCanonicalPath() + "/")) {
				throw new Exception("output directory can not be the sub-directory of the input!");
			}
			this.input = inputDir;
			this.output = outputDir;
			this.output.mkdir();
			instDir(inputDir);
		}
	}
}
