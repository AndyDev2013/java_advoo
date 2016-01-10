package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ie.gmit.sw.ui.UserUIDialog;
import ie.gmit.sw.ui.UserUIMessageType;

/**
 * This class is manages the creation of a list of ordered words.
 * You can pass in a file or url to create a ordered list of words.
 * You can create a blacklist for those ordered words to keep common words out.
 * @author Andy Sweeney - G00237144
 * @version 1.0
 */
public class Importer implements Parserable
{
	private WordValueMap wordValueMap;
	private HashSet<String> blacklist;
	private int minWordLen = 3;
	
	/**
	* Takes in a file location string.
	* Method figures out where a file or url is given.
	* The url or file is parsed in and a sorted and an ordered list of words is returned
	* @param fileOrUrl String
	* @return a List of Strings
	*/ 
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
	
	/**
	* Takes in a file location string.
	* Creates the blacklist internally for the Importer.
	* If no file exists a backup internal stopwords file is used.
	* @param fileUrl String
	*/ 	
	public void ImportBlackList(String fileUrl)
	{
		System.out.println("--- Blacklist File ---");
		System.out.println("\nTrying to import blacklist file: " + "\"" + fileUrl + "\"");
		
		Reader currentReader = null;
		blacklist = new HashSet<String>();	
		
		if(isFile(fileUrl))
		{
			try
			{
				currentReader = new FileReader(fileUrl);
			} 
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			if(!(Globals.getInstance().getIsDebug()))
	    	{
	    		ClassLoader classLoader = getClass().getClassLoader();
	    		
	        	if(classLoader.getResourceAsStream(fileUrl) != null) 		
	    			currentReader = new InputStreamReader(getClass().getResourceAsStream(fileUrl)); 
	    		else
	    		{
	    			System.out.println("Using the internal backup file : " + "\"" + fileUrl + "\"\n");
	    			currentReader = new InputStreamReader(getClass().getResourceAsStream("/" + Globals.getInstance().getBackupStopwords()));
	    		}
	    	}
	    	else
	    	{
	    		try 
	    		{    			
	    			System.out.println("Regular File Reader");
					currentReader = new FileReader(fileUrl);
					
					if(!(isFile(fileUrl)))
					{
						fileUrl = Globals.getInstance().getBackupStopwords();
						
						System.out.println("Using the internal backup file: " + "\"" + fileUrl + "\"\n");
					}
				}
	    		catch (FileNotFoundException e)
	    		{}   		
	    	}			
			
		}
	
    	try (BufferedReader buffRead = new BufferedReader(currentReader))
		{
    		System.out.println("Read file for blacklist: " + fileUrl);
    		
			String currentLine;

			while ((currentLine = buffRead.readLine()) != null)
			{
				String[] arr = currentLine.split(" ");    

				 for ( String word : arr) 
				 {
					 word = CleanStr(word);
					 					 
					 if(word != null)
					 {						 
						 if(!word.equals(""))
							 word.toUpperCase();
						 
						 if(!(blacklist.contains(word)) && !word.equals(""))
						 {
							 blacklist.add(word);
						 }						 
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
	
	/**
	* Takes in a file location string.
	* Method figures out whether the file exists or not.
	* If not file is at that location then uses the internal stopword.txt
	* @param fileOrUrl String
	*/ 	
    private List<String> ReadFile(String fileOrUrl)
    {
    	Reader currentReader = null;
    	
    	if(!isFile(fileOrUrl))
    	{
    	
	    	if(!(Globals.getInstance().getIsDebug()))
	    	{
	    		ClassLoader classLoader = getClass().getClassLoader();
	    		
	    		if(classLoader.getResourceAsStream(fileOrUrl) != null) 		
	    			currentReader = new InputStreamReader(getClass().getResourceAsStream("/" + fileOrUrl)); 
	    		else
	    			currentReader = new InputStreamReader(getClass().getResourceAsStream("/" + Globals.getInstance().getBackupStopwords()));
	    	}
	    	else
	    	{
	    		System.out.println("Trying to read FILE: " + "\"" + fileOrUrl + "\"\n");
	    		
	    		try
	    		{
					currentReader = new FileReader(fileOrUrl);
				}
	    		catch (FileNotFoundException e) 
	    		{
					e.printStackTrace();
				}
	    	}
    	} 
    	else
    	{
			try
    		{
				currentReader = new FileReader(fileOrUrl);
			}
    		catch (FileNotFoundException e1) 
    		{
				e1.printStackTrace();
			}
    	}
    	
    	try (BufferedReader buffRead = new BufferedReader(currentReader))
		{
    		System.out.println("Read file: " + fileOrUrl);
    		
			String currentLine;

			while ((currentLine = buffRead.readLine()) != null)
			{
				String[] arr = currentLine.split(" ");    

				 for ( String word : arr) 
				 {
					 word = CleanStr(word);
					 
					 if(word != null)
					 {
						 word = word.toUpperCase();
						 
						 if(blacklist != null)
						 {
							 if(!(blacklist.contains(word)) && word.length() > minWordLen)					 
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
    
	/**
	* Takes in a file location string.
	* Reads the url using Jsoup
	* Creates a ordered list of strings for the wordcloud
	* @param urlLocation String
	* @return returns an ordered List of Strings for the worldcloud
	*/     
    public List<String> ReadUrl(String urlLocation)
    {
    	System.out.println("Trying to read URL: " + "\"" + urlLocation + "\"\n");

    	try 
    	{
			Document doc = Jsoup.connect(urlLocation).get();
			
			//System.out.println(doc.title());
			
			Elements divs = doc.select("p, h1, h2, h3, h4, h5, h6, span, a, li, title, div");
						
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

	/**
	* Takes in a file location string.
	* @param isFileOrUrl String
	* @return whether the file location is a file or url
	*/ 
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

	/**
	* Takes in a file location string.
	* @param possibleUrl String
	* @return figures out if the string is a url using UrlValidator
	*/ 
	public boolean isUrl(String possibleUrl)
	{
		String[] schemes = {"http","https"};
		
		UrlValidator urlValidator = new UrlValidator(schemes);
		
		if (urlValidator.isValid(possibleUrl)) 
		   return true;
		else
		   return false;
	
	}// Tells whether the string is a possible Url or not

	/**
	* Takes in a file location string.
	* @param possibleFile String
	* @return figures out if the string is a file
	*/ 	
    public boolean isFile(String possibleFile)
	{
		File f = new File(possibleFile);
		
		if(f.exists() && !f.isDirectory()) 
		    return true;
		else
			return false;
		
	}// Tells whether the string is a file that exists in the directory or not
  
	/**
	* Cleans a string by removing all special characters and converting the word to uppercase.
	* @param str String
	* @return an String
	*/ 	    
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
  
	/**
	* Gets the current WorldValueMap which holds the a map of keyvalue pairs with words and their frequency.
	* @return WordValueMap
	*/ 	    
    public WordValueMap getWordValueMap()
    {
    	return this.wordValueMap;
    }
}
