package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PaintContext;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import logic.*;

public class desenho extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	desenho()
	{
		Maze_Builder mazeBuilder = new Maze_Builder();
		mazeBuilder.set_maze_type(0);
		mazeBuilder.set_maze_size(17);
		Maze maze = mazeBuilder.get_maze(new Exit());
	}

	protected void paintComponent(Graphics g){
		g.setColor(Color.blue);
		for(int i = 0 ; i < 17 ; i++)
		{
			for(int j = 0 ; j < 17 ; j++)
			{
					g.drawRect(i*20, j*20, 20, 20);
				//ImageIcon iwall = new ImageIcon("images/wall1.gif");
				//Image wall = iwall.getImage();
				//g.drawImage(wall, i*20, j*20, null);
			}
		}
	}
	
}
