import java.util.ArrayList;

public class NodeArcNP extends NodeArc2
{
	
	public NodeArcNP()
	{
		
	}
	
	
	public boolean passesTest(LexiconWord currentWord, ArrayList<StringPair> variables)
	{
		if (isWordCategory && !wordMatchesCategory(currentWord))
		{
			//fails test
		}
		ArrayList<StringPair> wordVariables = getCurrentWordValues(currentWord);
		
		
		
		return false;
	}
}
