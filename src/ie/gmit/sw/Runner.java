package ie.gmit.sw;

import ie.gmit.sw.ui.UserUI;

/**
 * This is the basic runner class.
 * It takes command line arguments to decide whether to us a gui or not
 * @author Andy Sweeney - G00237144
 *
 */

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
					Globals.getInstance().setIsGui(true);
					UserUI projectUI = new UserUI();
					projectUI.Init(args[1],args[2]);
					
				}
				else if(args[0].equalsIgnoreCase(("cmd")))
				{
					commandLineText();
					Globals.getInstance().setIsGui(false);
					
					Worker worker = new Worker();
					worker.doWork(args[1],args[2]);
					
				}
				else
				{
					commandLineText();
					Globals.getInstance().setIsGui(false);
					
					Worker worker = new Worker();
					worker.doWork(args[1],args[2]);
					
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
