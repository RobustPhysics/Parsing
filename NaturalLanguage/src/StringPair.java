import java.util.ArrayList;

public class StringPair
{
	String slot;
	ArrayList<String> values;
	
	public StringPair(String s, String v)
	{
		slot = s;
		values = new ArrayList<String>();
		//value = v;
	}
	
	public StringPair()
	{
		this(null, null);
	}
	
	public String getSlot()
	{
		return slot;
	}
	
	public boolean containsValue(String v)
	{
		return values.contains(v);
	}
	
	public String toString()
	{
		return "(" + slot + " " + values + ")";
	}
}
