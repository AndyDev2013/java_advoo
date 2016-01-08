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
	private int ImageDimension = 2000;
	private int fontSize = 250;
	private Color currentColor;
	
	public WordCloud(ArrayList words) throws IOException
	{
		BufferedImage image = new BufferedImage(ImageDimension, ImageDimension, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics plaingraphics = image.getGraphics();	
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true); 		
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
				
		boundsList = new ArrayList<Bounds>();
		
		plaingraphics.setColor(new Color(238,238,238));
		plaingraphics.fillRect(0, 0, ImageDimension, ImageDimension);
		
		///////////////////////////////////////////////////////////////
		
		int wordCount = 0;
				
		while(wordCount < Globals.getInstance().getWordLimit())
		{
			String currWord = (String) words.get(wordCount);
			Bounds currBounds = null;
			
			int strWidth = (int)(font.getStringBounds(currWord, frc).getWidth());
			int strHeight = (int)(font.getStringBounds(currWord, frc).getHeight());
			
			currBounds = new Bounds(strWidth,strHeight,getRandomPosition(strWidth,strHeight,ImageDimension,ImageDimension));
			
			while(!CheckBounds(currBounds))
			{
				currBounds = new Bounds(strWidth,strHeight,getRandomPosition(strWidth,strHeight,ImageDimension,ImageDimension));
			}
			
			boundsList.add(currBounds);				
			
			Wordable word = SingleWordFactory.getInstance().CreateWord(currBounds, currWord, fontSize);	
			
			plaingraphics.setFont(word.getFont());
			
			plaingraphics.setColor(randomColor());
			
			plaingraphics.drawString(word.getText(), word.getBounds().getX(), word.getBounds().getY());	
	
			if(wordCount < 3)			
				fontSize -= 40;
			else
				fontSize -= 2;			
						
			//System.out.println(word.getText() +  " : " + wordCount + " " + fontSize);
			
			font = word.getFont();
			++wordCount;
		}		
	
		plaingraphics.dispose();
		
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
			
		return color[pos];
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
