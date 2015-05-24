package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartScreen extends JFrame {
	private static JPanel background = null;
	private static final long serialVersionUID = 1L;
    private static final String filename = "images/BattleshipStartScreen.gif";
	
    private static int width, height;
	public StartScreen() throws Exception {
		setUndecorated(true);
		initUI();
	}

	private void initUI() throws Exception {
		Window w = new Window(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screen = ge.getDefaultScreenDevice();       
        screen.setFullScreenWindow(this);
         
        System.out.println((int)getBounds().getWidth());
        width = (int)getBounds().getWidth();
        height = (int)getBounds().getHeight();
        setResizable(false);
        pack();
        setVisible(true);
        //setLocationRelativeTo(null);   
        initComponents();
        setSize(width,height);
      
       
		
	}

	private void initComponents() throws Exception {
		background = new StartScreenBackground(width, height, filename);
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

