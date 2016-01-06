package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUrlImporter 
{
	private SwissArmy swissArmy;
	private WordValueMap wordValueMap;
	
	public void Importer(String fileOrUrl)
	{
		System.out.println("Trying to import");
		
		swissArmy = new SwissArmy();
		wordValueMap = new WordValueMap();
		
		if(swissArmy.validStr(fileOrUrl))
		{
			if(swissArmy.isFile(fileOrUrl))
				ReadFile(fileOrUrl);
			else
				ReadUrl(fileOrUrl);
		}		
	}
		
    private void ReadFile(String fileOrUrl)
    {
    	System.out.println("Trying to read FILE: " + fileOrUrl);
    	
    	try (BufferedReader buffRead = new BufferedReader(new FileReader(fileOrUrl)))
		{
    		System.out.println("Read file: " + fileOrUrl);
    		
			String currentLine;

			while ((currentLine = buffRead.readLine()) != null)
			{
				String[] arr = currentLine.split(" ");    

				 for ( String word : arr) 
				 {
					 wordValueMap.add(word);
				 }
			}
			
			System.out.println("Populated Map: " + wordValueMap.size());
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		} 
    }
    
    private void ReadUrl(String fileOrUrl)
    {
    	System.out.println("Trying to read URL: " + fileOrUrl);
    }
}
