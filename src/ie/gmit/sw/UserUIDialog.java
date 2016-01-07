package ie.gmit.sw;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class UserUIDialog 
{
	private Frame frame = new Frame();
	
	public UserUIDialog(String message,UserUIMessageType uim)
	{		
		if(message != null && !(message.equals("")))
		{		
			if(uim == UserUIMessageType.ERROR)
				JOptionPane.showMessageDialog(frame,message,"Error!",JOptionPane.ERROR_MESSAGE, null);	
			else if(uim == UserUIMessageType.WARNING)
				JOptionPane.showMessageDialog(frame,message,"Warning!",JOptionPane.WARNING_MESSAGE, null);
			else if(uim == UserUIMessageType.SUCCESS)
				JOptionPane.showMessageDialog(frame,message,"Success!",JOptionPane.INFORMATION_MESSAGE, null);
			else if(uim == UserUIMessageType.PLAIN)
				JOptionPane.showMessageDialog(frame,message,"",JOptionPane.PLAIN_MESSAGE, null);
		}
	}
	
	public UserUIDialog(String title,String message)
	{		
		JOptionPane.showMessageDialog(frame,message,title,JOptionPane.PLAIN_MESSAGE, null);
	}
}
