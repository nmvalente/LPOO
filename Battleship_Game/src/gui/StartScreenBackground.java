package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class StartScreenBackground extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	private static JLabel help = new JLabel("Help"), start = new JLabel("Start Game") , exit = new JLabel("Quit"), statistics = new JLabel("Statistics");
	private static Font change = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24), defaultFont = new Font("Tahoma", Font.PLAIN | Font.BOLD, 20);
	private static JLabel[] options = {start, help, statistics, exit};

	private static int optionNr = 0;
	private int lastOptionNr;

	private HelpMenu helpmenu;// = new HelpMenu();
	private StartMenu startmenu;// = new StartMenu();
	private StatisticsMenu statmenu;// = new StatisticsMenu();
	private ExitMenu exitmenu;// = new ExitMenu();
	private LoadGameMenu loadmenu;// = new LoadGameMenu();

	private Image image;
	/**
	 * Create the panel.
	 */
	public StartScreenBackground(String path) {
		//setFocusable(true);

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

		statistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeFont();
				statistics.setFont(change);
				optionNr = 2;
				statmenu = new StatisticsMenu();
			}
		});
		statistics.setPreferredSize(new Dimension(200, 25));
		statistics.setHorizontalTextPosition(SwingConstants.CENTER);
		statistics.setHorizontalAlignment(SwingConstants.CENTER);
		add(statistics);

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

		//Fundamental que a propriedade seja colocada a false ou a imagem não vai aparecer
		setOpaque(false);
		//setPreferredSize(new Dimension (1280, 900));
		//setSize(new Dimension(getWidth(), getHeight()));
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
		//g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, Window.WIDTH, Window.HEIGHT, null);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		super.paintComponent(g);
	}
	@Override
	public int getWidth() {
		return image.getWidth(null);
	}
	@Override
	public int getHeight() {
		return image.getHeight(null);
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
				helpmenu = new HelpMenu();
			else if(options[1].getFont().equals(change))
				startmenu = new StartMenu();
			else if(options[2].getFont().equals(change))
				statmenu = new StatisticsMenu();
			else if(options[3].getFont().equals(change))
				exitmenu = new ExitMenu();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
