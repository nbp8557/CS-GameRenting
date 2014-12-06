package views;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import entities.Console;
import entities.Game;
import entities.Rental;
import manager.ConsoleManager;
import manager.GameManager;
import manager.PersonManager;
import manager.RentalManager;
import java.util.List;

public class GameRegistration extends JPanel implements ActionListener,
		ItemListener {
	protected JButton buttonSubmit, buttonCancel;
	static JLabel labelName;
	static JLabel labelRentable;
	static JLabel labelConsole;

	static String strName;
	static String strRentable;
	static String strConsole;
	
	static int argKey;
	static boolean rentable;

	static JFrame frame;

	String[] strsConsoles;

	JTextField fieldName;

	JCheckBox checkRentable;

	boolean boolRentable;

	JComboBox listConsoles;

	public GameRegistration() {
		super(new GridBagLayout());

		labelName = new JLabel("Name");
		labelRentable = new JLabel("Rentable");
		labelConsole = new JLabel("Console");

		fieldName = new JTextField(64);
		fieldName.setText(strName);

		setStringArray();

		listConsoles = new JComboBox(strsConsoles);
		listConsoles.addActionListener(this);
		listConsoles.setPreferredSize(new Dimension(253, 30));

		checkRentable = new JCheckBox("     ");
		checkRentable.setSelected(rentable);
		checkRentable.addItemListener(this);

		buttonSubmit = new JButton("Submit/Update");
		buttonSubmit.setVerticalTextPosition(AbstractButton.CENTER);
		buttonSubmit.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonSubmit.setActionCommand("Submit");
		buttonSubmit.setPreferredSize(new Dimension(145, 30));
		buttonSubmit.addActionListener(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.setVerticalTextPosition(AbstractButton.CENTER);
		buttonCancel.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonCancel.setActionCommand("Cancel");
		buttonCancel.setPreferredSize(new Dimension(145, 30));
		buttonCancel.addActionListener(this);

		JPanel textPane = new JPanel();
		textPane.setPreferredSize(new Dimension(190, 395));

		JPanel inputPane = new JPanel();
		inputPane.setPreferredSize(new Dimension(190, 395));

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;

		add(labelName);
		add(fieldName, c);

		add(labelRentable);
		add(checkRentable, c);

		add(labelConsole);
		add(listConsoles, c);
		add(textPane);
		add(inputPane);
		add(buttonSubmit);
		add(buttonCancel, c);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			boolRentable = false;
		} else {
			boolRentable = true;
		}
	}

	public void actionPerformed(ActionEvent e) {
		if ("Submit".equals(e.getActionCommand())) {
			System.out.println("Submit:");

			if (!fieldName.getText().equals("")) {
				System.out.println(fieldName.getText());
				System.out.println("" + boolRentable);
				System.out.println(listConsoles.getSelectedItem().toString());
				/*
				 * do database work here
				 */
			} else {
				System.out.println("ERROR: Field is empty");
			}
		} else if ("Cancel".equals(e.getActionCommand())) {
			System.out.println("Cancel");
			frame.dispose();
		}
	}

	public void setStringArray() {
		String[] tempConsoles = { "console1", "console2" };
		strsConsoles = tempConsoles;
	}

	private static boolean retrieveEditInformation(String str) {
		if (str != null && !str.isEmpty() && !str.equals("")) {
			argKey = Integer.parseInt(str);
			
			GameManager gm = new GameManager();
			List<Game> g = gm.listGames();
			for(Game game: g)
			{
				if(argKey == game.getGameID())
				{
					rentable = game.getRentable();
					strName = game.getName();
					break;
				}
			}

			return true;
		}
		rentable = true;
		
		return false;
	}

	public static void createAndShowGUI(String str) {

		// Create and set up the window.
		retrieveEditInformation(str);
		frame = new JFrame("Registration Screen");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		GameRegistration newContentPane = new GameRegistration();
		newContentPane.setOpaque(true); // content panes must be opaque
		newContentPane.setPreferredSize(new Dimension(300, 100));
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
				createAndShowGUI("");
			}
		});
	}
}
