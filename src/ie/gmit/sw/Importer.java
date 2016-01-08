package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ie.gmit.sw.ui.UserUIDialog;
import ie.gmit.sw.ui.UserUIMessageType;

public class Importer implements Parsable
{
	private WordValueMap wordValueMap;
	private HashSet<String> blacklist;
	
	public List<String> ImportWords(String fileOrUrl)
	{
    	System.out.println("\n--- WordCloudFile ---");
		System.out.println("\nTrying to import wordcloud file: " + "\"" + fileOrUrl + "\"");
		
		wordValueMap = new WordValueMap();
		
		if(isFileOrUrl(fileOrUrl))
		{
			if(isFile(fileOrUrl))
				return ReadFile(fileOrUrl);
			else
				return ReadUrl(fileOrUrl);
		}	
		else
		{
			String message = "WordCloud file/url isn't valid.";
			String otherMessage;
			
			fileOrUrl = Globals.getInstance().getBackupWordCloud();
			
			otherMessage = "Using the internal backup file: " + "\"" + fileOrUrl + "\"\n";
				
			System.out.println(message);
			System.out.println(otherMessage);
			
			if(Globals.getInstance().getIsGui())
				new UserUIDialog(message + "\n" + otherMessage, UserUIMessageType.WARNING);
		
			return ReadFile(fileOrUrl);
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
					 {
						 word = word.toUpperCase();
						 blacklist.add(word);
					 }
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
	
    private List<String> ReadFile(String fileOrUrl)
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
			
			return wordValueMap.getOrderedList();
		} 
    	catch (IOException e) 
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
			
			return wordValueMap.getOrderedList(); 
		}	    	
    }	
    
    public List<String> ReadUrl(String urlLocation)
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
					 
					 if(word != null)
					 {						 
						 if(blacklist != null)
						 {
							 if(!(blacklist.contains(word)) && !word.equals(""))					 
								 wordValueMap.add(word);
						 }
						 else
							 wordValueMap.add(word); 
					 }
				 }
			}
			
			System.out.println("Populated Map: " + wordValueMap.size());
			
			return wordValueMap.getOrderedList();						
		}
    	catch (IOException e) 
    	{
    		String message = "Unable to read url";
    		String otherMessage;

    		urlLocation = Globals.getInstance().getBackupWordCloud();
    		
    		otherMessage = "Using the internal backup file: " + "\"" + urlLocation + "\"\n";    		
    		
			if(Globals.getInstance().getIsGui())
				new UserUIDialog(message + "\n" + otherMessage,UserUIMessageType.ERROR);
			else
				System.out.println(message + "\n" + otherMessage);
			
			return ReadFile(urlLocation);
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
    	if(str != null && str != "" && str.length() > 1)
    	{    	
	    	String word = str;
	    	
	    	word = str.replaceAll("[^A-Za-z ]", "");    	
	    	word = word.toUpperCase();    
	    	
	    	return word; 	
	    }
    	else    		   	    	
    		return null;
    }
    
    public WordValueMap getWordValueMap()
    {
    	return this.wordValueMap;
    }
}
