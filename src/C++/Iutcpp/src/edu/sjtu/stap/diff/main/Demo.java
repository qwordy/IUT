package edu.sjtu.stap.diff.main;

import java.io.File;
import java.io.IOException;

import edu.sjtu.stap.diff.ast.ASTTranslationUnitCore;
import edu.sjtu.stap.diff.ast.MultiVisitor;
import edu.sjtu.stap.diff.ast.MyASTVisitor;
import edu.sjtu.stap.diff.ast.MyCPPASTVisitor;
import edu.sjtu.stap.diff.diff.ASTDifferOld;
import edu.sjtu.stap.diff.diff.DiffUtils;
import edu.sjtu.stap.diff.diff.DifferResult;
import edu.sjtu.stap.diff.diff.FileDiffer;
import org.eclipse.cdt.core.dom.ast.*;
import org.eclipse.cdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.cdt.core.parser.ParserLanguage;



public class Demo {

	static int FLAG = 4;// only for test
	
	public static void main(String[] args) {
		
		if(FLAG == 3){//diff dir
			FileDiffer differ = new FileDiffer();
			String oldDir = "testcode/project.Old";
			String newDir = "testcode/project.New";
			
//			 oldDir = "/home/weizhaoy/Desktop/cctz-old";
//			 newDir = "/home/weizhaoy/Desktop/cctz-new";

			if(args.length != 0){
				oldDir = args[0];
				newDir = args[1];
			}
			System.out.println("Old: "+oldDir+"------------New: "+newDir);
//			String result = fileDiffer.diff(oldDir, newDir);
			
			DifferResult result = null;
			try {
				result = differ.diff(oldDir, newDir);
				System.out.println("**************************************");
				System.out.println("*                              DifferResult                               *");
				System.out.println("**************************************");
				System.out.println(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Utils.writeStringToFile(result.toString(), new File("result/DifferResult.txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(FLAG == 2){//diff file
//			String oldPath = "testcode/main.cpp"; ///home/weizhaoy/Desktop/cctz-old/src
//			String newPath="testcode/mainNew.cpp";///home/weizhaoy/Desktop/cctz-new/src
			
			String oldPath = "testcode/main.cpp"; ///home/weizhaoy/Desktop/cctz-old/src
			String newPath="testcode/mainNew.cpp";///home/weizhaoy/Desktop/cctz-new/src
			
			if(args.length != 0){
				oldPath = args[0];
				newPath = args[1];
			}
			ASTDifferOld astDiffer = new ASTDifferOld();
			String result =  astDiffer.diff(oldPath, newPath, false);
			System.out.println(result);
		}
		
		
		if(FLAG == 1){//print dom
			String filePath = "testcode/time_zone_libc.cc";
//			String filePath = "testcode/cctz_v1_test.cc";
			if (args.length != 0)
				filePath = args[0];
			/*
			File cppfile = new File(filePath);
			System.out.println(cppfile.exists());
			*/
			ASTTranslationUnitCore astTranslationUnitCore = new ASTTranslationUnitCore();
			IASTTranslationUnit astTranslationUnit = astTranslationUnitCore.parseFile(filePath, ParserLanguage.CPP, false, false);
			System.out.println(astTranslationUnit.getFilePath());
			
//			MyASTVisitor astVisitor = new MyASTVisitor();
			
			MultiVisitor astVisitor = new MultiVisitor();
//			astVisitor.shouldVisitDeclSpecifiers = true;
//			astVisitor.shouldVisitDeclarators = true;
////			astVisitor.shouldVisitExpressions = true;
//			astVisitor.shouldVisitAttributes = true;
//			astVisitor.shouldVisitImplicitNames = true;
//			astVisitor.shouldVisitInitializers = true;
//         astVisitor.shouldVisitStatements = true;
//			astVisitor.shouldVisitTokens = true;
//			astVisitor.shouldVisitParameterDeclarations = true;
			astVisitor.shouldVisitDeclarations = true;
//			astVisitor.shouldVisitAmbiguousNodes = true;
			astTranslationUnit.accept(astVisitor);
			
			//Comments
			
			/*
			IASTComment[] comments = astTranslationUnit.getComments();
			for( IASTComment comm : comments){
				System.out.println("IASTComment: "+comm.toString());
				comm.setParent(null);
			}
			
			IASTComment[] c = astTranslationUnit.getComments();
			for( IASTComment comm : c){
				System.out.println(" c IASTComment: "+comm.toString());
				
			}
			*/
			
			IASTPreprocessorMacroDefinition[] macroDefinitions = astTranslationUnit.getMacroDefinitions();
			for (IASTPreprocessorMacroDefinition macroDefinition : macroDefinitions){
//				System.out.println(macroDefinition);
				if(macroDefinition instanceof IASTPreprocessorFunctionStyleMacroDefinition){
					IASTPreprocessorFunctionStyleMacroDefinition fucker = (IASTPreprocessorFunctionStyleMacroDefinition) macroDefinition;
					IASTFunctionStyleMacroParameter[] macroParameters = fucker.getParameters();
					for(IASTFunctionStyleMacroParameter mp : macroParameters){
//						System.out.println("macroprp: "+mp.getParameter());
					}
				}
//				System.out.println(macroDefinition.getName() );			
//				System.out.println(macroDefinition.getExpansion());
			}
			
		
			IASTPreprocessorMacroExpansion[] macroExpansions = astTranslationUnit.getMacroExpansions();
			for(IASTPreprocessorMacroExpansion macroExpansion : macroExpansions){
//				System.out.println(macroExpansion.getRawSignature());
//				System.out.println(macroExpansion.getMacroReference());
//				System.out.println(macroExpansion.getMacroDefinition());
			}
			/*
			IASTPreprocessorStatement[] preprocessorStatements = astTranslationUnit.getAllPreprocessorStatements();
			for (IASTPreprocessorStatement ps : preprocessorStatements){
				try {
					System.out.println(ps.getSyntax().getNext().getCharImage());
				} catch (ExpansionOverlapsBoundaryException e) {
					
					e.printStackTrace();
				}
			
			}
			*/
			
			
			
			
			/*
			for (IASTDeclaration declaration : astVisitor.getDecls()){
				System.out.println("Rawsig: "+declaration.getRawSignature());
			}
			*/
		}

		if(FLAG == 4){//test other elements
//			String filePath = "testcode/time_zone_libc.cc";
//			String filePath = "testcode/cctz_v1_test.cc";
			String filePath = "testcode/mainNew.cpp";
			if (args.length != 0)
				filePath = args[0];
			/*
			File cppfile = new File(filePath);
			System.out.println(cppfile.exists());
			*/
			ASTTranslationUnitCore astTranslationUnitCore = new ASTTranslationUnitCore();
			IASTTranslationUnit astTranslationUnit = astTranslationUnitCore.parseFile(filePath, ParserLanguage.CPP, false, false);
			System.out.println(astTranslationUnit.getFilePath());

			MyCPPASTVisitor myCPPASTVisitor = new MyCPPASTVisitor();
			myCPPASTVisitor.shouldVisitDeclarations = true;

			MyASTVisitor visitor = new MyASTVisitor();

			astTranslationUnit.accept(visitor);
			astTranslationUnit.accept(myCPPASTVisitor);

			for(IASTDeclaration declaration : visitor.getDecls()){
//				System.out.println("visitor: "+declaration.getRawSignature());
				System.out.println(DiffUtils.getDeclarationStr(declaration));
			}
		}
		
		
		

	}

	
}
