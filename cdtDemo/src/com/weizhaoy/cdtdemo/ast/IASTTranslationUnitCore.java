package com.weizhaoy.cdtdemo.ast;

import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.parser.ParserLanguage;

public interface IASTTranslationUnitCore {

	public IASTTranslationUnit parseFile(
			String file,
			ParserLanguage parserLanguage,
			boolean useGNUExtensions,
			boolean skipTrivialInitializers
			);
	
	
	public IASTTranslationUnit parseCode(
			String code,
			ParserLanguage parserLanguage,
			boolean useGNUExtensions,
			boolean skipTrivialInitializers 
			);
}
