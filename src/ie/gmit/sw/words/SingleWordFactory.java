package ie.gmit.sw.words;

public class SingleWordFactory
{
	private static int count = -1;

	private static SingleWordFactory instance = null;
	protected SingleWordFactory() {}
	
    public static SingleWordFactory getInstance()
    {
       if(instance == null) 
       {
          instance = new SingleWordFactory();
       }
      
       return instance;
    }
    
    public Wordable CreateWord(Bounds bounds,String text,int size)
    {
    	WordType wt = WordType.getRandomWordType();
    	
    	++count;
   
    	if (wt == WordType.BASIC)
    	  return new WordBasic(bounds, text,size);
	    else if (wt == WordType.FANCY)
	      return new WordFancy(bounds, text,size);
	    else if (wt == WordType.FUNNY)
	      return new WordFunny(bounds, text,size);
    	 
    	return null;    	
    }
}
