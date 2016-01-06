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
		
		if(swissArmy.isFile(fileToBlackList))
		{
			blacklist = new Blacklist();
			blacklist.FileToList(fileToBlackList);
		}
		
		fuImporter.Importer(blacklist,fileOrUrl);
		
	}
	
}
