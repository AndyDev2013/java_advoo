package ie.gmit.sw.words;

import java.awt.Font;

import ie.gmit.sw.Globals;

/**
 * A type of Word style
 * @author Andy Sweeney - G00237144 
 * @version 1.0 
 */
public class WordBasic extends Word
{
	/**
	 * Creates a word of a particular style
	 * @param bounds the bounds object
	 * @param text the actual text of the word
	 * @param size the actual size of the word
	 * @param wt the actual type of the word
	 */
	public WordBasic(Bounds bounds, String text, int size, WordType wt)
	{
		super(bounds, text, size, wt);
		setFont(new Font(Font.SERIF, Font.PLAIN, Globals.getInstance().getFontSize()));
	}	
}
