package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * The Class OpponentsGrid.
 */
public class OpponentsGrid extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The grid. */
	private static JPanel grid[][];
	
	/**
	 * Create the panel.
	 *
	 * @param row the number of rows of the Opponents Grid
	 * @param col the number of columns of the Opponents Grid
	 */
	public OpponentsGrid(int row, int col) {
		grid = new JPanel [row][col];
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		
		for(int i = 0  ; i < row ; i++)
			for(int j = 0 ; j < col ; j++)
			{
				gbc_panel.gridx = j;
				gbc_panel.gridy = i;	
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
                JPanel panel = new ChancesListener();
                panel.setBorder(border);
                panel.setMinimumSize(new Dimension(40,40));
                panel.setPreferredSize(new Dimension(60,60));
                panel.setBackground(Color.black);
                add(panel, gbc_panel);
                grid[i][j] = panel;
			}
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
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new OpponentsGrid(10,10));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }   
}
