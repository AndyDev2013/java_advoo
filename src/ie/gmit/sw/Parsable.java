package ie.gmit.sw;

import java.util.List;

public interface Parsable 
{
	boolean isFile(String fileLocation);
	boolean isUrl(String fileLocation);
	boolean isFileOrUrl(String fileLocation);	
	List<String> ImportWords(String fileLocation);
}
