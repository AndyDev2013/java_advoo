package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthSeparatorUI;

import ie.gmit.sw.words.Bounds;
import ie.gmit.sw.words.SingleWordFactory;
import ie.gmit.sw.words.WordType;
import ie.gmit.sw.words.Wordable;

public class WordCloud 
{		
	private ArrayList<Bounds> boundsList;
	private int ImageDimension = 3000;
	private int fontSize = 120;
	private Color currentColor;
	
	public WordCloud(ArrayList words) throws IOException
	{
		BufferedImage image = new BufferedImage(ImageDimension, ImageDimension, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();		
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true); 
				
		boundsList = new ArrayList<Bounds>();
		
		graphics.setColor(new Color(238,238,238));
		graphics.fillRect(0, 0, ImageDimension, ImageDimension);
		
		///////////////////////////////////////////////////////////////
		
		int wordCount = 0;
				
		while(wordCount < Globals.getInstance().getWordLimit())
		{
			String currWord = (String) words.get(wordCount);
			Bounds currBounds = null;
						
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
			
			int strWidth = (int)(font.getStringBounds(currWord, frc).getWidth());
			int strHeight = (int)(font.getStringBounds(currWord, frc).getHeight());
			
			currBounds = new Bounds(strWidth,strHeight,getRandomPosition(strWidth,strHeight,ImageDimension,ImageDimension));
			
			while(!CheckBounds(currBounds))
			{
				currBounds = new Bounds(strWidth,strHeight,getRandomPosition(strWidth,strHeight,ImageDimension,ImageDimension));
			}
			
			boundsList.add(currBounds);				
			
			Wordable word = SingleWordFactory.getInstance().CreateWord(currBounds, currWord, fontSize);
			
			graphics.setColor(word.getColor());
			
			graphics.setFont(word.getFont());
			
			graphics.drawString(word.getText(), word.getBounds().getX(), word.getBounds().getY());			
	
			if(wordCount % 15 == 0)			
				fontSize -= 2;
			else
				fontSize -= 1;
			
			if(fontSize < 0)
				System.out.println("Font: " + wordCount);
			
			++wordCount;
		}		
	
		graphics.dispose();
		
		ImageIO.write(image, "png", new File("image.png"));
		
		System.out.println("\nFinished: " + wordCount);
	}
	
	private boolean CheckBounds(Bounds checkMe)
	{		
		for(Bounds bound : boundsList)
		{	
			if(checkMe.getX() < bound.getX() + bound.getWidth() && checkMe.getX() + checkMe.getWidth() > bound.getX() && checkMe.getY() < bound.getY() + bound.getHeight() && checkMe.getHeight() + checkMe.getY() > bound.getY())
			{
				//System.out.println("Collision");
				
				return false; //collision
			}			
		}
		
		return true;
	}
	
	private Bounds getRandomPosition(int wid,int hi,int pagewidth,int pageheight)
	{
		Random random = new Random();
		
		int x = random.nextInt(pagewidth - wid);
		int y = random.nextInt(pageheight);
		
		if(y < hi)
		{
			y += hi;
		}
						
		return new Bounds(wid,hi,x,y);		
	}

	private Color RandomColor() 
	{		
		Random random = new Random();
		Color []color = {
				Color.RED,
				Color.BLUE,
				Color.GREEN,
				Color.CYAN,
				Color.PINK,
				Color.ORANGE
	        };
				
		int pos = random.nextInt(color.length);
			
		Color selected = color[pos];
		
		System.out.println("Here: " + selected + " " + pos);
		
		return Color.ORANGE;
	}
	
	private Color randomColor()
	{
		Random random = new Random();
		
		int R = random.nextInt(256);
		int G = random.nextInt(256);
		int B= random.nextInt(256);
		int A=  256;
		
		return new Color(R, G, B);
	}
}
