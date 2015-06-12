package gui;

import logic.Game;
import logic.Player;
import logic.Position;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.border.MatteBorder;

/**
 * The Class GamePanel.
 */
public class GamePanel extends JFrame implements ActionListener, MouseListener {

	public JPanel[][][] cells = new JPanel [2][10][10]; //, cells2 = new JPanel [10][10];
	int posX;
	int posY;
	/** The default background. */
	private Color defaultBackground = Color.GRAY;
	private int turn;
    private boolean played;
	/** The My opponents panel. */
	public JPanel[] MyOpponentsPanels = new JPanel[2];
	public JPanel[] myGridPanels = new JPanel[2];
//	public JPanel MyOpponentsPanel2;
//	public JPanel myGridPanel2;
	public JPanel[] hitmissPanels = new JPanel[2];
//	public JPanel hitmissPanel2;
	private JPanel panel;
	private JButton play;
	private JButton end;
	private JButton save;
	private JButton quit;
	private JLabel nameP1;
	private Random randomRow = new Random(), randomCol = new Random();
    private int[] hits = {0, 0};
    private int[] misses = {0, 0};
//	private int hits1=0, hits2=0, misses1=0,misses2=0;
	/** The start frame. */
	private JFrame startFrame;

	/** The name player2. */
	private String namePlayer1, namePlayer2;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Game game;
	private Player player1, player2;

	public JPanel getMyOpponentsPanel1(){return MyOpponentsPanels[0];}
	public JPanel getMyPanel1(){return myGridPanels[0];}
	public JPanel getMyOpponentsPanel2(){return MyOpponentsPanels[0];}
	public JPanel getMyPanel2(){return myGridPanels[1];}

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
	 * @param player2 
	 * @param startFrame the start frame
	 */
	public GamePanel(String namePlayer1, String namePlayer2, Player player1, Player player2, JFrame startFrame) {
		this.player1 = player1;
		this.player2 = player2;
		game = Game.Instance();
		turn = game.getStartingPlayer();
        played = false;

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
			nameP1 = new JLabel(namePlayer1 + " plays first");
			nameP1.setForeground(Color.WHITE);
			nameP1.setFont(new Font("Tahoma", Font.ITALIC, 16));
			nameP1.setBounds(550, 0, 200, 50);
			panel.add(nameP1);
		}
		else
		{
			nameP1 = new JLabel(namePlayer2 + " plays first");
			nameP1.setForeground(Color.WHITE);
			nameP1.setFont(new Font("Tahoma", Font.ITALIC, 16));
			nameP1.setBounds(550, 0, 200, 50);
			panel.add(nameP1);
		}

		myGridPanels[0] = new Grid(10,10, player1);
		myGridPanels[0].setLocation(0, 50);
		myGridPanels[0].setPreferredSize(new Dimension(600, 20));
		myGridPanels[0].setSize(new Dimension(600, 653));
		getContentPane().add(myGridPanels[0]);
		((Grid)myGridPanels[0]).setGrid(player1, game.getDimV(), game.getDimH(), false);

		myGridPanels[1] = new Grid(10,10, player2);
		myGridPanels[1].setLocation(0, 50);
		myGridPanels[1].setPreferredSize(new Dimension(600, 20));
		myGridPanels[1].setSize(new Dimension(600, 653));
		getContentPane().add(myGridPanels[1]);
		((Grid)myGridPanels[1]).setGrid(player2, game.getDimV(), game.getDimH(), false);

		MyOpponentsPanels[0] = new JPanel();
		MyOpponentsPanels[0].setBackground(Color.RED);
		MyOpponentsPanels[0].setBounds(772, 169, 450, 450);
		for (int i =0; i < 10; i++){
			for(int j = 0; j < 10; j++)
			{
				JPanel tempPanel = new JPanel();
				tempPanel.setBackground(Color.black);
				tempPanel.setMinimumSize(new Dimension(45, 45));
				tempPanel.setMaximumSize(new Dimension(45, 45));
				tempPanel.setPreferredSize(new Dimension(45, 45));
				tempPanel.setBorder(new MatteBorder(1, 1, 0, 0, Color.GRAY));
				cells[0][i][j] = tempPanel;
				//				tempPanel.addMouseListener(new MouseAdapter() {
				//					@Override
				//					public void mouseEntered(MouseEvent e) {
				//						JPanel painel = (JPanel)e.getSource();
				//						if(painel.getBackground() == Color.black)
				//							painel.setBackground(Color.GRAY);
				//					}
				//
				//					@Override
				//					public void mouseExited(MouseEvent e) {
				//						JPanel painel = (JPanel)e.getSource();
				//						if(painel.getBackground() == Color.blue || painel.getBackground() == Color.LIGHT_GRAY)
				//						{
				//							defaultBackground=painel.getBackground();
				//							painel.setBackground(defaultBackground);
				//						}
				//						if(painel.getBackground() == Color.GRAY)
				//						{
				//							defaultBackground=Color.BLACK;
				//							painel.setBackground(defaultBackground);
				//						}
				//					}
				//
				//				});

				MyOpponentsPanels[0].add(cells[0][i][j]);
			}
		}
		MyOpponentsPanels[0].setLayout(new GridLayout(10, 10, 0, 0));
		MyOpponentsPanels[0].addMouseListener(this);
		getContentPane().add(MyOpponentsPanels[0]);

		/*
		MyOpponentsPanel1 = new Grid(10,10, player2, player1);
		MyOpponentsPanel1.setBounds(682, 50, 600, 653);
		MyOpponentsPanel1.setPreferredSize(new Dimension(600, 20));
		MyOpponentsPanel2.addMouseListener(this);
		getContentPane().add(MyOpponentsPanel1);
		 */
		MyOpponentsPanels[1] = new JPanel();
		MyOpponentsPanels[1].setBackground(Color.RED);
		MyOpponentsPanels[1].setBounds(772, 169, 450, 450);
		for (int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++)
			{
				JPanel tempPanel = new JPanel();
				tempPanel.setBackground(Color.black);
				tempPanel.setMinimumSize(new Dimension(45, 45));
				tempPanel.setMaximumSize(new Dimension(45, 45));
				tempPanel.setPreferredSize(new Dimension(45, 45));
				tempPanel.setBorder(new MatteBorder(1, 1, 0, 0, Color.GRAY));
				cells[1][i][j] = tempPanel;
				//				tempPanel.addMouseListener(new MouseAdapter() {
				//					@Override
				//					public void mouseEntered(MouseEvent e) {
				//						JPanel painel = (JPanel)e.getSource();
				//						if(painel.getBackground() == Color.black)
				//							painel.setBackground(Color.GRAY);
				//					}
				//
				//					@Override
				//					public void mouseExited(MouseEvent e) {
				//						JPanel painel = (JPanel)e.getSource();
				//						if(painel.getBackground() == Color.blue || painel.getBackground() == Color.LIGHT_GRAY)
				//						{
				//							defaultBackground=painel.getBackground();
				//							painel.setBackground(defaultBackground);
				//						}
				//						if(painel.getBackground() == Color.GRAY)
				//						{
				//							defaultBackground=Color.BLACK;
				//							painel.setBackground(defaultBackground);
				//						}
				//					}
				//
				//				});

				MyOpponentsPanels[1].add(cells[1][i][j]);
			}
		}
		MyOpponentsPanels[1].setLayout(new GridLayout(10, 10, 0, 0));
		MyOpponentsPanels[1].addMouseListener(this);
		getContentPane().add(MyOpponentsPanels[1]);

		/*
		MyOpponentsPanel2 = new Grid(10,10, player1, player2);
		MyOpponentsPanel2.setBounds(682, 50, 600, 653);
		MyOpponentsPanel2.setPreferredSize(new Dimension(600, 20));
		MyOpponentsPanel2.addMouseListener(this);
		getContentPane().add(MyOpponentsPanel2);
		 */
		hitmissPanels[0] = new HitMissPanel(600,50);
		hitmissPanels[0].setBounds(0, 703, 1282, 50);
		getContentPane().add(hitmissPanels[0]);

		hitmissPanels[1] = new HitMissPanel(600,50);
		hitmissPanels[1].setBounds(0, 703, 1282, 50);
		getContentPane().add(hitmissPanels[1]);

		myGridPanels[0].setVisible(false);
		myGridPanels[1].setVisible(false);
		MyOpponentsPanels[0].setVisible(false);
		MyOpponentsPanels[1].setVisible(false);
		hitmissPanels[0].setVisible(false);
		hitmissPanels[1].setVisible(false);

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
		myGridPanels[0].setVisible(true);
		MyOpponentsPanels[0].setVisible(true);
		myGridPanels[1].setVisible(false);
		MyOpponentsPanels[1].setVisible(false);
		hitmissPanels[0].setVisible(true);
		hitmissPanels[1].setVisible(false);
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
				myGridPanels[0].setVisible(true);
				MyOpponentsPanels[0].setVisible(true);
				myGridPanels[1].setVisible(false);
				MyOpponentsPanels[1].setVisible(false);
				hitmissPanels[0].setVisible(true);
				play.setEnabled(false);
				end.setEnabled(true);
			}
			else if(turn == 2)
			{
				nameP1.setText(namePlayer2 + " playing");
				myGridPanels[1].setVisible(true);
				MyOpponentsPanels[1].setVisible(true);
				myGridPanels[0].setVisible(false);
				MyOpponentsPanels[0].setVisible(false);
				hitmissPanels[1].setVisible(true);
				play.setEnabled(false);
				end.setEnabled(true);
			}
		}
		else if(item.equals("End"))
		{
            played = false;
            if (turn == 1) {
                myGridPanels[0].setVisible(false);
                MyOpponentsPanels[0].setVisible(false);
                hitmissPanels[0].setVisible(false);
                ++turn;
                play.setEnabled(true);
                end.setEnabled(false);
                nameP1.setText("Changing to " + namePlayer2);
            } else if (turn == 2) {
                myGridPanels[1].setVisible(false);
                MyOpponentsPanels[1].setVisible(false);
                hitmissPanels[1].setVisible(false);
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
				MyOpponentsPanels[0] = null;
				myGridPanels[0] = null;
				MyOpponentsPanels[1] = null;
				myGridPanels[1] = null;
			} 
		}
		else if(item.equals("Save"))
			new SaveGameMenu();	
	}

	public void computerTurn(Player player2, Player player1) {
        game.computerTurn(player2, player1);
        ((Grid)myGridPanels[0]).setGrid(player1, game.getDimV(), game.getDimH(), false);
        played = false;
        turn--;
        endGame();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
        if (game.getState() == 0) {
            posX = arg0.getY() / 45;
            posY = arg0.getX() / 45;
            Position position = Position.Instance(posX, posY);
            if (game.getNumberPlayers() == 1) {
                if (turn == 2) computerTurn(player2, player1);
                else if (!played) playerTurn(position, player1, player2);
            } else if (!played) {
                if (turn == 1) playerTurn(position, player1, player2);
                else playerTurn(position, player2, player1);
            }
        }
	}

    private void playerTurn(Position position, Player playerAttack, Player playerDefend) {
        if (playerDefend.getBoard().getPosition(position) != -2 && playerDefend.getBoard().getPosition(position) != -3) {
            boolean attackResult = game.attackPosition(playerAttack, playerDefend, position);
            if(attackResult) {
                ((HitMissPanel) hitmissPanels[playerAttack.getNumber() - 1]).setStats(++hits[playerAttack.getNumber() - 1], misses[playerAttack.getNumber() - 1]);
                cells[playerAttack.getNumber() - 1][posX][posY].setBackground(Color.RED);
                ((Grid)myGridPanels[playerDefend.getNumber() - 1]).setGridColor(Color.RED, posX, posY);
            }
            else {
                ((HitMissPanel) hitmissPanels[playerAttack.getNumber() - 1]).setStats(hits[playerAttack.getNumber() - 1], ++misses[playerAttack.getNumber() - 1]);
                cells[playerAttack.getNumber() - 1][posX][posY].setBackground(Color.BLUE);
                ((Grid)myGridPanels[playerDefend.getNumber() - 1]).setGridColor(Color.BLUE, posX, posY);
                played = true;
                if (game.getNumberPlayers() == 1) turn++;
            }
            endGame();
        }
   }

    private void endGame() {
        if (game.getState() != 0) {
            if (game.getState() == 1)
                JOptionPane.showMessageDialog(null, "Congratulations " + game.getPlayer1().getName() + ", you won!", "Game Over", JOptionPane.OK_OPTION);
            else if (game.getState() == 2) {
                if (game.getNumberPlayers() == 2)
                    JOptionPane.showMessageDialog(null, "Congratulations " + game.getPlayer2().getName() + ", you won!", "Game Over", JOptionPane.OK_OPTION);
                else
                    JOptionPane.showMessageDialog(null, "Sorry " + game.getPlayer1().getName() + ", you loose!", "Game Over", JOptionPane.OK_OPTION);
            }
            play.setEnabled(false);
            end.setEnabled(false);
            played = true;
        }
    }

    @Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	public JPanel createSquareJPanel(Color color, int size)
	{
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(color);
		tempPanel.setMinimumSize(new Dimension(size, size));
		tempPanel.setMaximumSize(new Dimension(size, size));
		tempPanel.setPreferredSize(new Dimension(size, size));
		tempPanel.setBorder(new MatteBorder(1, 1, 0, 0, Color.GRAY));
		repaint();
		return tempPanel;
	}
}


