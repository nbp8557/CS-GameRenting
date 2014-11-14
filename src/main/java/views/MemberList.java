package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MemberList extends JPanel
implements ListSelectionListener {
	
	private JList list;
    private DefaultListModel listModel;
    
    private static final String addString = "add";
    private static final String editString = "edit";
    private static final String deleteString = "delete";
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    
    public MemberList() 
    {
        super(new BorderLayout());
        
        listModel = new DefaultListModel();
        populateMemberList(listModel);
        
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        
        deleteButton = new JButton(deleteString);
        deleteButton.setActionCommand(deleteString);
        deleteButton.addActionListener(new DeleteListener());
        
        addButton = new JButton(addString);
        addButton.setActionCommand(addString);
        addButton.addActionListener(new AddListener());
        
        editButton = new JButton(editString);
        editButton.setActionCommand(editString);
        editButton.addActionListener(new EditListener());
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(addButton);
        buttonPane.add(editButton);
        buttonPane.add(deleteButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }
    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int[] arr = list.getSelectedIndices();
            int index = list.getSelectedIndex();
            for(int i = 0; i < arr.length; i++)
            {
                 listModel.remove(arr[i]);
                 for(int k = i; k < arr.length; k++)
                 {
                     if(arr[i] < arr[k])
                     {
                            arr[k] = arr[k] - 1;
                     }
                 }
            }
           

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                deleteButton.setEnabled(false);
                editButton.setEnabled(false);
            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }
    class EditListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		deleteButton.setEnabled(false);
            editButton.setEnabled(false);
            addButton.setEnabled(false);
            
            /*
             * open and wait for edit/add window
             */
    	}
    }
    class AddListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		deleteButton.setEnabled(false);
            editButton.setEnabled(false);
            addButton.setEnabled(false);
            
            /*
             * open and wait for edit/add window
             */
    	}
    }
    public void populateMemberList(DefaultListModel dlm)
    {
    	//this would be the point where the database comes in
    	dlm.addElement("Jane Doe");
    	dlm.addElement("John Smith");
    	dlm.addElement("Kathy Green");
    }
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
                deleteButton.setEnabled(false);
                editButton.setEnabled(false);

            } else {
            //Selection, enable the fire button.
            	deleteButton.setEnabled(true);
            	editButton.setEnabled(true);
            }
        }
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MemberList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new MemberList();
        newContentPane.setOpaque(true); //content panes must be opaque
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
