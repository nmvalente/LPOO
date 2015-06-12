package gui;

import logic.Game;
import logic.Player;
import logic.Position;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The Class Grid.
 */
public class Grid extends JPanel {

	int posX;
	int posY;
	//private int numberHits = 0;
	public int getPosX(){return posX;}
	public int getPosY(){return posY;}
	public Player opp, me;

	public Game game;
	/** The default background. */
	private Color defaultBackground = Color.GRAY; 

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The grid. */
	private JPanel grid[][];

	/**
	 * Create the panel.
	 *
	 * @param row the number of rows
	 * @param col the number of columns
	 */
	public Grid(int row, int col, Player opp, Player me) {
		//game = game.Instance();
		grid = new JPanel [row][col];
		this.opp = opp;
		this.me = me;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{};
		gridBagLayout.columnWeights = new double[]{};
		setLayout(gridBagLayout);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		Character minusculo = 'a';
		Character maiuscula = 'A';
		for(int i = 0  ; i < row ; i++)
			for(int j = 0 ; j < col ; j++)
			{
				gbc_panel.gridx = j+2;
				gbc_panel.gridy = i+2;	
				Border border = null;
				if (i < 9) {
					if (j < 9) {
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					}
				} else {
					if (j < 9) {
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
					}
				}
				JPanel panel = new JPanel();
				panel.setBorder(border);
				panel.setMinimumSize(new Dimension(40,40));
				panel.setPreferredSize(new Dimension(60,60));
				panel.setBackground(Color.black);
				add(panel, gbc_panel);
				grid[i][j] = panel;
				grid[i][j].addMouseListener(new MyAdapter(i,j));
			} 
		for(int k = 0; k < row ; k++)
		{			
			JPanel panel = new JPanel();

			gbc_panel.gridx = k+2;
			gbc_panel.gridy = 1;

			JLabel l = new JLabel(minusculo.toString());
			minusculo++;
			l.setForeground(Color.WHITE);
			panel.setMinimumSize(new Dimension(40,40));
			panel.setPreferredSize(new Dimension(60,60));
			panel.setBackground(Color.black);
			panel.add(l);
			add(panel, gbc_panel);
		}

		for(int k = 0; k < col ; k++)
		{			
			JPanel panel = new JPanel();

			gbc_panel.gridx = 1;
			gbc_panel.gridy = k+2;

			JLabel l = new JLabel(maiuscula.toString());
			maiuscula++;
			l.setForeground(Color.WHITE);
			panel.setMinimumSize(new Dimension(40,40));
			panel.setPreferredSize(new Dimension(60,60));
			panel.setBackground(Color.black);
			panel.add(l);
			add(panel, gbc_panel);
		}
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(40,40));
		panel.setPreferredSize(new Dimension(60,60));
		panel.setBackground(Color.black);
		add(panel, gbc_panel);

		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		gbc_panel.gridwidth = GridBagConstraints.REMAINDER;
		JLabel la = new JLabel(opp.getName());
		add(la, gbc_panel);
	}

	/**
	 * Create the panel.
	 *
	 * @param row the number of rows
	 * @param col the number of columns
	 * @wbp.parser.constructor
	 */
	public Grid(int row, int col, Player player) {
		grid = new JPanel [row][col];
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{};
		gridBagLayout.columnWeights = new double[]{};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_panel = new GridBagConstraints();

		Character minusculo = 'a';
		Character maiuscula = 'A';
		for(int i = 0  ; i < row ; i++)
			for(int j = 0 ; j < col ; j++)
			{
				gbc_panel.gridx = j+2;
				gbc_panel.gridy = i+2;
				Border border = null;
				if (i < 9) {
					if (j < 9) {
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					}
				} else {
					if (j < 9) {
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
					}
				}
				JPanel panel = new JPanel();
				panel.setBorder(border);
				panel.setMinimumSize(new Dimension(40,40));
				panel.setPreferredSize(new Dimension(60,60));
				panel.setBackground(Color.black);

				add(panel, gbc_panel);
				grid[i][j] = panel;
			}
		for(int k = 0; k < row ; k++)
		{			
			JPanel panel = new JPanel();

			gbc_panel.gridx = k+2;
			gbc_panel.gridy = 1;

			JLabel l = new JLabel(minusculo.toString());
			minusculo++;
			l.setForeground(Color.WHITE);
			panel.setMinimumSize(new Dimension(40,40));
			panel.setPreferredSize(new Dimension(60,60));
			panel.setBackground(Color.black);
			panel.add(l);
			add(panel, gbc_panel);
		}

		for(int k = 0; k < col ; k++)
		{			
			JPanel panel = new JPanel();

			gbc_panel.gridx = 1;
			gbc_panel.gridy = k+2;

			JLabel l = new JLabel(maiuscula.toString());
			maiuscula++;
			l.setForeground(Color.WHITE);
			panel.setMinimumSize(new Dimension(40,40));
			panel.setPreferredSize(new Dimension(60,60));
			panel.setBackground(Color.black);
			panel.add(l);
			add(panel, gbc_panel);
		}
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(40,40));
		panel.setPreferredSize(new Dimension(60,60));
		panel.setBackground(Color.black);
		add(panel, gbc_panel);

		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		gbc_panel.gridwidth = GridBagConstraints.REMAINDER;
		JLabel la = new JLabel(player.getName());
		add(la, gbc_panel);     
	}

	/**
	 * Gets the grid.
	 *
	 * @return the grid
	 */
	public JPanel[][] getGrid() {
		return grid;
	}


	/**
	 * Sets the grid.
	 * @param player the player whose board we want to draw
	 * @param row the number of rows
	 * @param col the numher of columns
	 */
	public void setGrid(Player player, int row, int col, boolean opponent){
		for(int i = 0  ; i < row ; i++) {
			for (int j = 0; j < col; j++) {
				Position position = Position.Instance(i, j);
				int shipIndex;
                if (opponent) shipIndex = player.getOpponent().getPosition(position);
                else shipIndex = player.getBoard().getPosition(position);
				if (shipIndex > -1) {
					Color color = player.getShips().get(shipIndex).getColor();
					grid[i][j].setBackground(color);
				}
                else if (shipIndex == -2) grid[i][j].setBackground(Color.BLUE);
                else if (shipIndex == -3) grid[i][j].setBackground(Color.RED);
            }
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Testing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
//		Player playerAux1 = new Player(1, "name1");
//		Player playerAux2 = new Player(2, "name2");
//		frame.getContentPane().add(new Grid(10,10, playerAux1, playerAux2));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class MyAdapter extends MouseAdapter {
		public int linha, coluna;
		public MyAdapter(int i, int j){
			linha = i;
			coluna = j;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			{
				JPanel panel = (JPanel)e.getSource();
				if(panel.getBackground() == Color.LIGHT_GRAY)
					System.out.println("do nothing");
				else 
				{
					posX=linha;
					posY=coluna;
					//Position position = Position.Instance(posX, posY);
					panel.setBackground(Color.LIGHT_GRAY); 

				}
			}

		}
		@Override
		public void mouseEntered(MouseEvent e) {
			JPanel panel = (JPanel)e.getSource();
			if(panel.getBackground() == Color.black)
				panel.setBackground(Color.GRAY);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JPanel panel = (JPanel)e.getSource();
			if(panel.getBackground() == Color.GRAY)
			{
				defaultBackground=Color.BLACK;
				panel.setBackground(defaultBackground);
			}
		}

	}
	public void setGridColor(Color color, int row, int col){
		grid[row][col].setBackground(color);
	}
}
