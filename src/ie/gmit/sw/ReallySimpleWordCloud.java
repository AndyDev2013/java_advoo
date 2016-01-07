package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

class Bounds
{
	private int x,y;
	
	public Bounds(Bounds bounds)
	{
		this.x = bounds.x;
		this.y = bounds.y;
	}
	
	public Bounds(int l,int w)
	{
		this.x = l;
		this.y = w;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}	
}

public class ReallySimpleWordCloud 
{		
	private ArrayList<Bounds> boundsList;
	private int ImageDimension = 1000;
	private int fontSize = 60;
	
	public ReallySimpleWordCloud(ArrayList words) throws IOException
	{
		BufferedImage image = new BufferedImage(ImageDimension, ImageDimension, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();
		
		boundsList = new ArrayList<Bounds>();
		
		///////////////////////////////////////////////////////////////
		
		int wordCount = 0;
		
		while(wordCount < 10)
		{
			String currWord = (String) words.get(wordCount);
			boolean outOfBounds = false;
			Bounds currBounds = null;
			
			//while(!outOfBounds)
			{
				currBounds = new Bounds(getRandomPosition(ImageDimension,ImageDimension));
				
				//outOfBounds = CheckBounds(currBounds);
			}
			
			System.out.println(currWord);			

			Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
			graphics.setColor(randomColor());
			graphics.setFont(font);
			graphics.drawString(currWord, currBounds.getX(), currBounds.getY());
						
			fontSize -= 5;
			
			++wordCount;
		}		
		
		graphics.dispose();
		
		ImageIO.write(image, "png", new File("image.png"));
	}
	
	private boolean CheckBounds(Bounds checkMe)
	{
		boolean flag = false;
		
		for(Bounds bound : boundsList)
		{
			
						
		}
		
		return flag;
	}
	
	private Bounds getRandomPosition(int pagewidth,int pageheight)
	{
		Random random = new Random();
		
		int randomWi = random.nextInt(pagewidth);
		int randomHi = random.nextInt(pageheight);
						
		return new Bounds(randomWi,randomHi);		
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
