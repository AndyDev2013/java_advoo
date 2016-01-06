package ie.gmit.sw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserUI 
{
	private int HI = 82;
	private int WI = 600;
	private int btnWi = 100;
	private int btnHi = 25;
	private int MIN_BUFF = 3;
	private int txtWi = WI - btnWi;
	
	private JFrame jFrame;
	private JPanel jPanel;
	private JMenuBar menuBar;
	private JMenu menu;		
	private JMenuItem menuItem;
	private Button okButton;
	private TextField textfield;
	
	public void Init()
	{			
		jFrame = new JFrame("Word Cloud Application");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		jFrame.setPreferredSize(new Dimension(WI,HI));
				
		jPanel = new JPanel();
		menuBar = new JMenuBar();
		menu = new JMenu("?");
		menuItem = new JMenuItem("About", null);
		
		jFrame.add(jPanel);
		jPanel.setLayout(null);
		menuBar.add(menu);
		menu.add(menuItem);
		jFrame.setJMenuBar(menuBar);
		
		okButton = new Button("Submit");
		okButton.setPreferredSize(new Dimension(btnWi,btnHi));
		okButton.setSize(new Dimension(btnWi - (MIN_BUFF * 2),btnHi));
		okButton.setLocation(txtWi - MIN_BUFF , MIN_BUFF - 2);
		jPanel.add(okButton);
		
		textfield = new TextField("Enter a URL or FilePath here");
		textfield.setPreferredSize(new Dimension(txtWi,btnHi));
		textfield.setSize(new Dimension(txtWi,btnHi));
		textfield.setLocation(MIN_BUFF, MIN_BUFF);
		jPanel.add(textfield);
		
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
		jFrame.setResizable(false);
		
		menuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {

				String messsage = "The project was created for the GMIT Advanced OO module for John Healy."
		    			+ "\nThis project was programmed by Andrew Sweeney - G00237144.";
				
				String title = "About Word Cloud";
				
				new UserUIDialog(title,messsage,UserUIMessageType.PLAIN);
		    }
		});
		
		okButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	OkButton();
		    }
		});
	}
	
	public void OkButton()
	{
		System.out.println("Submitted file/url location");
		
		Worker worker = new Worker();
		worker.doWork(getTextField());
	}	
	
	public String getTextField()
	{
		return textfield.getText();
	}
}
