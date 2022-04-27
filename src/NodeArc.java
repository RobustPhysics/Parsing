import java.util.ArrayList;

public class NodeArc
{
	Node parentNode = null;
	Node nextNode = null;
	String arcLabel = null;
	String processedWord = null;
	
	/*
	String requiredNum = null;
	
	boolean numIntersectLastNum = false;
	boolean numIntersectSubjNum = false;
	
	boolean requireTransitive = false;
	boolean requireNotTransitive = false;
	boolean requireBitransitive = false;
	
	boolean setName = false;
	boolean setSubj = false;
	boolean setMood = false;
	boolean setVerb = false;
	*/
	
	public NodeArc(String word, String label)
	{
		processedWord = word;
		arcLabel = label;
	}
	
	public boolean isTestTrue(LexiconWord currentWord, ArrayList<StringPair> values)
	{
		if (parentNode == null || arcLabel == null) //should not be able to get here
			return false;
		
		
		/*
		==================================================================================
		TESTS FOR NOUN PHRASE (NP)
		==================================================================================
		 */
		if (parentNode.nodeLabel.contains("NP")) //came from a NP node
		{
			String arcPath = parentNode.nodeLabel + arcLabel;
			if (arcPath.equals("NP/noun")) //Arc is NP/noun
			{
				//NUM(*) = 3p
				WordFeature num = currentWord.getFeature("NOUN", "NUM");
				if (num.containsValue("3p", true))
					return true;
			}
			else if (arcPath.equals("NP1/noun"))
			{
				//NUM(U) Intersects NUM
				WordFeature num = currentWord.getFeature("NOUN", "NUM");
				ArrayList<String> num2 = null;
				for (StringPair val : values)
				{
					if (val.slot.equals("NUM"))
						num2 = val.values;
				}
				
				if (num2 != null)
				{
					int intersects = 0;
					for (String n : num2)
					{
						if (num.containsValue(n))
							intersects++;
					}
					
					return intersects > 0;
				}
			}
		}
		
		
		/*
		==================================================================================
		TESTS FOR VERB PHRASE (VP)
		==================================================================================
		 */
		
		
		
		
		/*
		==================================================================================
		TESTS FOR SENTENCE (S)
		==================================================================================
		 */
		
		return true;
	}
	
	
	
	
	public ArrayList<StringPair> processArcAction(LexiconWord currentWord, ArrayList<StringPair> values)
	{
		
		
		return values;
	}
}
