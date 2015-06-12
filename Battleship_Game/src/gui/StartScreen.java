package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Class StartScreen create a frame with the first screen game
 */
public class StartScreen extends JFrame {
	
	/** The background. */
	private static JPanel background = null;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant filename. */
	private static final String filename = "images/BattleshipStartScreen.gif";
	
	/** The height. */
	private static int width = 1280, height = 900;

	/**
	 * Instantiates a new start screen.
	 *
     * @param random random number generator
     *
	 * @throws Exception the exception
	 */
	public StartScreen(Random random) throws Exception {
		initUI(random);
	}

	/**
	 * Inits the ui.
     *
     * @param random random number generator
	 *
	 * @throws Exception the exception
	 */
	private void initUI(Random random) throws Exception {
	
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
		initComponents(random);
		setSize(width,height);
	}

	/**
	 * Inits the components.
	 *
     * @param random random number generator
     *
	 * @throws Exception the exception
	 */
	private void initComponents(Random random) throws Exception {
		background = new StartScreenBackground(width, height, filename, this, random);
		add(background, BorderLayout.CENTER);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				StartScreen start = null;
				Random random = new Random();
				try {start = new StartScreen(random);}
				catch (Exception e){e.printStackTrace();}
				start.setVisible(true);
			}
		});
	}
}

