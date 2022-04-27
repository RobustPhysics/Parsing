import java.util.ArrayList;
import java.util.Arrays;

public class Node2
{
	Lexicon wordBase;
	ArrayList<NodeArc2> outgoingArcs;
	String parentATN;
	String id;
	
	public Node2()
	{
		wordBase = Lexicon.getInstance();
		outgoingArcs = new ArrayList<NodeArc2>();
		parentATN = null;
	}
	
	public ArrayList<NodeArc2> getValidArcs(LexiconWord currentWord, String asterisk, ArrayList<StringPair> currentVariables)
	{
		ArrayList<NodeArc2> valid = new ArrayList<NodeArc2>();
		
		for (NodeArc2 arc : outgoingArcs)
		{
			if (arc.passesTest(currentWord, asterisk, currentVariables))
			{
				valid.add(arc);
			}
		}
		
		return valid;
	}
	
	/*
	public void processNode(String[] words, ArrayList<StringPair> currentVariables)
	{
		if (words.length == 0)
		{
			System.out.println("ERROR! No words left to process.");
			return;
		}
			
		LexiconWord currentWord = wordBase.getWord(words[0]);
		
		if (currentWord == null)
		{
			System.out.println("ERROR! Unable to find word " + words[0]);
			return;
		}
		
		ArrayList<NodeArc2> possibleArcs = getValidArcs(currentWord, currentVariables);
		String[] newWords = Arrays.copyOfRange(words, 1, words.length);
		
		for (NodeArc2 arc : possibleArcs)
		{
			
			ArrayList<StringPair> newVariables = arc.executeAction(newWords, currentWord, currentVariables);
			
			if (newVariables != null)
			{
				
			}
		}
	}
	*/
	
	public String toString()
	{
		//return outgoingArcs.toString();
		
		String output = id;
		output = output + " -> " + outgoingArcs.toString();
		
		return output;
	}
}
