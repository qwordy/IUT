import org.eclipse.cdt.core.dom.ast.IASTPreprocessorFunctionStyleMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;

public class ASTDiffer {

	public String diff(String oldFile, String newFile){
		/**
		*Focus on:
		*CPPASTFunctionDefinition
		*IASTPreprocessorFunctionStyleMacroDefinition
		*...
		**/
		String result = "";
		return result;
	}
	
	private String diff (IASTTranslationUnit oldAST, IASTTranslationUnit newAST){
		//get all CPPASTFunctionDefinition
		//put into map
		//get all IASTPreprocessorFunctionStyleMacroDefinition
		//put into map
		return "";
	}
}
