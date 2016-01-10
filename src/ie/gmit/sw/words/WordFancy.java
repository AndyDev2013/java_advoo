package ie.gmit.sw.words;

import java.awt.Font;

import ie.gmit.sw.Globals;

public class WordFancy extends Word
{
	public WordFancy(Bounds bounds, String text, int size, WordType wt)
	{
		super(bounds, text, size, wt);
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, Globals.getInstance().getFontSize()));
	}
}
