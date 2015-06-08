package gui;

import logic.Game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
/**
 * The Class GamePanel.
 */
public class GamePanel extends JFrame implements ActionListener {
	/*private static final int NUM_ROWS = 10;
	private static final int NUM_COLS = 10;
	private static final int PANEL_WIDTH = 600;
	private static final int PANEL_HEIGHT = 600;*/
	/** The My opponents panel. */
	private JPanel MyOpponentsPanel;
	
	/** The start frame. */
	private JFrame startFrame;
	
	/** The name player2. */
	private String namePlayer1, namePlayer2;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    private Game game;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frameStart = new JFrame();
					GamePanel frame = new GamePanel("Me","You",frameStart);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *
	 * @param namePlayer1 the name of player1
	 * @param namePlayer2 the name of player2
	 * @param startFrame the start frame
	 */
	public GamePanel(String namePlayer1, String namePlayer2, JFrame startFrame) {
        game = Game.Instance();
		this.startFrame = startFrame;
		this.namePlayer1 = namePlayer1;
		this.namePlayer2 = namePlayer2;
		setBackground(Color.BLACK);
		setName("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1243, 622);
		setMinimumSize(new Dimension(1300, 800));
		getContentPane().setLayout(null);
		initPaint();
		
	}

	/**
	 * Inits the paint of the components
	 */
	public void initPaint()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 1282, 50);
		panel.setPreferredSize(new Dimension(600, 50));
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel myGrid = new JLabel(namePlayer1);
		myGrid.setForeground(Color.WHITE);
		myGrid.setFont(new Font("Tahoma", Font.ITALIC, 16));
		myGrid.setBounds(272, 0, 123, 50);
		panel.add(myGrid);
		
		if(namePlayer2 == "")
			namePlayer2 = "Computer Grid";
		JLabel label = new JLabel(namePlayer2);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.ITALIC, 16));
		label.setBounds(934, 0, 123, 50);
		panel.add(label);

		JPanel myGridPanel = new Grid(10,10, ChancesListener.class);
		myGridPanel.setLocation(0, 50);
		myGridPanel.setPreferredSize(new Dimension(600, 20));
		myGridPanel.setSize(new Dimension(600, 653));
		getContentPane().add(myGridPanel);
        ((Grid)myGridPanel).setGrid(game.getPlayer1().getBoard(), game.getDimV(), game.getDimH(), ChancesListener.class);
        myGridPanel.revalidate();

		JPanel panel_2 = new HitMissPanel(600,50);
		panel_2.setBounds(0, 703, 1282, 50);
		getContentPane().add(panel_2);

		MyOpponentsPanel = new Grid(10,10, JPanel.class);
		MyOpponentsPanel.setBounds(682, 50, 600, 653);
		MyOpponentsPanel.setPreferredSize(new Dimension(600, 20));
		getContentPane().add(MyOpponentsPanel);

		JLabel turn = new JLabel("My turn");
		turn.setFont(new Font("Tahoma", Font.ITALIC, 16));
		turn.setHorizontalAlignment(SwingConstants.CENTER);
		turn.setAlignmentX(Component.CENTER_ALIGNMENT);
		turn.setBounds(602, 292, 79, 50);
		getContentPane().add(turn);

		JButton save = new JButton("Save");
		save.setFocusable(false);
		save.setDisabledIcon(null);
		save.setDisabledSelectedIcon(null);
		save.setBorder(null);
		save.setRolloverEnabled(false);
		save.setBorderPainted(false);
		save.setContentAreaFilled(false);
		save.setHorizontalAlignment(SwingConstants.CENTER);
		save.setFont(new Font("Tahoma", Font.ITALIC, 16));
		save.setAlignmentX(0.5f);
		save.setBounds(602, 479, 79, 50);
		getContentPane().add(save);
		save.addActionListener(this);

		JButton quit = new JButton("Quit");
		quit.setFocusable(false);
		quit.setDisabledIcon(null);
		quit.setDisabledSelectedIcon(null);
		quit.setBorder(null);
		quit.setRolloverEnabled(false);
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		quit.setHorizontalAlignment(SwingConstants.CENTER);
		quit.setFont(new Font("Tahoma", Font.ITALIC, 16));
		quit.setAlignmentX(0.5f);
		quit.setBounds(602, 548, 79, 50);
		getContentPane().add(quit);
		quit.addActionListener(this);
		
		JButton play = new JButton("Play");
		play.setFocusable(false);
		play.setDisabledIcon(null);
		play.setDisabledSelectedIcon(null);
		play.setBorder(null);
		play.setSelected(true);
		play.setRolloverEnabled(false);
		play.setHorizontalAlignment(SwingConstants.CENTER);
		play.setFont(new Font("Tahoma", Font.ITALIC, 16));
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.setAlignmentX(0.5f);
		play.setBounds(602, 409, 79, 50);
		getContentPane().add(play);
		play.addActionListener(this);
	}
	
	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
	public void paintComponent(Graphics g)
	{
	   super.paintComponents(g);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String item = evt.getActionCommand();
		if(item.equals("Play"))
		{
			MyOpponentsPanel = new Grid(10,10, HitsListener.class);
			MyOpponentsPanel.setBounds(682, 50, 600, 653);
			MyOpponentsPanel.setPreferredSize(new Dimension(600, 20));
			add(MyOpponentsPanel);	
			MyOpponentsPanel.revalidate();
		}
		else if(item.equals("Quit"))
		{
			int confirmDialog = JOptionPane.showConfirmDialog(null, "You selected quit! \nAre you sure you want to quit?", "Selected Quit", JOptionPane.YES_NO_OPTION);
			if(confirmDialog == JOptionPane.YES_OPTION) {               
				dispose();
				startFrame.setVisible(true);         
				//focus under this comment here                     
			} else {                    
				//game.reset();                 
				//displayBoard();
				System.out.println("you almost quit the game");
			}
		}
		else if(item.equals("Save"))
			new SaveGameMenu();
	}
}


