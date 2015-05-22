package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.SwingConstants;

import audio.IntroSound;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.File;

public class StartScreenBackground extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	private static JLabel help = new JLabel("Help"), start = new JLabel("Start Game") , exit = new JLabel("Quit"), load = new JLabel("Load Game");
	private static Font change = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24), defaultFont = new Font("Tahoma", Font.PLAIN | Font.BOLD, 20);
	private static JLabel[] options = {start, help, load, exit};
	//private static JButton play;
	//private static Clip clip;
	private static ImageIcon imageOn, imageOff;
	//private static boolean playTouch = false;
	private static int optionNr = 0;
	private int lastOptionNr;

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();  
	private static Dimension scrnsize = toolkit.getScreenSize();   
	private static final int screenH = (int)scrnsize.getHeight();
	private static final int screenW = (int)scrnsize.getWidth();
	private static final String musicFilename = "musics/KissesinParadise.wav";
	private HelpMenu helpmenu;// = new HelpMenu();
	private StartMenu startmenu;// = new StartMenu();
	private StatisticsMenu statmenu;// = new StatisticsMenu();
	private ExitMenu exitmenu;// = new ExitMenu();
	private LoadGameMenu loadmenu;// = new LoadGameMenu();

	private Image image;
	/**
	 * Create the panel.
	 */
	public StartScreenBackground(String path) throws Exception {
		/*
		imageOn = new ImageIcon("images/SoundOn.png");
		imageOff = new ImageIcon("images/SoundOff.jpeg");
*/
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeFont();
				start.setFont(change);
				optionNr = 0;
				startmenu = new StartMenu();
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
				loadmenu = new LoadGameMenu();
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

		//Fundamental que a propriedade seja colocada a false ou a imagem n�o vai aparecer
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

		JButton audio = new IntroSound(musicFilename);
	/*	File file = new File(musicFilename);
		clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream(file);
		clip.open(ais);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		play = new JButton("");
		play.setSize(new Dimension(25, 25));
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		add(play);
		play.setOpaque(false);
		play.setIcon(imageOn);
		play.setName("Play");
		play.addActionListener(this);
		*/
		add(audio);
	
	}

	public void removeFont(){
		for(int i = 0 ; i < options.length ; i++)
			options[i].setFont(defaultFont);
	}

	public void setImage(String path) {
		image = new ImageIcon(path).getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, screenW, screenH, null);
		super.paintComponent(g);
	}

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
				startmenu = new StartMenu();
			else if(options[1].getFont().equals(change))
				helpmenu = new HelpMenu();
			else if(options[2].getFont().equals(change))
				loadmenu = new LoadGameMenu();
			else if(options[3].getFont().equals(change))
				exitmenu = new ExitMenu();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}

	/*@Override
	public void actionPerformed(ActionEvent ae) {
		if (playTouch == false){
			clip.stop();
			play.setIcon(imageOff);
			play.setFocusable(false);
		} 
		else{
			play.setIcon(imageOn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			play.setFocusable(false);
		}
		playTouch = !playTouch;
	}*/
}