package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.util.Log4JUtils;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(MJParserTest.class);
		Reader br = null;
		try {
			
			File sourceCode = new File("test/TestProgram.mj");	
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());

			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja

	        log.info("\n===============================\nVARS COUNTING:");
	        log.info("Constants number: " + p.constantsCount);
	        log.info("Global vars number: " + p.globalVarsCount);
	        log.info("Global array vars number: " + p.globalArrayVarsCount);
	        log.info("Class vars number: " + p.classGlobalVarsCount);
	        log.info("Main function vars number: " + p.mainFunctionVarsCount + "\n===============================\n");
	        log.info("\n===============================\nFUNCTION CALLS COUNTING:");
	        log.info("Global function calls: " + p.globalFuncCount);
	        log.info("Global static function calls: " + p.globalStaticFuncCount);
	        log.info("Class function calls: " + p.classFuncCount);
	        log.info("Class static function calls: " + p.classStaticFuncCount);
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}
	}
	
}
