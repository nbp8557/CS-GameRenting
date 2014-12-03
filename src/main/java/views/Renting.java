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

public class Renting extends JPanel implements ActionListener {
	protected JButton buttonRent, buttonCancel;
	JLabel labelMember, labelConsole, labelGame, labelEboardMember,
			labelRenting;

	String[] strsMembers;
	String[] strsConsoles;
	String[] strsGames;
	String[] strsEboardMembers;

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

		setStringArrays();

		JComboBox listMembers = new JComboBox(strsMembers);
		listMembers.addActionListener(this);
		listMembers.setPreferredSize(new Dimension(340, 30));

		JComboBox listConsoles = new JComboBox(strsConsoles);
		listConsoles.addActionListener(this);
		listConsoles.setPreferredSize(new Dimension(340, 30));

		JComboBox listGames = new JComboBox(strsGames);
		listGames.addActionListener(this);
		listGames.setPreferredSize(new Dimension(353, 30));

		JComboBox listEboardMembers = new JComboBox(strsEboardMembers);
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
		String[] tempMembers = { "john doe", "jane doe", "billy bob",
				"that guy", "other guy" };
		strsMembers = tempMembers;

		String[] tempConsoles = { "snes", "genesis", "microwave", "toaster" };
		strsConsoles = tempConsoles;

		String[] tempGames = { "superman 64", "earthworm jim", "E.T. SNES" };
		strsGames = tempGames;

		String[] tempEboardMembers = { "tam", "tim", "tom" };
		strsEboardMembers = tempEboardMembers;
		// testing code, comment out or delete when no longer relevant
	}

	public void actionPerformed(ActionEvent e) {
		if ("Rent".equals(e.getActionCommand())) {
			System.out.println("Rent");
		} else if ("Cancel".equals(e.getActionCommand())) {
			System.out.println("Cancel");
			frame.dispose();
		} else {
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
