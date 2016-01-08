package ie.gmit.sw.words;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class WordFunny implements Wordable
{
	private Bounds bounds;
	private Color color;
	private Font font;
	private String text;
	private int size;

	public WordFunny(Bounds bounds,String text,int size)
	{
		this.bounds = bounds;
		this.text = text;
		this.color = RandomColor();
		this.font = new Font(Font.SANS_SERIF, Font.BOLD, getSize());
	}
	
	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public Color getColor() 
	{
		return this.color;		
	}

	public Color RandomColor() 
	{		
		Random random = new Random();
		Color color;	
				
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		
		color = new Color(red,green,blue);
			
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() 
	{
		return this.font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
