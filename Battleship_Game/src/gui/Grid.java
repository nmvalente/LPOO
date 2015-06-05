package gui;

import extras.TestGrid02.TestPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Grid extends JPanel {
	private static JPanel grid[][];
	/**
	 * Create the panel.
	 */
	public Grid(int row, int col) {
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
	 
	public JPanel[][] getGrid() {
		return grid;
	}

	public static void main(String[] args) {
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new Grid(10,10));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }   
}
