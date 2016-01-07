package ie.gmit.sw;

public class Globals 
{	
   private static Globals instance = null;
   protected Globals() {}
 
   private boolean DEBUG = true;
   private boolean GUI = false;
   
   private String backUpStopwords = "txt/stopwords.txt";
   private String backUpWordCloud = "txt/warandpeace.txt";
   
   public static Globals getInstance()
   {
      if(instance == null) 
      {
         instance = new Globals();
      }
      
      return instance;
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
	   return DEBUG;
   }
   
   public boolean getIsGui()
   {
	   return GUI;
   }
   
   public String getBackupStopwords()
   {
	   return backUpStopwords;
   }
   
   public String getBackupWordCloud()
   {
	   return backUpWordCloud;
   }
}
