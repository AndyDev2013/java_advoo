package ie.gmit.sw;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class UserUIDialog 
{
	private Frame frame;
	
	public UserUIDialog(String message,String title,UserUIMessageType uim)
	{
		if(message != null && !(message.equals("")))
		{		
			if(uim == UserUIMessageType.ERROR)
			{			
				JOptionPane.showMessageDialog(frame,title,message,JOptionPane.ERROR_MESSAGE, null);				
			}
			else if(uim == UserUIMessageType.WARNING)
			{
				JOptionPane.showMessageDialog(frame,title,message,JOptionPane.WARNING_MESSAGE, null);
			}	
			else if(uim == UserUIMessageType.SUCCESS)
			{
				JOptionPane.showMessageDialog(frame,title,message,JOptionPane.QUESTION_MESSAGE, null);
			}
			else if(uim == UserUIMessageType.PLAIN)
			{
				JOptionPane.showMessageDialog(frame,title,message,JOptionPane.PLAIN_MESSAGE, null);
			}
			else
			{
				JOptionPane.showMessageDialog(frame,title,message,JOptionPane.PLAIN_MESSAGE, null);
			}
		}
	}
}
