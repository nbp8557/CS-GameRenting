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

public class ConsoleRegistration extends JPanel implements ActionListener {
	protected JButton buttonSubmit, buttonCancel;
	static JLabel labelName;
	static String strName;
	JTextField fieldName;
	static JFrame frame;
	static int argKey;

	public ConsoleRegistration() {
		super(new GridBagLayout());
		labelName = new JLabel("Name");
		fieldName = new JTextField(64);
		fieldName.setText(strName);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;

		buttonSubmit = new JButton("Submit/Update");
		buttonSubmit.setVerticalTextPosition(AbstractButton.CENTER);
		buttonSubmit.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonSubmit.setActionCommand("Submit");
		buttonSubmit.setPreferredSize(new Dimension(250, 30));
		buttonSubmit.addActionListener(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.setVerticalTextPosition(AbstractButton.CENTER);
		buttonCancel.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonCancel.setActionCommand("Cancel");
		buttonCancel.setPreferredSize(new Dimension(250, 30));
		buttonCancel.addActionListener(this);

		add(labelName);
		add(fieldName, c);
		add(buttonSubmit);
		add(buttonCancel, c);
	}

	public void actionPerformed(ActionEvent e) {
		if ("Submit".equals(e.getActionCommand())) {
			System.out.println("Submit:");

			if (!fieldName.getText().equals("")) 
			{
				System.out.println(fieldName.getText());

				if(argKey < 0)
				{
					//we are not given a key, so this is an addition
					ConsoleManager cm = new ConsoleManager();
					cm.createConsole(fieldName.getText());
				}
				else
				{
					//we are given a key, so this is an update
					ConsoleManager cm = new ConsoleManager();
					cm.updateConsole(argKey, fieldName.getText());
				}
			} else {
				System.out.println("ERROR: Field is empty");
			}
		} else if ("Cancel".equals(e.getActionCommand())) {
			System.out.println("Cancel");
			frame.dispose();
		}
	}

	private static boolean retrieveEditInformation(String str) {
		if (str != null && !str.isEmpty() && !str.equals("")) {
			argKey = Integer.parseInt(str);
			
			//get name of console with argKey
			ConsoleManager cm = new ConsoleManager();
			Console console = cm.selectConsole(argKey);
			strName = console.getConsoleName();

			return true;
		}
		argKey = -1;

		return false;
	}

	public static void createAndShowGUI(String str) {

		// Create and set up the window.
		retrieveEditInformation(str);
		frame = new JFrame("Registration Screen");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		ConsoleRegistration newContentPane = new ConsoleRegistration();
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
