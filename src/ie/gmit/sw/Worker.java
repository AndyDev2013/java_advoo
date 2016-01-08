package ie.gmit.sw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Worker 
{	
	private HashMap<String,Integer> map;
	private ArrayList<String> orderedWordList;
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
			
		System.out.println("List size: " + orderedWordList.size());
		
		try 
		{
			ReallySimpleWordCloud rswc = new ReallySimpleWordCloud(orderedWordList);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		/*
		System.out.println();
		
		for(String s : orderedWordList)
		{
			System.out.println(s + " " + map.get(s));
		}
		*/
		
	}	
}
