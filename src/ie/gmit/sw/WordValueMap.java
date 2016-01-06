package ie.gmit.sw;

import java.util.HashMap;

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
	
}
