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

public class ReallySimpleWordCloud 
{		
	private ArrayList<Bounds> boundsList;
	private int ImageDimension = 3000;
	private int fontSize = 120;
	
	public ReallySimpleWordCloud(ArrayList words) throws IOException
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
				
		while(wordCount < 100)
		{
			String currWord = (String) words.get(wordCount);
			Bounds currBounds = null;
						
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
			graphics.setColor(randomColor());
			graphics.setFont(font);
			
			int strWidth = (int)(font.getStringBounds(currWord, frc).getWidth());
			int strHeight = (int)(font.getStringBounds(currWord, frc).getHeight());
			
			currBounds = new Bounds(strWidth,strHeight,getRandomPosition(strWidth,strHeight,ImageDimension,ImageDimension));
			
			while(!CheckBounds(currBounds))
			{
				currBounds = new Bounds(strWidth,strHeight,getRandomPosition(strWidth,strHeight,ImageDimension,ImageDimension));
			}
			
			//System.out.println(currBounds.getX() + " " + currBounds.getY() + " " + currBounds.getWidth() + " " + currBounds.getHeight());
			
			boundsList.add(currBounds);
			
			graphics.drawString(currWord, currBounds.getX(), currBounds.getY());				
	
			if(wordCount % 15 == 0)			
				fontSize -= 2;
			else
				fontSize -= 1;
			
			if(fontSize < 0)
				System.out.println("Font: " + wordCount);
			
			++wordCount;
		}		
		
		System.out.println(wordCount + " : " + fontSize);
		
		graphics.dispose();
		
		ImageIO.write(image, "png", new File("image.png"));
		
		System.out.println("\nFinished");
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
	
	private Color randomColor()
	{
		Random random = new Random();
		
		int R = random.nextInt(256);
		int G = random.nextInt(256);
		int B= random.nextInt(256);
		
		return new Color(R, G, B);
	}
}
