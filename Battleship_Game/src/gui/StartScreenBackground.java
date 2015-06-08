package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.SwingConstants;

import cli.Start;
import cli.Utils;
import audio.IntroSound;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.util.Scanner;

/**
 * The Class StartScreenBackground is used to create a new background image and to handle with the events on the first game frame
 */
public class StartScreenBackground extends JPanel implements KeyListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The load. */
	private static JLabel help = new JLabel("Help"), start = new JLabel("Start Game") , exit = new JLabel("Quit"), load = new JLabel("Load Game");
	
	/** The default font. */
	private static Font change = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24), defaultFont = new Font("Tahoma", Font.PLAIN | Font.BOLD, 20);
	
	/** The options. */
	private static JLabel[] options = {start, help, load, exit};
	
	/** The option nr. */
	private static int optionNr = 0;
	
	/** The last option nr. */
	private int lastOptionNr;
	
	/** The screen h. */
	private static int screenH ;
	
	/** The screen w. */
	private static int screenW ;
	
	/** The Constant musicFilename. */
	private static final String musicFilename = "musics/KissesinParadise.wav";
	
	/** The helpmenu. */
	private HelpMenu helpmenu;// = new HelpMenu();
	
	/** The startmenu. */
	private GameSettings startmenu;
	
	/** The statmenu. */
	private StatisticsMenu statmenu;// = new StatisticsMenu();
	
	/** The exitmenu. */
	private ExitMenu exitmenu;// = new ExitMenu();
	
	/** The loadmenu. */
	private LoadGameMenu loadmenu;// = new LoadGameMenu();
	
	/** The image. */
	private Image image; 
	
	/** The frame. */
	private JFrame frame;
	
	/** The audio. */
	private IntroSound audio;

	private Random random;
	
	/**
	 * Create the panel.
	 *
	 * @param width the width
	 * @param height the height
	 * @param path the path
	 * @param startScreen the start screen
	 * @throws Exception the exception
	 */
	public StartScreenBackground(int width, int height, String path, JFrame startScreen, Random newRandom) throws Exception {
        random = newRandom;
		screenH = height; 
		screenW = width;
		frame = startScreen;
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeFont();
				start.setFont(change);
				optionNr = 0;
				//frame.setVisible(false);
				audio.stop();
				
				startmenu = new GameSettings(screenW, screenH, frame, audio, random);
				startmenu.setLocationRelativeTo(frame);
				startmenu.setVisible(true);
			}
		});
		start.setPreferredSize(new Dimension(200, 25));
		start.setHorizontalTextPosition(SwingConstants.CENTER);
		start.setHorizontalAlignment(SwingConstants.CENTER);
		add(start);
		
		
		help.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeFont();
				help.setFont(change);
				optionNr = 1;
				helpmenu = new HelpMenu();
			}
		});		
		help.setPreferredSize(new Dimension(200, 25));
		help.setHorizontalTextPosition(SwingConstants.CENTER);
		help.setHorizontalAlignment(SwingConstants.CENTER);
		add(help);

		load.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeFont();
				load.setFont(change);
				optionNr = 2;
				loadmenu = new LoadGameMenu(".", "game.xml");
			}
		});
		load.setPreferredSize(new Dimension(200, 25));
		load.setHorizontalTextPosition(SwingConstants.CENTER);
		load.setHorizontalAlignment(SwingConstants.CENTER);
		add(load);

		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeFont();
				exit.setFont(change);
				optionNr = 3;
				exitmenu = new ExitMenu();
			}
		});
		exit.setPreferredSize(new Dimension(200, 25));
		exit.setHorizontalTextPosition(SwingConstants.CENTER);
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		add(exit);

		setImage(path);

		//Fundamental que a propriedade seja colocada a false ou a imagem nao vai aparecer
		setOpaque(false);
		setSize(screenW, screenH);
		

		for(int i = 0 ; i < options.length ; i++)
		{
			options[i].setFocusable(true);
			options[i].setOpaque(false);
			options[i].setFont(defaultFont);
			options[i].setBackground(new Color(0,0,0,0));
			options[i].setForeground(Color.WHITE);
			options[i].addKeyListener(this);
		}
		options[0].setFont(change); // aplicacao poe enfase no primeiro botao

		audio = new IntroSound(musicFilename);
		add(audio);
	
	}

	/**
	 * Removes the font.
	 */
	public void removeFont(){
		for(int i = 0 ; i < options.length ; i++)
			options[i].setFont(defaultFont);
	}

	/**
	 * Sets the image.
	 *
	 * @param path the new image
	 */
	public void setImage(String path) {
		image = new ImageIcon(path).getImage();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, screenW, screenH, null);
		super.paintComponent(g);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			lastOptionNr = optionNr;
			optionNr++;
			optionNr = optionNr % 4;
			options[lastOptionNr].setFont(defaultFont);
			options[optionNr].setFont(change);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			lastOptionNr = optionNr;
			optionNr--;
			if(optionNr < 0)
				optionNr = 3;
			optionNr = optionNr % 4;
			options[lastOptionNr].setFont(defaultFont);
			options[optionNr].setFont(change);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(options[0].getFont().equals(change))
			{
				frame.setVisible(false);
				audio.stop();
				startmenu = new GameSettings(screenW, screenH,frame, audio, random);
				startmenu.setLocationRelativeTo(frame);
				startmenu.setVisible(true);
				
			}
			else if(options[1].getFont().equals(change))
			{
				helpmenu = new HelpMenu();		
			}
			
			else if(options[2].getFont().equals(change))
				loadmenu = new LoadGameMenu(".", "game.xml");
			else if(options[3].getFont().equals(change))
				exitmenu = new ExitMenu();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent arg0) {}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent arg0) {}
	
}
