package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map.Entry;

public class WordValueMap 
{
	private HashMap<String,Integer> wordValueMap = new HashMap<String,Integer>();
	
	public void add(String key)
	{
		int INITIAL_VALUE = 1;
		
		if(wordValueMap.containsKey(key))
		{
			int value = wordValueMap.get(key);
			
			value += 1;
			
			wordValueMap.remove(key);
			wordValueMap.put(key, value);
		}
		else
			wordValueMap.put(key, INITIAL_VALUE);
	}
	
	public int size()
	{
		return wordValueMap.size();
	}
	
	public String mostUsed()
	{
		String highKey = "";
		int highValue = 0;
		
		for(Entry<String, Integer> entry : wordValueMap.entrySet()) 
		{
			if(entry.getValue() > highValue)
			{
				highKey = entry.getKey();
				highValue = entry.getValue();
			}
		}
		
		return highKey;
	}
	
	public int mostUsedCount()
	{
		return wordValueMap.get(mostUsed());
	}
	
	public String firstTen()
	{
		String firstTen = "\nFirst 10 \n\n";
		
		int i = 0;
		
		for(Entry<String, Integer> entry : wordValueMap.entrySet()) 
		{
			if(i > 9)
			{
				return firstTen;
			}				
				
			firstTen += entry.getKey() + "\n";		
			
			++i;
		}
		
		return firstTen;
	}
	
	
}
