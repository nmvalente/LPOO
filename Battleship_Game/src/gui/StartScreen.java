package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

public class StartScreen extends JFrame {
	private static JPanel background;
	private static JFrame helpFrame;

	private static final long serialVersionUID = 1L;
	
	public StartScreen() {
		initUI();
	}

	private void initUI() {

		setMinimumSize(new Dimension(1000, 400));
		getContentPane().setLayout(null);
		background = new StartScreenBackground("images/BattleshipStartScreen.gif");
		setContentPane(background);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);  
		setPreferredSize(new Dimension(1280, 720));
		pack();
		setTitle("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);   
		//setResizable(false);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				StartScreen start = new StartScreen();
				start.setVisible(true);
			}
		});
	}
}


