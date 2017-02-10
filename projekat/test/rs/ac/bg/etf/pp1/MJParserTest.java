package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(MJParser.class);
		
		if (args.length < 2) {
			log.error("Invalid arguments! Usage: MJParserTest <source-file> <obj-file> ");
			return;
		}
		
		Reader br = null;
		try {
			File sourceCode = new File(args[0]);
			if (!sourceCode.exists()) {
				log.error("File not found: " + sourceCode.getAbsolutePath() + "");
				return;
			}
			
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());

			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        Tab.dump();
	        
	        if(!p.wasParsingSuccessful) {
	        	log.error("All error messages follow:" + p.allErrorMessages);
	        	log.error("Parsing finished with ERRORS!");
	        } else {
	        	File objFile = new File(args[1]);
	        	if (objFile.exists()) {
	        		objFile.delete();
	        	}
	        	Code.write(new FileOutputStream(objFile));
	        	log.info("Parsing finished successfully!");
	        }
	        
//	        log.info("\n===============================\nVARS COUNTING:");
//	        log.info("Constants number: " + p.constantsCount);
//	        log.info("Global vars number: " + p.globalVarsCount);
//	        log.info("Global array vars number: " + p.globalArrayVarsCount);
//	        log.info("Class vars number: " + p.classGlobalVarsCount);
//	        log.info("Main function vars number: " + p.mainFunctionVarsCount + "\n===============================\n");
//	        log.info("\n===============================\nFUNCTION CALLS COUNTING:");
//	        log.info("Global function calls: " + p.globalFuncCount);
//	        log.info("Global static function calls: " + p.globalStaticFuncCount);
//	        log.info("Class function calls: " + p.classFuncCount);
//	        log.info("Class static function calls: " + p.classStaticFuncCount);
//	        log.info("Statements block number: " + p.statementsBlockCount);
//	        log.info("Main method function calls number: " + p.mainMethodFunctionCallsCount);
//	        log.info("Function formal parameters number: " + p.functionFormParsCount);
//	        log.info("Class definitions number: " + p.classDefinitionCount);
	        
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}
	}
	
}
