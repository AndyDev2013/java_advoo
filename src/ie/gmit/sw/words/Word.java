package ie.gmit.sw.words;

import java.awt.Color;
import java.awt.Font;

public class Word implements Wordable
{
	private Bounds bounds;
	private Color color;
	private Font font;
	private String text;
	private int size;
	private WordType wt;
	
	public Word(Bounds bounds,String text,int size,WordType wt)
	{
		this.bounds = bounds;
		this.text = text;
		this.color = Color.black;
		this.font = new Font(Font.SERIF, Font.BOLD, getSize());
		this.wt = wt;
	}
	
	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public WordType getWt() {
		return wt;
	}

	public void setWt(WordType wt) {
		this.wt = wt;
	}

	public Color getColor() 
	{
		return this.color;
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