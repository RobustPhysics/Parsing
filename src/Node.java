import java.util.ArrayList;

public class Node
{
	String nodeLabel;
	String currentWord;
	ArrayList<StringPair> resultList;
	NodeArc parentArc;
	ArrayList<NodeArc> destinationArcs;
	
	public Node(String nLabel)
	{
		//currentWord = word;
		nodeLabel = nLabel;
		resultList = new ArrayList<StringPair>();
		parentArc = null;
		destinationArcs = new ArrayList<NodeArc>();
	}
	
	public Node()
	{
		this(null);
	}
	
	private int getNextValidArcIndex(LexiconWord word, ArrayList<StringPair> values)
	{
		for (NodeArc arc : destinationArcs)
		{
			//if (arc )
		}
		
		return -1;
	}
	
	public String executeNextArc()
	{
		String output = "";
		
		
		return output;
	}
	
	public String getNodeLabel()
	{
		return nodeLabel;
	}
	
	public void setNodeLabel(String newLabel)
	{
		nodeLabel = newLabel;
	}
	
	public StringPair getPairFromSlot(String s)
	{
		StringPair result = null;
		for (StringPair pair : resultList)
		{
			if (pair.getSlot().equals(s))
				result = pair;
		}
		
		return result;
	}
	
	public ArrayList<StringPair> getResultList()
	{
		return resultList;
	}
	
	public boolean addStringPair(StringPair pair)
	{
		return resultList.add(pair);
	}
	
	public boolean addStringPair(String s, String v)
	{
		return addStringPair(new StringPair(s, v));
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("(");
		sb.append(nodeLabel);
		int lastLength = sb.length();
		for (StringPair pair : resultList)
		{
			sb.append(" ");
			sb.append(pair);
			sb.append("\n\t");
		}
		if (sb.length() - lastLength > 0)
			sb.setLength(sb.length()-2);
		
		sb.append(")");
		
		return sb.toString();
	}
}
