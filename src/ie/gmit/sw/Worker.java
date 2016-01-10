package ie.gmit.sw;

import java.io.IOException;
import java.util.ArrayList;

public class Worker 
{	
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
			
		System.out.println("List size: " + orderedWordList.size());
		
		try 
		{
			WordCloud wordcloud = new WordCloud();
			wordcloud.CreateWordCloud(orderedWordList,worldvaluemap);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}	
}
