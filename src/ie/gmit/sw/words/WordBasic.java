package ie.gmit.sw.words;

import java.awt.Color;
import java.awt.Font;

public class WordBasic implements Wordable
{
	private Bounds bounds;
	private Color color;
	private Font font;
	private String text;
	private int size;

	public WordBasic(Bounds bounds,String text,int size)
	{
		this.bounds = bounds;
		this.text = text;
		this.size = size;
		this.color = Color.black;
		this.font = new Font(Font.SERIF, Font.BOLD, getSize());
	}
	
	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() {
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
