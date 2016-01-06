package ie.gmit.sw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class UserUI 
{
	private int HI = 140;
	private int WI = 600;
	private int FIELD_HIGH = 25;
	private int MIN_BUFF = 3;
	private int BORDER = 4;
	
	private JFrame jFrame;
	private JPanel jPanel;
	private JMenuBar menuBar;
	private JMenu menu;		
	private JMenuItem menuItem;
	private JButton okButton;
	private TextField textfieldFileUrl;
	private TextField textfieldBlackListFile;
	
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
		
		okButton = new JButton("Submit");
		okButton.setPreferredSize(new Dimension(WI - (MIN_BUFF * BORDER),FIELD_HIGH * 2));
		okButton.setSize(new Dimension(WI - (MIN_BUFF * BORDER),FIELD_HIGH));
		okButton.setLocation(MIN_BUFF, (FIELD_HIGH * 2) + MIN_BUFF * 3);
		jPanel.add(okButton);
		
		textfieldFileUrl = new TextField("Enter a URL or FilePath here for the word map you wish to create");
		textfieldFileUrl.setPreferredSize(new Dimension(WI - (MIN_BUFF * BORDER),FIELD_HIGH));
		textfieldFileUrl.setSize(new Dimension(WI - (MIN_BUFF * BORDER),FIELD_HIGH));
		textfieldFileUrl.setLocation(MIN_BUFF, MIN_BUFF);
		jPanel.add(textfieldFileUrl);
		
		textfieldBlackListFile = new TextField("Enter a file path to a file of blacklist words");
		textfieldBlackListFile.setPreferredSize(new Dimension(WI - (MIN_BUFF * BORDER),FIELD_HIGH));
		textfieldBlackListFile.setSize(new Dimension(WI - (MIN_BUFF * BORDER),FIELD_HIGH));
		textfieldBlackListFile.setLocation(MIN_BUFF, MIN_BUFF + FIELD_HIGH);
		jPanel.add(textfieldBlackListFile);
		
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
		worker.doWork(getTextFieldBlackListFile(),getTextFieldFile());
	}	
	
	private String getTextFieldFile()
	{
		System.out.println(textfieldFileUrl.getText());
		return textfieldFileUrl.getText();
	}
	
	private String getTextFieldBlackListFile()
	{
		System.out.println(textfieldBlackListFile.getText());
		return textfieldBlackListFile.getText();
	}
}
