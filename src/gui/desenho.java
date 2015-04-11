package gui;

import java.awt.Dimension;
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
	logic.Game game;
	
	desenho(int type, int size, int nr_of_dragons, int dragon_type, int nr_of_darts) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(17*50, 17*50));
		setSize(new Dimension(17*50, 17*50));
		game = new logic.Game();
		game.set_maze_type(type);
		if (type == 0) {
			game.set_maze_size(10);
			game.set_number_dragons(1);
			game.set_dragon_type(0);
			game.set_number_darts(0);
		}
		else {
			game.set_maze_size(size);
			game.set_number_dragons(nr_of_dragons);
			game.set_dragon_type(dragon_type);
			game.set_number_darts(nr_of_darts);
		}
		game.start_game();
	}
	
	public void print_maze(Graphics g) {
		int size = game.get_maze_size();
		g.setColor(Color.black);
		ImageIcon iwall = new ImageIcon("images/wall.gif");
		Image wall = iwall.getImage();
		ImageIcon ihero = new ImageIcon("images/hero.gif");
		Image hero = ihero.getImage();
		ImageIcon iherosword = new ImageIcon("images/herosword.gif");
		Image herosword = iherosword.getImage();
		ImageIcon iheroshield = new ImageIcon("images/heroshield.gif");
		Image heroshield = iheroshield.getImage();
		ImageIcon iheroswordshield = new ImageIcon("images/heroswordshield.gif");
		Image heroswordshield = iheroswordshield.getImage();
		ImageIcon idragon = new ImageIcon("images/dragon.gif");
		Image dragon = idragon.getImage();
		ImageIcon isword = new ImageIcon("images/sword.gif");
		Image sword = isword.getImage();
		ImageIcon ishield = new ImageIcon("images/shield.gif");
		Image shield = ishield.getImage();
		ImageIcon idart = new ImageIcon("images/dart.gif");
		Image dart = idart.getImage();
		ImageIcon iexit = new ImageIcon("images/exitclosed.gif");
		Image exit = iexit.getImage();
		ImageIcon inada = new ImageIcon("images/nothing.gif");
		Image nada = inada.getImage();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int[] position = {i, j};
				char symbol = game.get_maze().get_board_position(position);
				switch (symbol) {
				case 'X' :
					g.drawImage(wall, j * 50, i * 50, 50, 50, null);
					break;
				case 'h' :
					g.drawImage(hero, j * 50, i * 50, 50, 50, null);
					break;
				case 'a' :
					g.drawImage(herosword, j * 50, i * 50, 50, 50, null);
					break;
				case 'A' :
					g.drawImage(heroswordshield, j * 50, i * 50, 50, 50, null);
					break;
				case 'H' :
					g.drawImage(heroshield, j * 50, i * 50, 50, 50, null);
					break;
				case 'D' :
					g.drawImage(dragon, j * 50, i * 50, 50, 50, null);
					break;
				case 'S' :
					g.drawImage(sword, j * 50, i * 50, 50, 50, null);
					break;
				case 'V' :
					g.drawImage(shield, j * 50, i * 50, 50, 50, null);
					break;
				case 'i' :
					g.drawImage(dart, j * 50, i * 50, 50, 50, null);
					break;
				case 'E' :
					g.drawImage(exit, j * 50, i * 50, 50, 50, null);
					break;
				default:
					g.drawImage(nada, j * 50, i * 50, 50, 50, null);
					break;
				}		
			}
		}
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		print_maze(g);
	}
	
}
