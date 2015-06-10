package gui;

import logic.Game;
import logic.Player;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Class GamePanel.
 */
public class GamePanel extends JFrame implements ActionListener {

	private int turn; 
	/** The My opponents panel. */
	private JPanel MyOpponentsPanel1;
	private JPanel myGridPanel1;
	private JPanel MyOpponentsPanel2;
	private JPanel myGridPanel2;
	private JPanel hitmissPanel;
	private JPanel panel;
	private JButton play;
	private JButton end;
	private JButton save;
	private JButton quit;
	private JLabel nameP1, nameP2;
	/** The start frame. */
	private JFrame startFrame;

	/** The name player2. */
	private String namePlayer1, namePlayer2;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Game game;
	private Player player1, player2;

	public JPanel getMyOpponentsPanel1(){return MyOpponentsPanel1;}
	public JPanel getMyPanel1(){return myGridPanel1;}
	public JPanel getMyOpponentsPanel2(){return MyOpponentsPanel2;}
	public JPanel getMyPanel2(){return myGridPanel2;}

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game game = Game.Instance();
					game.loadConfig();
					JFrame frameStart = new JFrame();
					GamePanel frame = new GamePanel("Me","You",game.getPlayer1(), game.getPlayer2(),frameStart);
					//frame.setVisible(true);
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
	 * @param player2 
	 * @param startFrame the start frame
	 */
	public GamePanel(String namePlayer1, String namePlayer2, Player player1, Player player2, JFrame startFrame) {
		this.player1 = player1;
		this.player2 = player2;
		game = Game.Instance();
		turn = game.getStartingPlayer();

		this.startFrame = startFrame;
		this.namePlayer1 = namePlayer1.toUpperCase();
		this.namePlayer2 = namePlayer2.toUpperCase();
		setBackground(Color.BLACK);
		setName("Battleship");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 1243, 622);
		setMinimumSize(new Dimension(1300, 800));
		getContentPane().setLayout(null);

		initCommonPaint();

		if(namePlayer2.equals("Computer"))
			initPaintForOnePlayer();
		else initPaintForTwoPlayers();

	}

	public void initCommonPaint(){
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 1282, 50);
		panel.setPreferredSize(new Dimension(600, 50));
		getContentPane().add(panel);
		panel.setLayout(null);

		if(turn == 1 )
		{
			nameP1 = new JLabel(namePlayer1 + " play first");
			nameP1.setForeground(Color.WHITE);
			nameP1.setFont(new Font("Tahoma", Font.ITALIC, 16));
			nameP1.setBounds(550, 0, 200, 50);
			panel.add(nameP1);
		}
		else
		{
			nameP1 = new JLabel(namePlayer2 + " play first");
			nameP1.setForeground(Color.WHITE);
			nameP1.setFont(new Font("Tahoma", Font.ITALIC, 16));
			nameP1.setBounds(550, 0, 200, 50);
			panel.add(nameP1);
		}

		myGridPanel1 = new Grid(10,10, namePlayer1);
		myGridPanel1.setLocation(0, 50);
		myGridPanel1.setPreferredSize(new Dimension(600, 20));
		myGridPanel1.setSize(new Dimension(600, 653));
		getContentPane().add(myGridPanel1);
		((Grid)myGridPanel1).setGrid(player1, game.getDimV(), game.getDimH(), ChancesListener.class);

		myGridPanel2 = new Grid(10,10, namePlayer2);
		myGridPanel2.setLocation(0, 50);
		myGridPanel2.setPreferredSize(new Dimension(600, 20));
		myGridPanel2.setSize(new Dimension(600, 653));
		getContentPane().add(myGridPanel2);
		((Grid)myGridPanel2).setGrid(player2, game.getDimV(), game.getDimH(), ChancesListener.class);

		MyOpponentsPanel1 = new Grid(10,10, JPanel.class, namePlayer2);
		MyOpponentsPanel1.setBounds(682, 50, 600, 653);
		MyOpponentsPanel1.setPreferredSize(new Dimension(600, 20));
		getContentPane().add(MyOpponentsPanel1);

		MyOpponentsPanel2 = new Grid(10,10, JPanel.class, namePlayer1);
		MyOpponentsPanel2.setBounds(682, 50, 600, 653);
		MyOpponentsPanel2.setPreferredSize(new Dimension(600, 20));
		getContentPane().add(MyOpponentsPanel2);

		hitmissPanel = new HitMissPanel(600,50);
		hitmissPanel.setBounds(0, 703, 1282, 50);
		getContentPane().add(hitmissPanel);

		myGridPanel1.setVisible(false);
		myGridPanel2.setVisible(false);
		MyOpponentsPanel1.setVisible(false);
		MyOpponentsPanel2.setVisible(false);

		save = new JButton("Save");
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
		save.addActionListener(this);
		getContentPane().add(save);

		quit = new JButton("Quit");
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
		quit.addActionListener(this);
		getContentPane().add(quit);		
	}

	public void initPaintForOnePlayer(){
		myGridPanel1.setVisible(true);
		MyOpponentsPanel1.setVisible(true);
		myGridPanel2.setVisible(false);
		MyOpponentsPanel2.setVisible(false);
	}
	/**
	 * Inits the paint of the components
	 */
	public void initPaintForTwoPlayers()
	{
		play = new JButton("Start");
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
		play.setBounds(602, 301, 79, 50);
		play.addActionListener(this);
		getContentPane().add(play);

		end = new JButton("End");
		end.setSelected(true);
		end.setFont(new Font("Tahoma", Font.ITALIC, 16));
		end.setContentAreaFilled(false);
		end.setBorderPainted(false);
		end.setBorder(null);
		end.setFocusable(false);
		end.setBounds(602, 393, 79, 44);
		end.addActionListener(this);	
		getContentPane().add(end);
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */



	@Override
	public void actionPerformed(ActionEvent evt) {
		String item = evt.getActionCommand();

		if(item.equals("Start"))
		{
			if(turn == 1)
			{
				nameP1.setText(namePlayer1 + " playing");
				myGridPanel1.setVisible(true);
				MyOpponentsPanel1.setVisible(true);
				myGridPanel2.setVisible(false);
				MyOpponentsPanel2.setVisible(false);
				play.setEnabled(false);
				end.setEnabled(true);
			}
			else if(turn == 2)
			{
				nameP1.setText(namePlayer2 + " playing");
				myGridPanel2.setVisible(true);
				MyOpponentsPanel2.setVisible(true);
				myGridPanel1.setVisible(false);
				MyOpponentsPanel1.setVisible(false);
				play.setEnabled(false);
				end.setEnabled(true);
			}
		}
		else if(item.equals("End"))
		{
			if(turn == 1)
			{
				myGridPanel1.setVisible(false);
				MyOpponentsPanel1.setVisible(false);
				++turn;
				play.setEnabled(true);
				end.setEnabled(false);
				nameP1.setText("Changing to " + namePlayer2);
			}
			else if(turn == 2)
			{
				myGridPanel2.setVisible(false);
				MyOpponentsPanel2.setVisible(false);
				--turn;
				play.setEnabled(true);
				end.setEnabled(false);
				nameP1.setText("Changing to " + namePlayer1);
			}
		}
		else if(item.equals("Quit"))
		{
			int confirmDialog = JOptionPane.showConfirmDialog(null, "You selected quit! \nAre you sure you want to quit?", "Selected Quit", JOptionPane.YES_NO_OPTION);
			if(confirmDialog == JOptionPane.YES_OPTION) {               
				dispose();
				startFrame.setVisible(true);  
				MyOpponentsPanel1 = null;
				myGridPanel1 = null;
				MyOpponentsPanel2 = null;
				myGridPanel2 = null;
				//focus under this comment here                     
			} 
		}
		else if(item.equals("Save"))
			new SaveGameMenu();	
	}
}


