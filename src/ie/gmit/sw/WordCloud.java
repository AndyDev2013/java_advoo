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

import org.omg.CosNaming.IstringHelper;

import ie.gmit.sw.words.Bounds;
import ie.gmit.sw.words.SingleWordFactory;
import ie.gmit.sw.words.Wordable;

public class WordCloud 
{		
	private ArrayList<Bounds> boundsList;
	private int ImageDimension = 2000;
	private int bufferSpace = 10;
	
	public WordCloud(ArrayList<String> words) throws IOException
	{
		BufferedImage image = new BufferedImage(ImageDimension, ImageDimension, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics plaingraphics = image.getGraphics();	
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true); 		
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, Globals.getInstance().getFontSize());
				
		boundsList = new ArrayList<Bounds>();
		
		plaingraphics.setColor(new Color(236,236,236));
		plaingraphics.fillRect(0, 0, ImageDimension, ImageDimension);
		
		int wordCount = 0;
		int radius = 0;
		float increment = 0;
		
		while(wordCount < Globals.getInstance().getWordLimit())
		{
			String currWord = (String) words.get(wordCount);
			Bounds currBounds = null;
			
			int strWidth = (int)(font.getStringBounds(currWord, frc).getWidth());
			int strHeight = (int)(font.getStringBounds(currWord, frc).getHeight());
						
			currBounds = new Bounds(strWidth,strHeight,getRandomPositionCircle(radius,increment,strWidth,strHeight,ImageDimension,ImageDimension));
			
			while(!CheckBounds(currBounds))
			{
				if(increment > 360)
				{
					increment = 0;		
					radius++;
				}
				
				increment += 0.01;
				
				currBounds = new Bounds(strWidth,strHeight,getRandomPositionCircle(radius,increment,strWidth,strHeight,ImageDimension,ImageDimension));				
			}	
			
			boundsList.add(currBounds);				
			
			Wordable word = SingleWordFactory.getInstance().CreateWord(currBounds, currWord, Globals.getInstance().getFontSize());	
			
			plaingraphics.setFont(word.getFont());
			
			plaingraphics.setColor(RandomColor());
			
			plaingraphics.drawString(word.getText(), word.getBounds().getX(), word.getBounds().getY());	
			
			int fSize = Globals.getInstance().getFontSize();
			
			if(wordCount < 10)
				fSize -= 5;
			else
				fSize -= 0.2;			
									
			font = word.getFont();
			
			Globals.getInstance().setFontSize(fSize);
			
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
			if(checkMe.getX() < bound.getX() + bound.getWidth() + bufferSpace && checkMe.getX() + checkMe.getWidth() + bufferSpace > bound.getX() && checkMe.getY() < bound.getY() + bound.getHeight() + bufferSpace && checkMe.getHeight() + checkMe.getY() + bufferSpace > bound.getY())
			{
				return false;
			}			
		}	
		
		if(checkMe.getX() + checkMe.getWidth() < ImageDimension - bufferSpace || checkMe.getY() + checkMe.getHeight() < ImageDimension - bufferSpace)
		{
			return true;
		}
		else
			return false;
	}
	
	private Bounds getRandomPositionCircle(int radius,float increment, int wid,int hi,int pagewidth,int pageheight)
	{			
		double angle = increment * Math.PI / 180;
		int x = (int)((ImageDimension / 2) + radius * Math.cos(angle));
		int y = (int)((ImageDimension / 2) + radius * Math.sin(angle));
		
		return new Bounds(wid,hi,x,y);		
	}

	private Color RandomColorPreset() 
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
	
	private Color RandomColor()
	{
		Random random = new Random();
		
		int R = random.nextInt(256);
		int G = random.nextInt(256);
		int B = random.nextInt(256);
		
		return new Color(R, G, B);
	}
}
