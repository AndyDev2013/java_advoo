package ie.gmit.sw;

import java.util.HashMap;

public class Worker 
{		
	private HashMap<String,Integer> wordValueMap;
	private SwissArmy swissArmy;
	private FileUrlImporter fuImporter;

	public void doWork(String fileOrUrl)
	{
		wordValueMap = new HashMap<String,Integer>();
		swissArmy = new SwissArmy();
		fuImporter = new FileUrlImporter();
		
		fuImporter.Importer(fileOrUrl);
	}
	
}
