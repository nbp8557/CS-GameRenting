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
import java.util.ArrayList;

import entities.Console;
import entities.Game;
import entities.Rental;
import manager.ConsoleManager;
import manager.GameManager;
import manager.PersonManager;
import manager.RentalManager;
import java.util.List;

public class ManageGameConsole extends JPanel implements ListSelectionListener {

	private static final String addString = "add";
	private static final String editString = "edit";
	private static final String deleteString = "delete";
	private static final String refreshString = "refresh";
	private JButton addButtonGame;
	private JButton editButtonGame;
	private JButton deleteButtonGame;
	private JButton refreshButtonGame;
	private JList listGame;
	private DefaultListModel listModelGame;

	private JButton addButtonConsole;
	private JButton editButtonConsole;
	private JButton deleteButtonConsole;
	private JButton refreshButtonConsole;
	private JList listConsole;
	private DefaultListModel listModelConsole;
	
	private ArrayList<Integer> gameIds;
	private ArrayList<Integer> consoleIds;

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
		gameIds = new ArrayList<Integer>();
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
		
		refreshButtonGame = new JButton(refreshString);
		refreshButtonGame.setActionCommand(refreshString);
		refreshButtonGame.addActionListener(new refreshListenerGame());

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(addButtonGame);
		buttonPane.add(editButtonGame);
		buttonPane.add(deleteButtonGame);
		deleteButtonGame.setEnabled(false);
		buttonPane.add(refreshButtonGame);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panel.add(listScrollPane, BorderLayout.CENTER);
		panel.add(buttonPane, BorderLayout.PAGE_END);

		return panel;
	}

	protected JComponent makeConsolePanel() {
		JPanel panel = new JPanel(false);

		panel.setLayout(new GridLayout(1, 1));
		listModelConsole = new DefaultListModel();
		consoleIds = new ArrayList<Integer>();
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
		
		refreshButtonConsole = new JButton(refreshString);
		refreshButtonConsole.setActionCommand(refreshString);
		refreshButtonConsole.addActionListener(new refreshListenerConsole());

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(addButtonConsole);
		buttonPane.add(editButtonConsole);
		buttonPane.add(deleteButtonConsole);
		deleteButtonConsole.setEnabled(false);
		buttonPane.add(refreshButtonConsole);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panel.add(listScrollPane);
		panel.add(buttonPane);

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
				removeGameFromDatabase(gameIds.get(arr[i]));
				listModelGame.remove(arr[i]);
				gameIds.remove(arr[i]);
				for (int k = i; k < arr.length; k++) {
					if (arr[i] < arr[k]) {
						arr[k] = arr[k] - 1;
					}
				}
			}
			int size = listModelGame.getSize();

			if (size == 0) { 
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
	public void removeGameFromDatabase(int key)
	{
		
	}
	public void removeConsoleFromDatabase(int key)
	{
		
	}
	class EditListenerGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// deleteButton.setEnabled(false);
			// editButton.setEnabled(false);
			// addButton.setEnabled(false);
			int index = listGame.getSelectedIndex();
			startGameRegistration(""+gameIds.get(index));
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
	
	class refreshListenerGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			listModelGame.clear();
			gameIds.clear();
			
			populateGameList(listModelGame);
		}
	}
	class refreshListenerConsole implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			listModelConsole.clear();
			consoleIds.clear();
			
			populateConsoleList(listModelConsole);
		}
	}

	class DeleteListenerConsole implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int[] arr = listConsole.getSelectedIndices();
			int index = listConsole.getSelectedIndex();
			for (int i = 0; i < arr.length; i++) {
				removeConsoleFromDatabase(consoleIds.get(arr[i]));
				listModelConsole.remove(arr[i]);
				consoleIds.remove(arr[i]);
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
			startGameRegistration(""+consoleIds.get(index));
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

		
		GameManager gm = new GameManager();
		List<Game> g = gm.listGames();
		for(Game game: g)
		{
			dlm.addElement(game.getName());
			gameIds.add(new Integer(game.getGameID()));
		}
		
		//dlm.addElement("game1");
		//gameIds.add(new Integer(1));//key
	}

	public void populateConsoleList(DefaultListModel dlm) {
		// this would be the point where the database comes in
		
		ConsoleManager cm = new ConsoleManager();
		List<Console> c = cm.listConsoles();
		for(Console consoles: c)
		{
			dlm.addElement(consoles.getConsoleName());
			consoleIds.add(new Integer(consoles.getconsoleID()));
		}
		
		//dlm.addElement("Console1");// examples
		//consoleIds.add(new Integer(1));//key
		
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
		
		JFrame frame = new JFrame("Manage games and consoles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new ManageGameConsole(), BorderLayout.CENTER);
		
		frame.setPreferredSize(new Dimension(600, 200));

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
