package ie.gmit.sw.words;

import java.awt.Font;

import ie.gmit.sw.Globals;

public class WordBasic extends Word
{
	public WordBasic(Bounds bounds, String text, int size, WordType wt)
	{
		super(bounds, text, size, wt);
		setFont(new Font(Font.SERIF, Font.PLAIN, Globals.getInstance().getFontSize()));
	}	
}
