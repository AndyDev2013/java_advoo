package ie.gmit.sw.words;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import ie.gmit.sw.Globals;

public class WordFunny extends Word
{
	public WordFunny(Bounds bounds, String text, int size, WordType wt) 
	{
		super(bounds, text, size, wt);
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, Globals.getInstance().getFontSize()));
	}	
	
	public Color getColor()
	{
		Random random = new Random();
		
		int R = random.nextInt(256);
		int G = random.nextInt(256);
		int B = random.nextInt(256);
		
		return new Color(R, G, B);
	}
}
