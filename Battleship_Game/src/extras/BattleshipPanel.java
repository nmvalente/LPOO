package extras;

import gui.HitMissPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Represents a BattleshipGrid graphically.
 */
public class BattleshipPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// the width and height of this panel
	private final int PANEL_WIDTH;
	private final int PANEL_HEIGHT;

	private BattleshipGrid grid;
	private HitMissPanel hitMissPanel;

	private int numHits;
	private int numMisses;

	public BattleshipPanel(int width, int height, BattleshipGrid battleshipGrid, HitMissPanel hmp)
	{
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		grid = battleshipGrid;
		hitMissPanel = hmp;

		numHits = 0;
		numMisses = 0;

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		addMouseListener(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						// attack the grid based on where the user clicked
						attackGrid(e.getX(), e.getY());
						//
						// update the graphics
						repaint();

						// have all of the ships been destroyed?
						if (grid.allDestroyed())
						{
							JOptionPane.showMessageDialog(null, "You win!");
							System.exit(0);
						}
					}
				}
				);
	}

	public void paintComponent(Graphics g)
	{
		setBackground(Color.BLACK);
		super.paintComponent(g);

		drawGridLines(g);
		drawGridCells(g);
	}

	/**
	 * Draw grid lines.
	 *
	 * @param    g    the graphics context on which to draw the grid lines
	 */
	private void drawGridLines(Graphics g)
	{
		g.setColor(Color.WHITE);

		// draw vertical grid lines
		for (int x = 0; x < PANEL_WIDTH; x += PANEL_WIDTH / grid.NUM_COLS)
		{
			g.drawLine(x, 0, x, PANEL_HEIGHT - 1);
		}

		// draw horizontal grid lines
		for (int y = 0; y < PANEL_HEIGHT; y += PANEL_HEIGHT / grid.NUM_ROWS)
		{
			g.drawLine(0, y, PANEL_WIDTH - 1, y);
		}
	}



	/**
	 * Draws the grid cells such that empty cells are represented by
	 * black squares, misses by blue squares, and hits by red squares.
	 *
	 * @param    g    the graphics context on which to draw the grid cells
	 */
	private void drawGridCells(Graphics g)
	{
		// iterate over the entire grid
		for (int row = 0; row < grid.NUM_ROWS; row++)
		{
			for (int col = 0; col < grid.NUM_COLS; col++)
			{
				g.setColor(Color.BLACK);
				g.fillRect( 7*PANEL_HEIGHT / grid.NUM_COLS,3*PANEL_WIDTH / grid.NUM_COLS, 59, 59);
				
				// ::: DETERMINE THE VALUE OF THE CELL AT THE CURRENT
				//     ROW AND COLUMN AND DRAW A RECTANGLE OF THE
				//     APPROPRIATE SIZE AND COLOR:
				//     - BLACK FOR EMPTY
				//     - BLUE FOR MISSES
				//     - RED FOR HITS
				//     REMEMBER, USE g.setColor(Color.RED) (FOR EXAMPLE)
				//     TO CHANGE YOUR COLOR BEFORE YOU DRAW YOUR RECTANGLE
				//     USING THE g.fillRect METHOD.
			}
		}
	}


	/**
	 * Determines which grid cell the user clicked on and then attacks
	 * that grid cell. If the user has already attacked the cell that
	 * was clicked on, this method does not attack the cell. This
	 * method also updates the total number of hits and misses and
	 * updates the hit/miss panel accordingly.
	 *
	 * @param    mouseX    the x-coordinate of the user's mouse click
	 * @param    mouseY    the y-coordinate of the user's mouse click
	 */
	private void attackGrid(int mouseX, int mouseY)
	{
		// ::: FILL IN THIS CODE
		
		int k = (mouseX / 60);
		System.out.println(k + "    fdsfdsfsfds"); 

		//hitMissPanel.setStats(numHits, numMisses);
	}
}