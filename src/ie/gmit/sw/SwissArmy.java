package ie.gmit.sw;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SwissArmy 
{
	public boolean isUrl(String possibleUrl)
	{
		try 
		{
		  @SuppressWarnings("unused")
		  URL url = new URL(possibleUrl);
		} 
		catch (MalformedURLException e)
		{
			return false;
		}
		
		return true;
	
	}// Tells whether the string is a possible Url or not
	
	public boolean isFile(String possibleFile)
	{
		File f = new File(possibleFile);
		
		if(f.exists() && !f.isDirectory()) 
		    return true;
		else
			return false;
		
	}// Tells whether the string is a file that exists in the directory or not
	
	public boolean validStr(String isFileOrUrl)
	{
		boolean isFile = isFile(isFileOrUrl);
		boolean isUrl = isUrl(isFileOrUrl);
		
		if(isFile || isUrl)
			return true;
		else
			return false;
	}
	
	public void validStrDialog(String isFileOrUrl)
	{
		String successTitle = "Valid File";
		String warningTitle = "File Not Valid";
		String goodMsg = "The file or url: \n\"" + isFileOrUrl + "\"\nis VALID";
		String badMsg = "The file or url: \n\"" + isFileOrUrl + "\"\nis NOT VALID";
		
		boolean flag = validStr(isFileOrUrl);		
		UserUIDialog uid;
		
		if(flag)
			uid = new UserUIDialog(successTitle,goodMsg,UserUIMessageType.PLAIN);
		else
			uid = new UserUIDialog(warningTitle,badMsg,UserUIMessageType.ERROR);		
	}
	
	
}
