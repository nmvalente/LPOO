package gui;

import logic.Board;
import logic.Game;
import logic.Player;
import logic.Position;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import org.omg.CORBA.portable.RemarshalException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * The Class Grid.
 */
public class Grid extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The grid. */
	private JPanel grid[][];

	private Game game;

	/**
	 * Create the panel.
	 *
	 * @param row the number of rows
	 * @param col the number of columns
	 * @param obj the obj the Grid uses like Listener
	 */
	public Grid(int row, int col, @SuppressWarnings("rawtypes") Class obj, String namePlayer) {
		game = Game.Instance();
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
				Object panel = null;
				try {
					panel = obj.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				((JPanel) panel).setBorder(border);
				((JPanel) panel).setMinimumSize(new Dimension(40,40));
				((JPanel) panel).setPreferredSize(new Dimension(60,60));
				((JPanel) panel).setBackground(Color.black);
				add(((JPanel) panel), gbc_panel);
				grid[i][j] = ((JPanel) panel);
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
		JLabel la = new JLabel(namePlayer);
		add(la, gbc_panel);
	}

	/**
	 * Create the panel.
	 *
	 * @param row the number of rows
	 * @param col the number of columns
	 * @wbp.parser.constructor
	 */
	public Grid(int row, int col, String namePlayer) {
		game = Game.Instance();
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
		JLabel la = new JLabel(namePlayer);
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
	 * @param obj the obj the Grid uses like Listener
	 */
	public void setGrid(Player player, int row, int col, @SuppressWarnings("rawtypes") Class obj){
		for(int i = 0  ; i < row ; i++) {
			for (int j = 0; j < col; j++) {
				Position position = Position.Instance(i, j);
				int shipIndex = player.getBoard().getPosition(position);
				if (shipIndex > -1) {
					Color color = player.getShips().get(shipIndex).getColor();
					grid[i][j].setBackground(color);
				}
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
		frame.getContentPane().add(new Grid(10,10, ChancesListener.class, "name player"));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}   
}
