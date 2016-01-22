
public class PrefixTree {
	

	
	static PrefixNode createTree()
    {
        return(new PrefixNode('\0'));
    }
    
	
    static boolean insertWord(PrefixNode root, String word)
    {
    	if(root == null || word.length()==0)
    		return false;
    	
        int offset = 97;
        int l = word.length();
        char[] letters = word.toCharArray();
        PrefixNode curNode = root;
        
        for (int i = 0; i < l; i++)
        {
            if (curNode.links[letters[i]-offset] == null)
                curNode.links[letters[i]-offset] = new PrefixNode(letters[i]);
            curNode = curNode.links[letters[i]-offset];
        }
        curNode.fullWord = true;
        
        return true;
    }

    static boolean find(PrefixNode root, String word)
    {
        char[] letters = word.toCharArray();
        int l = letters.length;
        int offset = 97;
        PrefixNode curNode = root;
        
        int i;
        for (i = 0; i < l; i++)
        {
            if (curNode == null)
                return false;
            curNode = curNode.links[letters[i]-offset];
        }
        
        if (i == l && curNode == null)
            return false;
        
        if (curNode != null && !curNode.fullWord)
            return false;
        
        return true;
    }
    
    static void printTree(PrefixNode root, int level, char[] branch)
    {
        if (root == null)
            return;
        
        for (int i = 0; i < root.links.length; i++)
        {
            branch[level] = root.letter;
            printTree(root.links[i], level+1, branch);    
        }
        
        if (root.fullWord)
        {
            for (int j = 1; j <= level; j++)
                System.out.print(branch[j]);
            System.out.println();
        }
    }
    
    public static void main(String[] args)
    {
        PrefixNode tree = createTree();
        
               String[] words = {"a", "ai", "book", "bee", "cee", "cin", "core", "deed", "dee","donkey","don"};
        
        for (int i = 0; i < words.length; i++)
            insertWord(tree, words[i]);
        
        char[] branch = new char[50];
        printTree(tree, 0, branch);
        
        String searchWord = "deed";
        if (find(tree, searchWord))
        {
            System.out.println("The word was found");
        }
        else
        {
            System.out.println("The word was NOT found");
        }
        
        
        
     

    }
}


	
	

