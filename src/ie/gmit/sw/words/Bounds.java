package ie.gmit.sw.words;

public class Bounds
{
	private int x,y,wid,hi;
	
	public Bounds(int w,int h,Bounds bounds)
	{
		this.wid = w;
		this.hi = h;
		this.x = bounds.x;
		this.y = bounds.y;
	}
	
	public Bounds(Bounds bounds)
	{
		this.x = bounds.x;
		this.y = bounds.y;
	}
	
	public Bounds(int w,int h,int x,int y)
	{
		this.wid = w;
		this.hi = h;
		this.x = x;
		this.y = y;
	}
	
	public boolean doIAvoidCollision(Bounds checkMe,int bufferSpace)
	{
		if(checkMe.getX() < this.getX() + this.getWidth() + bufferSpace && checkMe.getX() + checkMe.getWidth() + bufferSpace > this.getX() && checkMe.getY() < this.getY() + this.getHeight() + bufferSpace && checkMe.getHeight() + checkMe.getY() + bufferSpace > this.getY())
		{
			return false;
		}	
		else
		{
			return true;
		}
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}	
	
	public int getWidth()
	{
		return this.wid;
	}
	
	public int getHeight()
	{
		return this.hi;
	}
}
