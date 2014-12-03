package views;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import views.MemberList.AddListener;
import views.MemberList.DeleteListener;
import views.MemberList.EditListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ManageGameConsole extends JPanel implements ListSelectionListener {

	private static final String addString = "add";
	private static final String editString = "edit";
	private static final String deleteString = "delete";
	private JButton addButtonGame;
	private JButton editButtonGame;
	private JButton deleteButtonGame;
	private JList listGame;
	private DefaultListModel listModelGame;

	private JButton addButtonConsole;
	private JButton editButtonConsole;
	private JButton deleteButtonConsole;
	private JList listConsole;
	private DefaultListModel listModelConsole;

	public ManageGameConsole() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeGamePanel();
		tabbedPane.addTab("Game Add/Remove", panel1);

		JComponent panel2 = makeConsolePanel();
		tabbedPane.addTab("Console Add/Remove", panel2);

		add(tabbedPane);
	}

	protected JComponent makeGamePanel() {
		JPanel panel = new JPanel(false);

		panel.setLayout(new GridLayout(1, 1));
		listModelGame = new DefaultListModel();
		populateGameList(listModelGame);

		listGame = new JList(listModelGame);
		listGame.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		listGame.setSelectedIndex(0);
		listGame.addListSelectionListener(this);
		listGame.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(listGame);

		deleteButtonGame = new JButton(deleteString);
		deleteButtonGame.setActionCommand(deleteString);
		deleteButtonGame.addActionListener(new DeleteListenerGame());

		addButtonGame = new JButton(addString);
		addButtonGame.setActionCommand(addString);
		addButtonGame.addActionListener(new AddListenerGame());

		editButtonGame = new JButton(editString);
		editButtonGame.setActionCommand(editString);
		editButtonGame.addActionListener(new EditListenerGame());

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(addButtonGame);
		buttonPane.add(editButtonGame);
		buttonPane.add(deleteButtonGame);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panel.add(listScrollPane, BorderLayout.CENTER);
		panel.add(buttonPane, BorderLayout.PAGE_END);

		return panel;
	}

	protected JComponent makeConsolePanel() {
		JPanel panel = new JPanel(false);

		panel.setLayout(new GridLayout(1, 1));
		listModelConsole = new DefaultListModel();
		populateConsoleList(listModelConsole);

		listConsole = new JList(listModelConsole);
		listConsole
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		listConsole.setSelectedIndex(0);
		listConsole.addListSelectionListener(this);
		listConsole.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(listConsole);

		deleteButtonConsole = new JButton(deleteString);
		deleteButtonConsole.setActionCommand(deleteString);
		deleteButtonConsole.addActionListener(new DeleteListenerConsole());

		addButtonConsole = new JButton(addString);
		addButtonConsole.setActionCommand(addString);
		addButtonConsole.addActionListener(new AddListenerConsole());

		editButtonConsole = new JButton(editString);
		editButtonConsole.setActionCommand(editString);
		editButtonConsole.addActionListener(new EditListenerConsole());

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(addButtonConsole);
		buttonPane.add(editButtonConsole);
		buttonPane.add(deleteButtonConsole);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panel.add(listScrollPane, BorderLayout.CENTER);
		panel.add(buttonPane, BorderLayout.PAGE_END);

		return panel;
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {

			if (listGame.getSelectedIndex() == -1) {
				// No selection, disable fire button.
				deleteButtonGame.setEnabled(false);
				editButtonGame.setEnabled(false);

			} else {
				// Selection, enable the fire button.
				deleteButtonGame.setEnabled(true);
				editButtonGame.setEnabled(true);
			}
			if (listConsole.getSelectedIndex() == -1) {
				// No selection, disable fire button.
				deleteButtonConsole.setEnabled(false);
				editButtonConsole.setEnabled(false);

			} else {
				// Selection, enable the fire button.
				deleteButtonConsole.setEnabled(true);
				editButtonConsole.setEnabled(true);
			}
		}

	}

	class DeleteListenerGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int[] arr = listGame.getSelectedIndices();
			int index = listGame.getSelectedIndex();
			for (int i = 0; i < arr.length; i++) {
				listModelGame.remove(arr[i]);
				for (int k = i; k < arr.length; k++) {
					if (arr[i] < arr[k]) {
						arr[k] = arr[k] - 1;
					}
				}
			}
			int size = listModelGame.getSize();

			if (size == 0) { // Nobody's left, disable firing.
				deleteButtonGame.setEnabled(false);
				editButtonGame.setEnabled(false);
			} else { // Select an index.
				if (index == listModelGame.getSize()) {
					// removed item in last position
					index--;
				}

				listGame.setSelectedIndex(index);
				listGame.ensureIndexIsVisible(index);
			}
		}
	}

	class EditListenerGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// deleteButton.setEnabled(false);
			// editButton.setEnabled(false);
			// addButton.setEnabled(false);
			int index = listGame.getSelectedIndex();
			startGameRegistration("key");
			// startRegistration(listModelGame.getElementAt(index).toString());
			/*
			 * open and wait for edit/add window
			 */
		}
	}

	class AddListenerGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// deleteButton.setEnabled(false);
			// editButton.setEnabled(false);
			// addButton.setEnabled(false);
			startGameRegistration("");
			// startRegistration("");
			/*
			 * open and wait for edit/add window
			 */
		}
	}

	class DeleteListenerConsole implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int[] arr = listConsole.getSelectedIndices();
			int index = listConsole.getSelectedIndex();
			for (int i = 0; i < arr.length; i++) {
				listModelConsole.remove(arr[i]);
				for (int k = i; k < arr.length; k++) {
					if (arr[i] < arr[k]) {
						arr[k] = arr[k] - 1;
					}
				}
			}
			int size = listModelConsole.getSize();

			if (size == 0) { // Nobody's left, disable firing.
				deleteButtonConsole.setEnabled(false);
				editButtonConsole.setEnabled(false);
			} else { // Select an index.
				if (index == listModelConsole.getSize()) {
					// removed item in last position
					index--;
				}

				listConsole.setSelectedIndex(index);
				listConsole.ensureIndexIsVisible(index);
			}
		}
	}

	class EditListenerConsole implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// deleteButton.setEnabled(false);
			// editButton.setEnabled(false);
			// addButton.setEnabled(false);
			int index = listConsole.getSelectedIndex();
			startConsoleRegistration("key");
			// startRegistration(listModelConsole.getElementAt(index).toString());
			/*
			 * open and wait for edit/add window
			 */
		}
	}

	class AddListenerConsole implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// deleteButton.setEnabled(false);
			// editButton.setEnabled(false);
			// addButton.setEnabled(false);
			// startRegistration("");
			startConsoleRegistration("");
			/*
			 * open and wait for edit/add window
			 */
		}
	}

	public void populateGameList(DefaultListModel dlm) {
		// this would be the point where the database comes in
		dlm.addElement("game1");
		dlm.addElement("game2");
		dlm.addElement("game3");
		dlm.addElement("game4");
		dlm.addElement("game5");
	}

	public void populateConsoleList(DefaultListModel dlm) {
		// this would be the point where the database comes in
		dlm.addElement("Console1");
		dlm.addElement("Console2");
	}

	private void startGameRegistration(String str) {
		GameRegistration grgst = new GameRegistration();
		grgst.createAndShowGUI(str);
	}

	private void startConsoleRegistration(String str) {
		ConsoleRegistration rgst = new ConsoleRegistration();
		rgst.createAndShowGUI(str);
	}

	static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("TabbedPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new ManageGameConsole(), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
