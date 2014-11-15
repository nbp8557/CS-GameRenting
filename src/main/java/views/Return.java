package views;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Return extends JPanel implements ActionListener 
{
	protected JButton buttonReturn, buttonCancel;
	JLabel labelMember, labelGame;
	
	String[] strsMembers;
	String[] strsGames;
	
	static JFrame frame;
	JComboBox listMembers;
	JComboBox listGames;
	public Return() 
	{
		labelMember 		= new JLabel("Member");
		labelGame 			= new JLabel("Game");
		
		buttonReturn = new JButton("Return");
		buttonReturn.setVerticalTextPosition(AbstractButton.CENTER);
		buttonReturn.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonReturn.setActionCommand("Return");
		buttonReturn.setPreferredSize(new Dimension(145,30));
		buttonReturn.addActionListener(this);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.setVerticalTextPosition(AbstractButton.CENTER);
		buttonCancel.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonCancel.setActionCommand("Cancel");
		buttonCancel.setPreferredSize(new Dimension(145,30));
		buttonCancel.addActionListener(this);
		
		setStringArrays();
		
		listMembers = new JComboBox(strsMembers);
		listMembers.addActionListener(this);
		listMembers.setPreferredSize(new Dimension(340,30));
		listGames = new JComboBox(strsGames);
		listGames.addActionListener(this);
		listGames.setPreferredSize(new Dimension(353,30));
		
		add(labelMember);
		add(listMembers);

		add(labelGame);
		add(listGames);
		
		add(buttonReturn);
		add(buttonCancel);
		
	}
	
	public void setStringArrays()
	{
		/*
		 * we take the info from the databases and populate
		 * the string arrays for the drop down menus here
		 */
		
		//testing code, comment out or delete when no longer relevant
		String[] tempMembers = { "john doe", "jane doe", "billy bob", "that guy", "other guy" };
		strsMembers = tempMembers;
		
		String[] tempGames = { "superman 64", "earthworm jim", "E.T. SNES"};
		strsGames = tempGames;
		//testing code, comment out or delete when no longer relevant
	}
	public void actionPerformed(ActionEvent e) 
	{
		if ("Return".equals(e.getActionCommand())) 
		{
			System.out.println("Return");
		}
		else if ("Cancel".equals(e.getActionCommand())) 
		{
			System.out.println("Cancel");
			frame.dispose();
		}
		else 
		{
			JComboBox cb = (JComboBox)e.getSource();
	        String slct = (String)cb.getSelectedItem();
	        System.out.println(slct);
		}
	}
	public static void createAndShowGUI() {

        //Create and set up the window.
        frame = new JFrame("Return Screen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        Return newContentPane = new Return();
        newContentPane.setOpaque(true); //content panes must be opaque
        newContentPane.setPreferredSize(new Dimension(400,120));
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
