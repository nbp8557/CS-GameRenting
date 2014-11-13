package views;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Shell extends JFrame {

    public Shell() {
        
       setTitle("Game Renting");
       setSize(600, 600);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);        
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Shell ex = new Shell();
                ex.setVisible(true);
            }
        });
    }
}
