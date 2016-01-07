package ie.gmit.sw;

import java.util.HashMap;
import java.util.List;

public class Worker 
{	
	private HashMap<String,Integer> map;
	private List<String> orderedWordList;
	private Importer importer;
	private WordValueMap worldvaluemap;

	public void doWork(String fileToBlackList, String fileOrUrl)
	{
		importer = new Importer();		
		importer.ImportBlackList(fileToBlackList);
		importer.ImportWords(fileOrUrl);	
		
		worldvaluemap = importer.getWordValueMap();
		orderedWordList = worldvaluemap.getOrderedList();
		map = worldvaluemap.getStrValMap();
						
		for(String s : orderedWordList)
		{
			System.out.println(s + " " + map.get(s));
		}
	}	
}
