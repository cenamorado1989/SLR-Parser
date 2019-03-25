import java.io.File;
import java.util.Scanner; 

public class LR_Batch{
  public static void main(String[] args) throws Exception{
    File batch_test = new File("batch_test.txt"); //Define a file object as batch_test.txt
    Scanner sc = new Scanner(batch_test); //Define a scanner object of batch_test 
    Grammar g = new Grammar(); //Define a grammar object
    int line_num = 1; //Define an integer to keep count of the index of the current line being parsed
    
    while (sc.hasNextLine()){
      System.out.println("Parsing line " + line_num + "...");
      g.printGrammarRules(); //Print the Grammar rules defined in Grammar.java
      g.parse(sc.nextLine()); //Parse the next line of the file
      line_num++; //Increment the counter
  }
}
