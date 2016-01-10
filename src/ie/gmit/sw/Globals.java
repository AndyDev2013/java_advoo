package ie.gmit.sw;

public class Globals 
{	
   private static Globals instance = null;
   protected Globals() {}
 
   private boolean DEBUG = true;
   private boolean GUI = false;
   
   private String backUpStopwords = "txt/stopwords.txt";
   private String backUpWordCloud = "txt/warandpeace.txt";
   
   private int WordLimit = 70;
   private int fontSize = 120;
   
   public static Globals getInstance()
   {
      if(instance == null) 
      {
         instance = new Globals();
      }
      
      return instance;
   }
   
   public int getFontSize() 
   {
	   return fontSize;
   }

	public void setFontSize(int fontSize) 
	{
		this.fontSize = fontSize;
	} 
   
   public void setIsDebug(boolean debug)
   {
	   this.DEBUG = debug;
   }
   
   public void setIsGui(boolean gui)
   {
	   this.GUI = gui;
   }
   
   public boolean getIsDebug()
   {
	   return this.DEBUG;
   }
   
   public boolean getIsGui()
   {
	   return this.GUI;
   }
   
   public int getWordLimit()
   {
	   return this.WordLimit;
   }
   
   public String getBackupStopwords()
   {
	   return this.backUpStopwords;
   }
   
   public String getBackupWordCloud()
   {
	   return this.backUpWordCloud;
   }
}
