package views;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainScreen extends JPanel implements ActionListener 
{
	
	protected JButton buttonRenting, buttonReturn, buttonList, buttonAlter;
	JLabel labelMainScreen;
	
	public MainScreen() 
	{
		labelMainScreen = new JLabel("Main Screen"); 
		add(labelMainScreen);
		
		buttonRenting = new JButton("Renting");
		buttonRenting.setVerticalTextPosition(AbstractButton.CENTER);
		buttonRenting.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonRenting.setActionCommand("Renting");
		buttonRenting.setPreferredSize(new Dimension(200,30));
		
		buttonReturn = new JButton("Return");
		buttonReturn.setVerticalTextPosition(AbstractButton.CENTER);
		buttonReturn.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonReturn.setActionCommand("Return");
		buttonReturn.setPreferredSize(new Dimension(200,30));
		
		buttonList = new JButton("List Members");
		buttonList.setVerticalTextPosition(AbstractButton.CENTER);
		buttonList.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonList.setActionCommand("List");
		buttonList.setPreferredSize(new Dimension(200,30));
		
		buttonAlter = new JButton("Add/Remove Game/Console");
		buttonAlter.setVerticalTextPosition(AbstractButton.CENTER);
		buttonAlter.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonAlter.setActionCommand("Alter");
		buttonAlter.setPreferredSize(new Dimension(200,30));
		
		buttonRenting.addActionListener(this);
		buttonReturn.addActionListener(this);
		buttonList.addActionListener(this);
		buttonAlter.addActionListener(this);
		
		add(buttonRenting);
		add(buttonReturn);
		add(buttonList);
		add(buttonAlter);
		
	}
	public void actionPerformed(ActionEvent e) 
	{
		if ("Renting".equals(e.getActionCommand())) 
		{
			System.out.println("Renting");
			startRenting();
		}
		else if ("Return".equals(e.getActionCommand())) 
		{
			System.out.println("Return");
			startReturn();
		}
		else if ("List".equals(e.getActionCommand())) 
		{
			System.out.println("List");
			startMemberList();
		}
		else if ("Alter".equals(e.getActionCommand())) 
		{
			System.out.println("Alter");
			startManage();
		}
	}
	private void startRenting()
	{
		 Renting rnt = new Renting();
		 rnt.createAndShowGUI();
	}
	private void startReturn()
	{
		Return rtn = new Return();
		rtn.createAndShowGUI();
	}
	private void startMemberList()
	{
		MemberList mbrl = new MemberList();
		mbrl.createAndShowGUI();
	}
	private void startManage()
	{
		ManageGameConsole mbrl = new ManageGameConsole();
		mbrl.createAndShowGUI();
	}
	private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Main Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MainScreen newContentPane = new MainScreen();
        newContentPane.setOpaque(true); //content panes must be opaque
        newContentPane.setPreferredSize(new Dimension(210,165));
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
}
