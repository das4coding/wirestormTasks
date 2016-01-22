
public class PrefixNode {

	 	char letter;
	    PrefixNode[] links;
	    boolean fullWord;
	  
	    PrefixNode(char letter, boolean fullWord)
	    {
	        this.letter = letter;
	        links = new PrefixNode[26];
	        this.fullWord = fullWord;
	    }
	    
	    PrefixNode(char letter)
	    {
	        this.letter = letter;
	        links = new PrefixNode[26];
	        this.fullWord = false;
	    }
	
}
