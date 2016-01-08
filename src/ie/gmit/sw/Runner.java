package ie.gmit.sw;

import ie.gmit.sw.ui.UserUI;

public class Runner 
{	
	public static void main(String args[])
	{	
		if(Globals.getInstance().getIsDebug())
		{
			UserUI projectUI = new UserUI();
			projectUI.Init();		
		}
		else
		{		
			if(args.length > 2)
			{			
				if(args[0].equalsIgnoreCase(("gui")))
				{		
					UserUI projectUI = new UserUI();
					projectUI.Init(args[1],args[2]);
					
					Globals.getInstance().setIsGui(true);
				}
				else if(args[0].equalsIgnoreCase(("cmd")))
				{
					commandLineText();
					
					Worker worker = new Worker();
					worker.doWork(args[1],args[2]);
					
					Globals.getInstance().setIsGui(false);
				}
				else
				{
					commandLineText();
					
					Worker worker = new Worker();
					worker.doWork(args[1],args[2]);
					
					Globals.getInstance().setIsGui(false);
				}
			}
			else
			{
				System.out.println("Wrong amount of parameters passed.");
			}
		}		
	}	
	
	private static void commandLineText()
	{
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("  Word Cloud API - 2015/2016  ");
		System.out.println("        Andrew Sweeney        ");
		System.out.println("          G00237144           ");
		System.out.println("------------------------------");
		System.out.println();
	}
	
}
