package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartScreen extends JFrame {
	private static JPanel background = null;
	private static final long serialVersionUID = 1L;
    private static final String filename = "images/BattleshipStartScreen.gif";
	
	public StartScreen() throws Exception {
		setUndecorated(true);
		initUI();
	}

	private void initUI() throws Exception {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);  
		setLocationRelativeTo(null);   
		initComponents();
	}

	private void initComponents() throws Exception {  
		background = new StartScreenBackground(getBounds().getWidth(), getBounds().getHeight(), filename);
        getContentPane().add(background, BorderLayout.CENTER);
    }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				StartScreen start = null;
				try {start = new StartScreen();} 
				catch (Exception e){e.printStackTrace();}
				start.setVisible(true);
			}
		});
	}
}

