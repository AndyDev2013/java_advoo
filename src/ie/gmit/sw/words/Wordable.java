package ie.gmit.sw.words;

import java.awt.Color;
import java.awt.Font;

public interface Wordable 
{
	public Bounds getBounds();
	public void setBounds(Bounds bounds);
	public Color getColor();
	public void setColor(Color color);
	public Font getFont();
	public void setFont(Font font);
	public String getText();
	public void setSize(int size);
	public int getSize();
	public void setText(String text);	
	public WordType getWt();
	public void setWt(WordType wt);
}
