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

import javax.imageio.ImageIO;

import ie.gmit.sw.words.Bounds;
import ie.gmit.sw.words.SingleWordFactory;
import ie.gmit.sw.words.Wordable;

public class WordCloud 
{		
	private ArrayList<Bounds> boundsList;
	private int bufferSpace = 10;
	
	public void CreateWordCloud(ArrayList<String> words, WordValueMap worldvaluemap) throws IOException
	{
		System.out.println("Trying to create word cloud");
		
		BufferedImage image = new BufferedImage(Globals.getInstance().getImageDimension(), Globals.getInstance().getImageDimension(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics plaingraphics = image.getGraphics();	
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true); 		
		Font font = new Font(Font.SERIF, Font.BOLD, Globals.getInstance().getFontSize());
				
		boundsList = new ArrayList<Bounds>();
		
		plaingraphics.setFont(font);
		plaingraphics.setColor(new Color(236,236,236));
		plaingraphics.fillRect(0, 0, Globals.getInstance().getImageDimension(), Globals.getInstance().getImageDimension());
		
		int wordCount = 0;
		int radius = 0;
		int lastFrequency = 0;
		float increment = 0;
		
		while(wordCount < Globals.getInstance().getWordLimit() && Globals.getInstance().getFontSize() > Globals.getInstance().getMinFontSize())
		{
			String currWord = (String) words.get(wordCount);
			Bounds currBounds = null;
			
			Wordable word = SingleWordFactory.getInstance().CreateWord(null, currWord, Globals.getInstance().getFontSize());	
			
			font = word.getFont();			
			plaingraphics.setFont(font);	
			
			int strWidth = (int)(font.getStringBounds(currWord, frc).getWidth());
			int strHeight = (int)(font.getStringBounds(currWord, frc).getHeight());
						
			currBounds = new Bounds(strWidth,strHeight,getRandomPositionCircle(radius,increment,strWidth,strHeight,Globals.getInstance().getImageDimension(),Globals.getInstance().getImageDimension()));
			
			if(wordCount == 0)
			{
				lastFrequency = worldvaluemap.getValueForKey(word.getText());
			}
			
			while(!CanPlaceWord(currBounds))
			{
				if(increment > 360)
				{
					increment = 0;		
					radius++;
				}
				
				increment += 0.01;
				
				currBounds = new Bounds(strWidth,strHeight,getRandomPositionCircle(radius,increment,strWidth,strHeight,Globals.getInstance().getImageDimension(),Globals.getInstance().getImageDimension()));				
			}	
			
			word.setBounds(currBounds);
			
			boundsList.add(currBounds);				
											
			plaingraphics.setColor(word.getColor());			
			plaingraphics.drawString(word.getText(), word.getBounds().getX(), word.getBounds().getY());		
			
			int fSize = Globals.getInstance().getFontSize();			
			
			if(worldvaluemap.getValueForKey(word.getText()) < lastFrequency)
			{
				lastFrequency = worldvaluemap.getValueForKey(word.getText());
				
				if(wordCount < 5)									
					fSize -= 20;
				else
					fSize -= 2;
			}
										
			Globals.getInstance().setFontSize(fSize);
			
			++wordCount;
		}		
	
		plaingraphics.dispose();
		
		ImageIO.write(image, "png", new File("image.png"));
		
		System.out.println("\nFinished: " + wordCount);
	}
	
	private boolean CanPlaceWord(Bounds checkMe)
	{		
		boolean placeable = true;
		
		for(Bounds bound : boundsList)
		{	
			if(!(checkMe.doIAvoidCollision(bound,bufferSpace)))
			{
				placeable = false;
			}		
		}	
		
		if(!placeable)
		{
			return false;
		}
		
		if(checkMe.getX() + checkMe.getWidth() < Globals.getInstance().getImageDimension() - bufferSpace || checkMe.getY() + checkMe.getHeight() < Globals.getInstance().getImageDimension() - bufferSpace)
		{
			return true;
		}
		else
			return false;
	}
	
	private Bounds getRandomPositionCircle(int radius,float increment, int wid,int hi,int pagewidth,int pageheight)
	{			
		double angle = increment * Math.PI / 180;
		int x = (int)((Globals.getInstance().getImageDimension() / 2) + radius * Math.cos(angle));
		int y = (int)((Globals.getInstance().getImageDimension() / 2) + radius * Math.sin(angle));
		
		return new Bounds(wid,hi,x,y);		
	}
}
