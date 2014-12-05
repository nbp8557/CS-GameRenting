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

public class Renting extends JPanel implements ActionListener {
	protected JButton buttonRent, buttonCancel;
	JLabel labelMember, labelConsole, labelGame, labelEboardMember,
			labelRenting;

	String[] strsMembers;
	String[] strsConsoles;
	String[] strsGames;
	String[] strsEboardMembers;
	
	ArrayList<String> 	keysMembers;
	ArrayList<Integer>	keysConsoles;
	ArrayList<Integer>	keysGames;
	ArrayList<String> 	keysEboardMembers;
	
	JComboBox listMembers;
	JComboBox listConsoles;
	JComboBox listGames;
	JComboBox listEboardMembers;

	static JFrame frame;

	public Renting() {
		labelMember = new JLabel("Member");

		labelConsole = new JLabel("Console");

		labelGame = new JLabel("Game");

		labelEboardMember = new JLabel("EBoard Member");

		labelRenting = new JLabel("Renting");

		buttonRent = new JButton("Rent");
		buttonRent.setVerticalTextPosition(AbstractButton.CENTER);
		buttonRent.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonRent.setActionCommand("Rent");
		buttonRent.setPreferredSize(new Dimension(145, 30));
		buttonRent.addActionListener(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.setVerticalTextPosition(AbstractButton.CENTER);
		buttonCancel.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonCancel.setActionCommand("Cancel");
		buttonCancel.setPreferredSize(new Dimension(145, 30));
		buttonCancel.addActionListener(this);

		keysMembers = new ArrayList<String>();
		keysConsoles = new ArrayList<Integer>();
		keysGames = new ArrayList<Integer>();
		keysEboardMembers = new ArrayList<String>();
		
		setStringArrays();

		listMembers = new JComboBox(strsMembers);
		listMembers.addActionListener(this);
		listMembers.setPreferredSize(new Dimension(340, 30));

		listConsoles = new JComboBox(strsConsoles);
		listConsoles.addActionListener(this);
		listConsoles.setPreferredSize(new Dimension(340, 30));

		listGames = new JComboBox(strsGames);
		listGames.addActionListener(this);
		listGames.setPreferredSize(new Dimension(353, 30));

		listEboardMembers = new JComboBox(strsEboardMembers);
		listEboardMembers.addActionListener(this);
		listEboardMembers.setPreferredSize(new Dimension(295, 30));

		add(labelMember);
		add(listMembers);

		add(labelConsole);
		add(listConsoles);

		add(labelGame);
		add(listGames);

		add(labelEboardMember);
		add(listEboardMembers);

		add(buttonRent);
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
		
		ArrayList<String> tempConsoles = new ArrayList<String>();
		
		tempConsoles.add("snes");
		keysConsoles.add(1);
		
		tempConsoles.add("genesis");
		keysConsoles.add(2);
		
		tempConsoles.add("microwave");
		keysConsoles.add(3);
		
		tempConsoles.add("toaster");
		keysConsoles.add(4);
		
		strsConsoles = new String[tempConsoles.size()];
		strsConsoles = tempConsoles.toArray(strsConsoles);
		
		ArrayList<String> tempGames = new ArrayList<String>();
		
		tempGames.add("superman 64");
		keysGames.add(1);
		
		tempGames.add("earthworm jim");
		keysGames.add(2);
		
		tempGames.add("lemmings");
		keysGames.add(3);
		
		strsGames = new String[tempGames.size()];
		strsGames = tempGames.toArray(strsGames);
		
		ArrayList<String> tempEboardMembers = new ArrayList<String>();
		
		tempEboardMembers.add("tam");
		keysEboardMembers.add("sdf3557");
		
		tempEboardMembers.add("tim");
		keysEboardMembers.add("abt0265");
		
		tempEboardMembers.add("tom");
		keysEboardMembers.add("lwn3266");
		
		strsEboardMembers = new String[tempEboardMembers.size()];
		strsEboardMembers = tempEboardMembers.toArray(strsEboardMembers);
		// testing code, comment out or delete when no longer relevant
	}

	public void actionPerformed(ActionEvent e) {
		if ("Rent".equals(e.getActionCommand())) 
		{
			System.out.println("Rent");
			//do database work here
			System.out.println(keysMembers.get(listMembers.getSelectedIndex()));
			System.out.println(keysConsoles.get(listConsoles.getSelectedIndex()));
			System.out.println(keysGames.get(listGames.getSelectedIndex()));
			System.out.println(keysEboardMembers.get(listEboardMembers.getSelectedIndex()));
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
		frame = new JFrame("Renting Screen");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		Renting newContentPane = new Renting();
		newContentPane.setOpaque(true); // content panes must be opaque
		newContentPane.setPreferredSize(new Dimension(400, 200));
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
