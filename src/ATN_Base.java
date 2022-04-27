import java.util.ArrayList;
import java.util.Arrays;

public class ATN_Base
{
	Node2 startNode = null;
	
	Node2 startNodeNP = null;
	Node2 startNodeS = null;
	Node2 startNodeVP = null;
	Node2 startNodePP = null;
	Lexicon wordBase;
	
	public ATN_Base()
	{
		wordBase = Lexicon.getInstance();
		
		initializeATN_S();
		initializeATN_NP();
	}
	
	private void initializeATN_S()
	{
		startNodeS = new Node2();
		startNodeS.parentATN = "S";
		startNodeS.id = "S0";
		
		Node2 S1 = new Node2();
		S1.parentATN = "S";
		S1.id = "S1";
		
		/*
		String arcLabel = null;
		boolean isWordCategory = false;
		boolean isJump = false;
		String arcCategory = null;
		String switchCategory = null;
		int depth = 0;
		Node2 parentNode = null;
		Node2 destinationNode = null;
		*/
		
		NodeArc2 a01 = new NodeArc2();
		a01.arcLabel = "NP";
		a01.arcCategory = "S";
		a01.switchCategory = "NP";
		a01.parentNode = startNodeS;
		a01.destinationNode = S1;
		startNodeS.outgoingArcs.add(a01);
		
		Node2 S2 = new Node2();
		S2.parentATN = "S";
		S2.id = "S2";
		
		NodeArc2 a02 = new NodeArc2();
		a02.arcLabel = "VERB";
		a02.arcCategory = "S";
		a02.parentNode = S1;
		a02.isWordCategory = true;
		a02.destinationNode = S2;
		S1.outgoingArcs.add(a02);
		
		NodeArc2 a03 = new NodeArc2();
		a03.arcLabel = "DONE";
		a03.arcCategory = "S";
		a03.parentNode = S2;
		S2.outgoingArcs.add(a03);
		
		
	}
	
	private void initializeATN_NP()
	{
		startNodeNP = new Node2();
		startNodeNP.parentATN = "NP";
		startNodeNP.id = "NP0";
		
		Node2 NP1 = new Node2();
		NP1.parentATN = "NP";
		NP1.id = "NP1";
		
		NodeArc2 a01 = new NodeArc2();
		a01.arcLabel = "NAME";
		a01.isWordCategory = true;
		a01.arcCategory = "NP";
		a01.parentNode = startNodeNP;
		a01.destinationNode = NP1;
		startNodeNP.outgoingArcs.add(a01);
		
		
		NodeArc2 a02 = new NodeArc2();
		a02.arcLabel = "DONE";
		a02.arcCategory = "NP";
		a02.parentNode = NP1;
		NP1.outgoingArcs.add(a02);
		
	}
	
	private String[] getWords(String line)
	{
		String[] words = line.split("\\W+");
		
		return words;
	}
	
	//words is an array of the words in sentence
	//currentNode is the node currently at
	//returnPoints starts at startNodeS
	//		First element is always the current group
	//		Second element is where to return to
	public String parseSentence(String[] words, String asterisk, Node2[] nodePointers, ArrayList<StringPair> currentValues) //Node2 currentNode, ATN_Base[] returnPoints)
	{
		System.out.println("----------------");
		System.out.println("Parse start: Asterisk = " + asterisk);
		System.out.println("Node Points = ");
		for (Node2 n : nodePointers)
		{
			System.out.println("\t" + n);
		}
		if (words.length == 0)
		{
			System.out.println("ERROR! No words left to process.");
			String group = nodePointers[0].parentATN;
			asterisk = "(" + group;
			for (StringPair p : currentValues)
			{
				asterisk = asterisk + p.toString();
			}
			asterisk = asterisk + ")";
			return asterisk;
		}
		System.out.print("Words: ");
		for (String str : words)
			System.out.print(str + ", ");
		System.out.println();
		
		String output = "";
		
		LexiconWord currentWord = wordBase.getWord(words[0]);
		
		if (currentWord == null)
		{
			System.out.println("ERROR! Unable to find word " + words[0]);
			return null;
		}
		
		Node2 currentNode = nodePointers[0];
		ArrayList<NodeArc2> possibleArcs = currentNode.getValidArcs(currentWord, asterisk, currentValues);
		String[] newWords = {};
		if (words.length > 1)
			newWords = Arrays.copyOfRange(words, 1, words.length);
		//asterisk = words[0];
		
		for (NodeArc2 arc : possibleArcs)
		{
			Node2 newNode = arc.destinationNode;
			
			if (arc.switchCategory != null && !currentNode.parentATN.equals(arc.switchCategory))
			{
				System.out.println("Switching ATN. Going from " + currentNode.parentATN + " to " + arc.switchCategory);
				
				Node2 pushNode = null;
				if (arc.switchCategory.equals("NP"))
					pushNode = startNodeNP;
				else if (arc.switchCategory.equals("VP"))
					pushNode = startNodeVP;
				else if (arc.switchCategory.equals("S")) //this should never happen though
					pushNode = startNodeS;
				else if (arc.switchCategory.equals("PP"))
					pushNode = startNodePP;
				
				//nodePointers[0] = newNode;
				Node2[] newNodePtrs = Arrays.copyOf(nodePointers, nodePointers.length+1);
				newNodePtrs[0] = pushNode;
				System.arraycopy(nodePointers, 0, newNodePtrs, 1, nodePointers.length);
				System.out.println("New nodePointers: ");
				for (Node2 n : newNodePtrs)
				{
					System.out.println("\t" + n);
				}
				nodePointers = newNodePtrs;
				arc.depth += 1;
				arc.switchCategory = null;
				return parseSentence(words, asterisk, nodePointers, currentValues);
			}
			
			ArrayList<StringPair> newValues = arc.executeAction(currentWord, asterisk, currentValues);
			
			
			
			if (newNode == null) //means it is done
			{
				System.out.println("ATN arc " + arc.arcCategory + " is done.");
				String group = currentNode.parentATN;
				asterisk = "(" + group;
				for (StringPair p : newValues)
				{
					asterisk = asterisk + p.toString();
				}
				asterisk = asterisk + ")";
				System.out.println("Asterisk = " + asterisk);
				if (nodePointers.length > 1)
				{
					nodePointers = Arrays.copyOfRange(nodePointers, 1, nodePointers.length);
				}
				else
				{
					//ATN pointer is now empty...
					if (words.length == 0)
					{
						return asterisk;
					}
					else
					{
						return null;
					}
				}
				
				System.out.println("New nodePointers: ");
				for (Node2 n : nodePointers)
				{
					System.out.println("\t" + n);
				}
				
				
				currentValues.clear();
				//arc.executeAction(currentWord, asterisk, currentValues);
				return parseSentence(words, asterisk, nodePointers, currentValues);
				//We set it to the value returned by this node...
				
				//The ATN label and then the newValues contents I believe.
			}
			
			else if (arc.isWordCategory)
			{
				System.out.println("Found word category (" + arc.arcLabel + ")");
				nodePointers[0] = newNode;
				currentWord = wordBase.getWord(words[0]);
				words = newWords;
				return parseSentence(newWords, asterisk, nodePointers, newValues);
			}
			
			else if (arc.isJump || arc.depth > 0)
			{
				System.out.println("Jumping, somehow.");
				nodePointers[0] = newNode;
				return parseSentence(words, asterisk, nodePointers, newValues);
			}
			else 
			{
				System.out.println("ERROR: Not doing anything with arc " + arc);
				//return parseSentence(words, asterisk, )
			}
			
			//return parseSentence(words, asterisk, nodePointers, currentValues);
		}
		
		System.out.println("ERROR: Went through all possible arcs, but none left.");
		
		return asterisk;
	}
	
	public String parseSentence(String line)
	{
		System.out.println("Start");
		String[] words = getWords(line);
		for (String s : words)
		{
			System.out.println(s);
		}
		Node2[] ptrs = {startNodeS};
		
		return parseSentence(words, words[0], ptrs, new ArrayList<StringPair>());
	}
}
