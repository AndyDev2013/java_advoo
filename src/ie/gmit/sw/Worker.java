package ie.gmit.sw;

import java.util.HashMap;

public class Worker 
{		
	private HashMap<String,Integer> wordValueMap;
	private Importer importer;

	public void doWork(String fileToBlackList, String fileOrUrl)
	{
		wordValueMap = new HashMap<String,Integer>();
		importer = new Importer();
		
		importer.ImportBlackList(fileToBlackList);
		importer.ImportWords(fileOrUrl);
		
	}
	
}
