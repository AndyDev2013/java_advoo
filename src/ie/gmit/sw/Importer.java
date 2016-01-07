package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Importer implements Parsable
{
	private WordValueMap wordValueMap;
	private HashSet<String> blacklist;
	
	public void ImportWords(String fileOrUrl)
	{
    	System.out.println("\n--- WordCloudFile ---");
		System.out.println("\nTrying to import wordcloud file: " + "\"" + fileOrUrl + "\"");
		
		wordValueMap = new WordValueMap();
		
		if(isFileOrUrl(fileOrUrl))
		{
			if(isFile(fileOrUrl))
				ReadFile(fileOrUrl);
			else
				ReadUrl(fileOrUrl);
		}	
		else
		{
			String message = "WordCloud file/url isn't valid.";
			String otherMessage;
			
			fileOrUrl = Globals.getInstance().getBackupWordCloud();
			
			otherMessage = "Using the internal backup file: " + "\"" + fileOrUrl + "\"\n";
				
			System.out.println(message);
			System.out.println(otherMessage);
			
			ReadFile(fileOrUrl);
			
			if(Globals.getInstance().getIsGui())
				new UserUIDialog(message + "\n" + otherMessage, UserUIMessageType.WARNING);
		}
	}
	
	public void ImportBlackList(String fileUrl)
	{
		System.out.println("--- Blacklist File ---");
		System.out.println("\nTrying to import blacklist file: " + "\"" + fileUrl + "\"");
		
		blacklist = new HashSet<String>();		
		
		if(!(isFile(fileUrl)))
		{
			fileUrl = Globals.getInstance().getBackupStopwords();
			
			System.out.println("Using the internal backup file: " + "\"" + fileUrl + "\"\n");
		}
	
    	try (BufferedReader buffRead = new BufferedReader(new FileReader(fileUrl)))
		{
    		System.out.println("Read file for blacklist: " + fileUrl);
    		
			String currentLine;

			while ((currentLine = buffRead.readLine()) != null)
			{
				String[] arr = currentLine.split(" ");    

				 for ( String word : arr) 
				 {
					 if(!(blacklist.contains(word)) && !word.equals(""))
						 blacklist.add(word);
				 }
			}
			
			System.out.println("Populated Blacklist: " + blacklist.size());
		} 
    	catch (IOException e) 
    	{
    		String message = "Blacklist file at given location not found";
    		
			if(Globals.getInstance().getIsGui())
				new UserUIDialog(message,UserUIMessageType.ERROR);
			else
				System.out.println(message);
		} 	
	}
	
    private void ReadFile(String fileOrUrl)
    {
    	System.out.println("Trying to read FILE: " + "\"" + fileOrUrl + "\"\n");
    	
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
			System.out.println(wordValueMap.firstTen());
		} 
    	catch (IOException e) 
    	{
    		String message = "WordCloud file at given location not found";
    		
			if(Globals.getInstance().getIsGui())
				new UserUIDialog(message,UserUIMessageType.ERROR);
			else
				System.out.println(message);
		} 
    }	
    
    public void ReadUrl(String urlLocation)
    {
    	System.out.println("Trying to read URL: " + "\"" + urlLocation + "\"\n");

    	try 
    	{
			Document doc = Jsoup.connect(urlLocation).get();
			
			//System.out.println(doc.title());
			
			Elements divs = doc.select("p, h1, h2, h3, h4, h5, h6, span, a, li, title");
						
			for (Element div : divs) 
			{
				String[] arr = div.text().split(" ");    

				 for ( String word : arr) 
				 {
					 word = CleanStr(word);
					 
					 if(blacklist != null)
					 {
						 if(!(blacklist.contains(word)) && !word.equals(""))					 
							 wordValueMap.add(word);
					 }
					 else
						 wordValueMap.add(word); 
				 }
			}
			
			System.out.println("Populated Map: " + wordValueMap.size());
			System.out.println(wordValueMap.firstTen());
			System.out.println("Best: " + wordValueMap.mostUsed() + " : " + wordValueMap.mostUsedCount());
		}
    	catch (IOException e) 
    	{
    		String message = "Unable to read url";
    		String otherMessage;

    		urlLocation = Globals.getInstance().getBackupStopwords();
    		
    		otherMessage = "Using the internal backup file: " + "\"" + urlLocation + "\"\n";
    		
    		
			if(Globals.getInstance().getIsGui())
				new UserUIDialog(message + "\n" + otherMessage,UserUIMessageType.ERROR);
			else
				System.out.println(message + "\n" + otherMessage);
		} 	
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
    
    private String CleanStr(String str)
    {
    	if(str != null && str != "" && str.length() > 0)
    	{    	
	    	String word = str;
	    	char[] wordArr = new char[str.length()];
	    	
	    	word = str.replaceAll("[^A-Za-z0-9 ]", "");    	
	    	word = word.toLowerCase();	    	
	    	
	    	wordArr = word.toCharArray();
	    	
	    	if(wordArr.length > 0)
	    	{	    	
	    		if(Character.isAlphabetic(wordArr[0]))
	    		{
	    			String temp;
	    			
	    			wordArr[0] = Character.toUpperCase(wordArr[0]);	   		
	    			temp = new String(wordArr);
	    			
	    			return temp;
	    		}
	    	}	    	
	    }
    	    	
    	return str;
    }
}
