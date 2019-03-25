package LLParser;

import java.util.ArrayList;
import java.util.HashSet; //Is this Necessary 
import java.util.List;
import java.util.Set;  //Is this Necessary
import java.util.Stack;

public class Grammar {
	private ArrayList<String> rules;
	private Stack<Character> parseStack;
	private String action;
	
	public Grammar() {
		//Define the rules given in the assignment 
		//Note: rules was origninally barely used so I modified the parse while loop to utilize it
		rules = new ArrayList<>(List.of("2E2", "3E3", "4"));
		//Create the stack to be parsed
		parseStack = new Stack<Character>();
		parseStack.push('$');
	}
	
	public void parse(String input) {
		StringBuilder in = new StringBuilder(input);
		in.append("$");
		
		String stackInput = "$"; 
		//Please explain why 3 different variables need to contain the same value
		
		printHeader(); //Seems unecessary to have a function for this
		
		printStep(stackInput, in.toString(), "SHIFT");
		
		//this loop will continue until the value of in.charAt(0) is null
		while(true) { 
			parseStack.push(in.charAt(0));
			stackInput += in.charAt(0); //stackInput = "$$" ??
			
			in.deleteCharAt(0);
			//below is hella repetitive code so I utilized lambda, havent had a chance to run it yet
			//if you are unfamiliar with lambda I basically did this 
			//https://www.logicbig.com/how-to/java/lambda-list-contains-a-substring.html
			if(in.equals("$E") && stackInput.equals("$")) {
				printStep(stackInput, in.toString(), "Accept");
				break; 
			} else if(rules.stream().anyMatch(r -> stackInput.contains(r))){ //here lies the lambda
				reduceAndShift(r);
			} else {
				printStep(stackInput, in.toString(), "SHIFT");
			}
		}
	}
	
	//This method performs the reduction and shift required with each rule
	public void reduceAndShift(String rule){
		printStep(stackInput, in.toString(), "REDUCE TO E -> " + rule);
		stackInput = stackInput.replace(rule, "E");
		printStep(stackInput, in.toString(), "SHIFT");
	}

	private void printHeader() {
		System.out.printf("%-15s%-15s%-15s\n", "Stack", "Input", "Action");
	}

	//This method prints all the steps required
	private void printStep(String stack, String input, String action) {
		System.out.printf("%-15s%-15s%-15s\n", stack, input, action);
	}
	
	//This methos prints the grammar rules specified in the rules ArrayList
	public void printGrammarRules() {
		System.out.println("GRAMMAR is");
		
		for(String rule : rules) 
			System.out.println("E -> " + rule);
		
		System.out.println();
	}
}
