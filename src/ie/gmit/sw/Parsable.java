package ie.gmit.sw;

public interface Parsable 
{
	boolean isFile(String fileLocation);
	boolean isUrl(String fileLocation);
	boolean isFileOrUrl(String fileLocation);	
	void ImportWords(String fileLocation);
}
