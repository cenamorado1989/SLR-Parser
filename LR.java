package LLParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LR{
	public static void main(String[] args) {
		Grammar g = new Grammar();
		
		g.printGrammarRules();
		
		g.parse("32423");
	}
}