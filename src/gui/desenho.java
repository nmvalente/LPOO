package gui;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
<<<<<<< Updated upstream

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

=======
<<<<<<< HEAD
import java.util.Scanner;

import javax.swing.ImageIcon;
//import javax.swing.JButton;
import javax.swing.JPanel;

import cli.Maze_Game;

public class desenho extends JPanel implements MouseListener, MouseMotionListener, KeyListener{

=======

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

>>>>>>> Stashed changes
public class desenho extends JPanel implements MouseListener, MouseMotionListener, KeyListener{

	/**
	 * 
	 */
>>>>>>> origin/master
	private static final long serialVersionUID = 1L;
	logic.Game game;
	cli.Maze_Game mg;

	desenho(int type, int size, int nr_of_dragons, int dragon_type, int nr_of_darts) {
	
		this.addKeyListener(this);
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
		requestFocus();
		int size = game.get_maze_size();
		g.setColor(Color.black);
		
		ImageIcon iwall = new ImageIcon("images/wall.gif");
		Image wall = iwall.getImage();
		ImageIcon ihero = new ImageIcon("images/hero.gif");
<<<<<<< Updated upstream
		JButton b= new JButton(ihero);
		
		//Image hero = ihero.getImage();
=======
<<<<<<< HEAD
//		JButton b= new JButton(ihero);
		Image hero = ihero.getImage();
=======
		JButton b= new JButton(ihero);
		
		//Image hero = ihero.getImage();
>>>>>>> origin/master
>>>>>>> Stashed changes
		ImageIcon iherosword = new ImageIcon("images/herosword.gif");
		Image herosword = iherosword.getImage();
		ImageIcon iheroshield = new ImageIcon("images/heroshield.gif");
		Image heroshield = iheroshield.getImage();
		ImageIcon iheroswordshield = new ImageIcon("images/heroswordshield.gif");
		Image heroswordshield = iheroswordshield.getImage();
		ImageIcon idragon = new ImageIcon("images/dragon.gif");
		Image dragon = idragon.getImage();
		ImageIcon idragonsleep = new ImageIcon("images/dragonsleep.gif");
		Image dragonsleep = idragonsleep.getImage();
		ImageIcon idragonsword = new ImageIcon("images/dragonsword.gif");
		Image dragonsword = idragonsword.getImage();
		ImageIcon idragonshield = new ImageIcon("images/dragonshield.gif");
		Image dragonshield = idragonshield.getImage();
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
					b.paintImmediately(j * 50, i * 50, 50, 50);//, offset, length, x, y);//g.drawImage(hero, j * 50, i * 50, 50, 50, null);
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
				case 'd' :
					g.drawImage(dragonsleep, j * 50, i * 50, 50, 50, null);
					break;
				case 'F' :
					g.drawImage(dragonsword, j * 50, i * 50, 50, 50, null);
					break;
				case 'G' :
					g.drawImage(dragonshield, j * 50, i * 50, 50, 50, null);
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

//	public void jogada(){
//		//String nome = JOptionPane.showInputDialog("Choose movement");
//			char hero_direction = 0;
//				int direction = 0;
//				switch (hero_direction) {
//				case 'w' :
//					direction = 2;
//					break;
//				case 'a' :
//					direction = 1;
//					break;
//				case 's' :
//					direction = 3;
//					break;
//				case 'd' :
//					direction = 0;
//					break;
//				}			
//		game.hero_turn(direction);
//	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		print_maze(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_A:
			game.hero_turn(1);
<<<<<<< Updated upstream
			//mg.play(mg);
			repaint(); 
=======
<<<<<<< HEAD
			repaint(); 
			play_dragon();
=======
			//mg.play(mg);
			repaint(); 
>>>>>>> origin/master
>>>>>>> Stashed changes
			break;

		case KeyEvent.VK_D: 
			game.hero_turn(0);
			repaint(); 
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
			play_dragon();
=======
>>>>>>> origin/master
>>>>>>> Stashed changes
			break;

		case KeyEvent.VK_W: 
			game.hero_turn(2);
			repaint(); 
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
			play_dragon();
=======
>>>>>>> origin/master
>>>>>>> Stashed changes
			break;

		case KeyEvent.VK_S: 
			game.hero_turn(3);
			repaint(); 
<<<<<<< Updated upstream
			break;
		}
=======
<<<<<<< HEAD
			play_dragon();
			break;
			
		case KeyEvent.VK_J:
			if (game.get_game_state() == 0 
			&& game.get_number_dragons() > 0 
			&& game.get_hero().get_hero_darts() > 0) {
				game.hero_dart(1);
				repaint(); 
				play_dragon();
			}
			break;

		case KeyEvent.VK_L: 
			if (game.get_game_state() == 0 
			&& game.get_number_dragons() > 0 
			&& game.get_hero().get_hero_darts() > 0) {
				game.hero_dart(0);
				repaint(); 
				play_dragon();
			}
			break;

		case KeyEvent.VK_I: 
			if (game.get_game_state() == 0 
			&& game.get_number_dragons() > 0 
			&& game.get_hero().get_hero_darts() > 0) {
				game.hero_dart(2);
				repaint(); 
				play_dragon();
			}
			break;

		case KeyEvent.VK_K: 
			if (game.get_game_state() == 0 
			&& game.get_number_dragons() > 0 
			&& game.get_hero().get_hero_darts() > 0) {
				game.hero_dart(3);
				repaint(); 
				play_dragon();
			}
			break;
		}		
	}

	private void play_dragon() {
		game.dragon_turn();
		repaint(); 
=======
			break;
		}
>>>>>>> origin/master
>>>>>>> Stashed changes
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
