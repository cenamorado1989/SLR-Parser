package LLParser;

public class LR{
	public static void main(String[] args) {
		Grammar g = new Grammar();
		
		g.printGrammarRules();
		
		g.parse("32423");
	}
}
