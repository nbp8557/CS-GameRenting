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
import java.util.ArrayList;

public class Return extends JPanel implements ActionListener {
	protected JButton buttonReturn, buttonCancel;
	JLabel labelMember, labelGame;

	String[] strsMembers;
	String[] strsGames;
	ArrayList<String> 	keysMembers;
	ArrayList<Integer>	keysGames;
	
	static JFrame frame;
	JComboBox listMembers;
	JComboBox listGames;

	public Return() {
		labelMember = new JLabel("Member");
		labelGame = new JLabel("Game");

		buttonReturn = new JButton("Return");
		buttonReturn.setVerticalTextPosition(AbstractButton.CENTER);
		buttonReturn.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonReturn.setActionCommand("Return");
		buttonReturn.setPreferredSize(new Dimension(145, 30));
		buttonReturn.addActionListener(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.setVerticalTextPosition(AbstractButton.CENTER);
		buttonCancel.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonCancel.setActionCommand("Cancel");
		buttonCancel.setPreferredSize(new Dimension(145, 30));
		buttonCancel.addActionListener(this);
		
		keysMembers = new ArrayList<String>();
		keysGames = new ArrayList<Integer>();
		setStringArrays();

		listMembers = new JComboBox(strsMembers);
		listMembers.addActionListener(this);
		listMembers.setPreferredSize(new Dimension(340, 30));
		listGames = new JComboBox(strsGames);
		listGames.addActionListener(this);
		listGames.setPreferredSize(new Dimension(353, 30));

		add(labelMember);
		add(listMembers);

		add(labelGame);
		add(listGames);

		add(buttonReturn);
		add(buttonCancel);

	}

	public void setStringArrays() {
		/*
		 * we take the info from the databases and populate the string arrays
		 * for the drop down menus here
		 */

		// testing code, comment out or delete when no longer relevant
		ArrayList<String> tempMembers = new ArrayList<String>();
		
		tempMembers.add("john doe");
		keysMembers.add("abc123");
		
		tempMembers.add("jane doe");
		keysMembers.add("abc456");
		
		tempMembers.add("billy bob");
		keysMembers.add("abc789");
		
		tempMembers.add("that guy");
		keysMembers.add("def123");
		
		tempMembers.add("other guy");
		keysMembers.add("def456");
		
		strsMembers = new String[tempMembers.size()];
		strsMembers = tempMembers.toArray(strsMembers);

		ArrayList<String> tempGames = new ArrayList<String>();
		
		tempGames.add("superman 64");
		keysGames.add(1);
		
		tempGames.add("earthworm jim");
		keysGames.add(2);
		
		tempGames.add("lemmings");
		keysGames.add(3);
		
		strsGames = new String[tempGames.size()];
		strsGames = tempGames.toArray(strsGames);
		// testing code, comment out or delete when no longer relevant
	}

	public void actionPerformed(ActionEvent e) {
		if ("Return".equals(e.getActionCommand())) 
		{
			System.out.println("Return");
			//do database work here
			System.out.println(keysMembers.get(listMembers.getSelectedIndex()));
			System.out.println(keysGames.get(listGames.getSelectedIndex()));
		} 
		else if ("Cancel".equals(e.getActionCommand())) 
		{
			System.out.println("Cancel");
			frame.dispose();
		} 
		else 
		{
			JComboBox cb = (JComboBox) e.getSource();
			String slct = (String) cb.getSelectedItem();
			System.out.println(slct);
		}
	}

	public static void createAndShowGUI() {

		// Create and set up the window.
		frame = new JFrame("Return Screen");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		Return newContentPane = new Return();
		newContentPane.setOpaque(true); // content panes must be opaque
		newContentPane.setPreferredSize(new Dimension(400, 120));
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
