package LLParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Grammar {
	private ArrayList<String> rules;
	private Stack<Character> parseStack;
	private String action;
	
	public Grammar() {
		rules = new ArrayList<>(List.of("2E2", "3E3", "4"));
		
		parseStack = new Stack<Character>();
		parseStack.push('$');
	}
	
	public void parse(String input) {
		StringBuilder in = new StringBuilder(input);
		in.append("$");
		
		String stackInput = "$";
		
		printHeader();
		
		printStep(stackInput, in.toString(), "SHIFT");
		
		while(true) {
			parseStack.push(in.charAt(0));
			stackInput += in.charAt(0);
			
			in.deleteCharAt(0);
			
			if(in.equals("$E") && stackInput.equals("$")) {
				printStep(stackInput, in.toString(), "Accept");
				break;
			} else if(stackInput.contains("2E2")) {
				printStep(stackInput, in.toString(), "REDUCE TO E -> 2E2");
				stackInput = stackInput.replace("2E2", "E");
				printStep(stackInput, in.toString(), "SHIFT");
			} else if(stackInput.contains("3E3")) {
				printStep(stackInput, in.toString(), "REDUCE TO E -> 3E3");
				stackInput = stackInput.replace("3E3", "E");
				printStep(stackInput, in.toString(), "SHIFT");
			} else if(stackInput.contains("4")) {
				printStep(stackInput, in.toString(), "REDUCE TO E -> 4");
				stackInput = stackInput.replace("4", "E");
				printStep(stackInput, in.toString(), "SHIFT");
			} else {
				printStep(stackInput, in.toString(), "SHIFT");
			}
		}
	}
	
	private void printHeader() {
		System.out.printf("%-15s%-15s%-15s\n", "Stack", "Input", "Action");
	}
	
	private void printStep(String stack, String input, String action) {
		System.out.printf("%-15s%-15s%-15s\n", stack, input, action);
	}
	
	public void printGrammarRules() {
		System.out.println("GRAMMAR is");
		
		for(String rule : rules) {
			System.out.println("E -> " + rule);
		}
		System.out.println();
	}
}
