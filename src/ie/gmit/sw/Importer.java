package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import org.apache.commons.validator.routines.UrlValidator;

public class Importer implements Parsable
{
	private WordValueMap wordValueMap;
	private HashSet<String> blacklist;
	
	public void ImportWords(String fileOrUrl)
	{
		System.out.println("Trying to import: " + fileOrUrl);	
		
		wordValueMap = new WordValueMap();
		
		if(isFileOrUrl(fileOrUrl))
		{
			if(isFile(fileOrUrl))
				ReadFile(fileOrUrl);
			else
				ReadUrl(fileOrUrl);
		}	
		else
			System.out.println("Bad file location, doesn't exist or isn't a valid file for file");
	}
	
    public boolean isFileOrUrl(String isFileOrUrl)
	{
		boolean isFile = isFile(isFileOrUrl);
		boolean isUrl = isUrl(isFileOrUrl);
		
		if(isFile || isUrl)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void ImportBlackList(String fileUrl)
	{
		blacklist = new HashSet<String>();		
	
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
	
	public boolean isUrl(String possibleUrl)
	{
		String[] schemes = {"http","https"};
		
		UrlValidator urlValidator = new UrlValidator(schemes);
		
		if (urlValidator.isValid(possibleUrl)) 
		   return true;
		else
		   return false;
	
	}// Tells whether the string is a possible Url or not
	
    public boolean isFile(String possibleFile)
	{
		File f = new File(possibleFile);
		
		if(f.exists() && !f.isDirectory()) 
		    return true;
		else
			return false;
		
	}// Tells whether the string is a file that exists in the directory or not
		
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
					 if(blacklist != null)
					 {
						 if(!(blacklist.contains(word)))					 
							 wordValueMap.add(word);
					 }
					 else
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
    
    private void validStrDialog(String isFileOrUrl)
	{
		String successTitle = "Valid File";
		String warningTitle = "File Not Valid";
		String goodMsg = "The file or url: \n\"" + isFileOrUrl + "\"\nis VALID";
		String badMsg = "The file or url: \n\"" + isFileOrUrl + "\"\nis NOT VALID";
		
		boolean flag = isFileOrUrl(isFileOrUrl);		
		UserUIDialog uid;
		
		if(flag)
			uid = new UserUIDialog(successTitle,goodMsg,UserUIMessageType.PLAIN);
		else
			uid = new UserUIDialog(warningTitle,badMsg,UserUIMessageType.ERROR);		
	}
    	
	private boolean blackListContains(String key)
	{
		return blacklist.contains(key);
	}
	
}
