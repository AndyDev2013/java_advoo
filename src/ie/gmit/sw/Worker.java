package ie.gmit.sw;

import java.util.HashMap;

public class Worker 
{		
	private HashMap<String,Integer> wordValueMap;
	private SwissArmy swissArmy;
	private FileUrlImporter fuImporter;
	private Blacklist blacklist;

	public void doWork(String fileToBlackList, String fileOrUrl)
	{
		wordValueMap = new HashMap<String,Integer>();
		swissArmy = new SwissArmy();
		fuImporter = new FileUrlImporter();
		blacklist = new Blacklist();
		
		if(swissArmy.isFile(fileToBlackList))		
			blacklist.FileToList(fileToBlackList);
		
		fuImporter.Importer(blacklist,fileOrUrl);
		
	}
	
}
