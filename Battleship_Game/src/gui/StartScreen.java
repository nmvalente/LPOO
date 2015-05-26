package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

import javafx.scene.layout.Background;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartScreen extends JFrame {
	private static JPanel background = null;
	private static final long serialVersionUID = 1L;
	private static final String filename = "images/BattleshipStartScreen.gif";
	private static int width = 1280, height = 900;

	public StartScreen() throws Exception {
		initUI();
	}

	private void initUI() throws Exception {
		//		setCaracteristics();
		//		setMaximizedWindow();
		//setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice screen = ge.getDefaultScreenDevice();
//		screen.setFullScreenWindow(this);
		
		Rectangle bounds = new Rectangle(screenSize.width, screenSize.height);
		width = (int) bounds.getWidth();
		height = (int) bounds.getHeight();
		
		setResizable(false);
		pack();
		setVisible(true);
		initComponents();
		setSize(width,height);
	}

	private void initComponents() throws Exception {
		background = new StartScreenBackground(width, height, filename, this);
		add(background, BorderLayout.CENTER);
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

