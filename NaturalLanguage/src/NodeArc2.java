import java.util.ArrayList;

public class NodeArc2
{
	String arcLabel = null;
	boolean isWordCategory = false;
	boolean isJump = false;
	String arcCategory = null;
	String switchCategory = null;
	int depth = 0;
	Node2 parentNode = null;
	Node2 destinationNode = null;
	
	protected boolean wordMatchesCategory(LexiconWord currentWord)
	{
		WordType t = currentWord.getType(arcLabel);
		
		return t != null;
	}
	
	protected ArrayList<StringPair> getCurrentWordValues(LexiconWord currentWord)
	{
		ArrayList<StringPair> wordVariables = new ArrayList<StringPair>();
		//fill array with features of currentWord
		
		WordType wt = currentWord.getType(arcLabel);
		if (wt != null)
		{
			ArrayList<WordFeature> features = wt.getFeatures();
			for (WordFeature f : features)
			{
				StringPair p = new StringPair();
				p.slot = f.getFeature();
				p.values = f.getValues();
				
				wordVariables.add(p);
			}
		}
		
		return wordVariables;
	}
	
	public boolean nounPhraseTests(ArrayList<StringPair> wordVariables, ArrayList<StringPair> atnVariables)
	{
		//based on tests for NP
		
		return true;
	}
	
	public boolean verbPhraseTests(ArrayList<StringPair> wordVariables, ArrayList<StringPair> atnVariables)
	{
		//based on tests for NP
		
		return true;
	}
	
	public boolean sentenceTests(ArrayList<StringPair> wordVariables, ArrayList<StringPair> atnVariables)
	{
		//based on tests for NP
		
		return true;
	}
	
	public boolean preprositionPhraseTests(ArrayList<StringPair> wordVariables, ArrayList<StringPair> atnVariables)
	{
		//based on tests for NP
		
		return true;
	}
	
	public boolean passesTest(LexiconWord currentWord, String asterisk, ArrayList<StringPair> variables)
	{
		if (isWordCategory && !wordMatchesCategory(currentWord))
		{
			//fails test
			return false;
		}
		ArrayList<StringPair> wordVariables = getCurrentWordValues(currentWord);
		
		if (arcCategory.equals("NP"))
			return nounPhraseTests(wordVariables, variables);
		else if (arcCategory.equals("VP"))
			return verbPhraseTests(wordVariables, variables);
		else if (arcCategory.equals("S"))
			return sentenceTests(wordVariables, variables);
		else if (arcCategory.equals("PP"))
			return preprositionPhraseTests(wordVariables, variables);
		
		return false;
	}
	
	
	public ArrayList<StringPair> executeAction(LexiconWord currentWord, String asterisk, ArrayList<StringPair> currentVariables)
	{
		System.out.println("Executing " + arcCategory + "/" + arcLabel + " with word = " + currentWord.getWord() + " and * = " + asterisk);
		System.out.println("CurrentVariables = " + currentVariables);
		ArrayList<StringPair> newVariables = new ArrayList<StringPair>();
		
		if (arcCategory.equals("NP"))
			newVariables = nounPhraseAction(currentWord, asterisk, currentVariables);
		else if (arcCategory.equals("S"))
			newVariables = sentenceAction(currentWord, asterisk, currentVariables);
		
		System.out.println("New variables: " + newVariables.toString());
		
		return newVariables;
	}
	
	private ArrayList<StringPair> nounPhraseAction(LexiconWord currentWord, String asterisk, ArrayList<StringPair> currentVariables)
	{
		System.out.println("Entered NP Action and arcLabel = " + arcLabel);
		ArrayList<StringPair> newVariables = new ArrayList<StringPair>();
		
		
		if (arcLabel.equals("NAME"))
		{
			for (StringPair sP : currentVariables)
			{
				if (!sP.slot.equals("NAME") && !sP.slot.equals("NUM"))
					newVariables.add(sP);
			}
			
			StringPair sP = new StringPair();
			sP.slot = "NAME";
			sP.values.add(asterisk);
			newVariables.add(sP);
			
			StringPair sP2 = new StringPair();
			sP2.slot = "NUM";
			sP2.values.add("3s");
			newVariables.add(sP2);
		}
		else
		{
			newVariables = currentVariables;
		}
		
		return newVariables;
	}
	
	private void verbPhraseAction()
	{
		
	}
	
	private ArrayList<StringPair> sentenceAction(LexiconWord currentWord, String asterisk, ArrayList<StringPair> currentVariables)
	{
		System.out.println("Entered S action and arcLabel = " + arcLabel);
		ArrayList<StringPair> newVariables = new ArrayList<StringPair>();
		
		
		if (arcLabel.equals("NP"))
		{
			for (StringPair sP : currentVariables)
			{
				if (!sP.slot.equals("SUBJ") && !sP.slot.equals("MOOD"))
					newVariables.add(sP);
			}
			
			StringPair sP2 = new StringPair();
			sP2.slot = "MOOD";
			sP2.values.add("Declarative");
			newVariables.add(sP2);
			
			StringPair sP = new StringPair();
			sP.slot = "SUBJ";
			sP.values.add(asterisk);
			newVariables.add(sP);
		}
		else if (arcLabel.equals("VERB"))
		{
			for (StringPair sP : currentVariables)
			{
				if (!sP.slot.equals("VERB") && !sP.slot.equals("NUM"))
					newVariables.add(sP);
			}
			
			StringPair sP = new StringPair();
			sP.slot = "VERB";
			sP.values.add(asterisk);
			newVariables.add(sP);
			
			StringPair sP2 = new StringPair();
			sP2.slot = "NUM";
			newVariables.add(sP2);
			//sP2.values.add("Declarative");
			
			
			String subj = null;
			for (StringPair sp : currentVariables)
			{
				if (sp.slot.equals("SUBJ") && sp.values.size() > 0)
					subj = sp.values.get(0);
			}
			LexiconWord subjWord = Lexicon.getInstance().getWord(subj);
			
			WordFeature num = currentWord.getFeature("NUM");
			ArrayList<String> num2 = null;
			for (StringPair val : currentVariables)
			{
				if (val.slot.equals("NUM"))
					num2 = val.values;
			}
			
			if (num2 != null)
			{
				ArrayList<String> intersections = new ArrayList<String>();
				for (String n : num2)
				{
					if (num.containsValue(n))
						intersections.add(n);
				}
				
				sP2.values = intersections;
			}
		}
		else
		{
			newVariables = currentVariables;
		}
		
		//System.out.println(newVariables.toString());
		
		return newVariables;
	}
	
	private void prepositionPhraseAction()
	{
		
	}
	
	public String toString()
	{
		return arcCategory + "/" + arcLabel;
	}
}
