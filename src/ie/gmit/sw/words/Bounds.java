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
