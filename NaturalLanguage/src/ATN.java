import java.util.ArrayList;

public class ATN
{
	Node startNode = null;
	
	public ATN()
	{
		
	}
	
	private void setupATN()
	{
		startNode = new Node("S");
	}
	
	
	private String[] getWords(String line)
	{
		String[] words = line.split("\\W+");
		
		return words;
	}
	
	/*
	PROCESS
	Get an array of the words.
	Get the first node.
	Keep a record of the current variables
		(Stuff like SUBJ, VERB, NUM, and etc)
		(Until an arc action sets it, may not exist)
	
	For the current node...
		Get LexiconWord from current word
		Process the node
			Search through all arcs
				If arc is a word type (like NOUN) and LexiconWord has that type...
					Execute the arc
						Set values under current variables (create if doesn't exist)
						Process the node (repeats previous step above)
						Merge variables (overwriting current)
						Return the list of new current variables
				If arc leads to a branch (like S -> NP)
					Also execute the arc (same as above)
					Append branch name (e.g., NP) and concat with the variables
			If no arcs searched, then...
				
	
	*/
	public String parseSentence(String line)
	{
		String[] words = getWords(line);
		String output = "";
		
		ArrayList<StringPair> currentValues = new ArrayList<StringPair>();
		Node currentNode = startNode;
		int currentIndex = 0;
		
		
		
		return output;
	}
}
