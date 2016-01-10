package ie.gmit.sw;

/**
 * This class is an example of a Singleton. It's created to hold most of the global variables used in the project
 * @author Andy Sweeney - G00237144
 *
 * @version 1.0
 */
public class Globals 
{	
   private static Globals instance = null;
   protected Globals() {}
 
   private boolean DEBUG = false; // Debug mode represents the code being run from eclipse otherwise its run from jar
   private boolean GUI = false;
   
   private String backUpStopwords = "txt/stopwords.txt";
   private String backUpWordCloud = "txt/warandpeace.txt";
   
   private int WordLimit = 80;
   private int fontSize = 180;
   private int ImageDimension = 2000;
   private int minFontSize = 10;

   /**
   * Returns the singleton instance of Globals.
   * @return an Globals
   */   
	public static Globals getInstance()
    {
		if(instance == null) 
		{
		   instance = new Globals();
		}
  
		return instance;
    }	

	/**
	* Returns the minimum font size.
	* @return an int
	*/   
    public int getMinFontSize()
    {
    	return minFontSize;
    }

	/**
	* Sets the minimum font size.
	* @param minFontSize int
	*/   
	public void setMinFontSize(int minFontSize) 
	{
		this.minFontSize = minFontSize;
	}	

	/**
	* Returns the image dimension used to set the length and height of the image
	* @return an int
	*/   	
   	public int getImageDimension()
    {
	   return ImageDimension;
    }

	/**
	* Sets the length and height variable of the image
	* @param imageDimension int
	*/  
	public void setImageDimension(int imageDimension) 
	{
		ImageDimension = imageDimension;
	}

	/**
	* Returns the current font size
	* @return an int
	*/  
	public int getFontSize() 
    {
	   return fontSize;
    }

	/**
	* Sets the current font size
	* @param fontSize int
	*/  
	public void setFontSize(int fontSize) 
	{
		this.fontSize = fontSize;
	} 
   
	/**
	* Sets whether the runner is in DEBUG mode or not
	* @param debug boolean
	*/  
	public void setIsDebug(boolean debug)
    {
  	   this.DEBUG = debug;
    }
   
	/**
	* Sets the whether the runner is in GUI mode or not
	* @param gui boolean
	*/  
    public void setIsGui(boolean gui)
    {
 	   this.GUI = gui;
    }
   
	/**
	* Returns whether the runner is in DEBUG mode or not
	* @return an int
	*/  
    public boolean getIsDebug()
    {
	    return this.DEBUG;
    }
   
	/**
	* Returns whether the runner is in GUI mode or not
	* @return an boolean
	*/  
    public boolean getIsGui()
    {
 	   return this.GUI;
    }
 
	/**
	* Returns the limit of words displayed on the word cloud
	* @return an int
	*/  
    public int getWordLimit()
    {
	    return this.WordLimit;
    }
   
    /**
    * Returns the location of the backup stop words file
    * @return an String
    */       
    public String getBackupStopwords()
    {
 	   return this.backUpStopwords;
    }

    /**
    * Returns the location of the backup wordcloud file
    * @return an String
    */     
    public String getBackupWordCloud()
    {
	    return this.backUpWordCloud;
    }
}
