package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Blacklist
{
	private HashSet<String> blacklist;
	
	public boolean blackListContains(String key)
	{
		return blacklist.contains(key);
	}
	
	public void FileToList(String fileUrl)
	{
		SwissArmy swissArmy = new SwissArmy();
		blacklist = new HashSet<String>();
		
		if(swissArmy.isFile(fileUrl))
		{
	    	try (BufferedReader buffRead = new BufferedReader(new FileReader(fileUrl)))
			{
	    		System.out.println("Read file for blacklist: " + fileUrl);
	    		
				String currentLine;

				while ((currentLine = buffRead.readLine()) != null)
				{
					String[] arr = currentLine.split(" ");    

					 for ( String word : arr) 
					 {
						 if(!(blacklist.contains(word)))
							 blacklist.add(word);
					 }
				}
				
				System.out.println("Populated Blacklist: " + blacklist.size());
			} 
	    	catch (IOException e) 
	    	{
				e.printStackTrace();
			} 
		}	
		else
		{
			System.out.println("Not valid blacklist file");
		}
	}
}
