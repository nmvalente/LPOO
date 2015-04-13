package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
//import javax.swing.JButton;
import javax.swing.JPanel;

public class desenho extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	logic.Game game;
	
	int keyCodeleft , keyCoderight, keyCodeup, keyCodedown;
	int dartleft = KeyEvent.getExtendedKeyCodeForChar('j');
	int dartright = KeyEvent.getExtendedKeyCodeForChar('l');
	int darteup = KeyEvent.getExtendedKeyCodeForChar('i');
	int dartdown = KeyEvent.getExtendedKeyCodeForChar('k');

	ImageIcon iwall = new ImageIcon("images/wall.gif");
	Image wall = iwall.getImage();
	ImageIcon ihero = new ImageIcon("images/hero.gif");
//	JButton b= new JButton(ihero);
	Image hero = ihero.getImage();
	ImageIcon iherosword = new ImageIcon("images/herosword.gif");
	Image herosword = iherosword.getImage();
	ImageIcon iheroshield = new ImageIcon("images/heroshield.gif");
	Image heroshield = iheroshield.getImage();
	ImageIcon iheroswordshield = new ImageIcon("images/heroswordshield.gif");
	Image heroswordshield = iheroswordshield.getImage();
	ImageIcon iheroburn = new ImageIcon("images/heroburn.gif");
	Image heroburn = iheroburn.getImage();
	ImageIcon iheroburnsword = new ImageIcon("images/heroburnsword.gif");
	Image heroburnsword = iheroburnsword.getImage();
	ImageIcon iherosurvive = new ImageIcon("images/herosurvive.gif");
	Image herosurvive = iherosurvive.getImage();
	ImageIcon iherosurvivesword = new ImageIcon("images/herosurvivesword.gif");
	Image herosurvivesword = iherosurvivesword.getImage();
	ImageIcon idragon = new ImageIcon("images/dragon.gif");
	Image dragon = idragon.getImage();
	ImageIcon idragonsleep = new ImageIcon("images/dragonsleep.gif");
	Image dragonsleep = idragonsleep.getImage();
	ImageIcon idragonsword = new ImageIcon("images/dragonsword.gif");
	Image dragonsword = idragonsword.getImage();
	ImageIcon idragonshield = new ImageIcon("images/dragonshield.gif");
	Image dragonshield = idragonshield.getImage();
	ImageIcon idragondart = new ImageIcon("images/dragondart.gif");
	Image dragondart = idragondart.getImage();
	ImageIcon idragonsleepsword = new ImageIcon("images/dragonsleepsword.gif");
	Image dragonsleepsword = idragonsleepsword.getImage();
	ImageIcon idragonsleepshield = new ImageIcon("images/dragonsleepshield.gif");
	Image dragonsleepshield = idragonsleepshield.getImage();
	ImageIcon idragonsleepdart = new ImageIcon("images/dragonsleepdart.gif");
	Image dragonsleepdart = idragonsleepdart.getImage();
	ImageIcon isword = new ImageIcon("images/sword.gif");
	Image sword = isword.getImage();
	ImageIcon ishield = new ImageIcon("images/shield.gif");
	Image shield = ishield.getImage();
	ImageIcon idart = new ImageIcon("images/dart.gif");
	Image dart = idart.getImage();
	ImageIcon iexitclosed = new ImageIcon("images/exitclosed.gif");
	Image exitclosed = iexitclosed.getImage();
	ImageIcon iexitopen = new ImageIcon("images/exitopen.gif");
	Image exitopen = iexitopen.getImage();
	ImageIcon iwin = new ImageIcon("images/win.gif");
	Image win = iwin.getImage();
	ImageIcon ilose = new ImageIcon("images/lose.gif");
	Image lose = ilose.getImage();
	ImageIcon inada = new ImageIcon("images/nothing.gif");
	Image nada = inada.getImage();

	desenho(int type, int size, int nr_of_dragons, int dragon_type, int nr_of_darts, char a, char d, char w, char s) {
	
		this.addKeyListener(this);
		keyCodeleft = KeyEvent.getExtendedKeyCodeForChar(a);
		keyCoderight = KeyEvent.getExtendedKeyCodeForChar(d);
		keyCodeup = KeyEvent.getExtendedKeyCodeForChar(w);
		keyCodedown = KeyEvent.getExtendedKeyCodeForChar(s);
		setBackground(Color.BLACK);
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

	desenho(String filename, char a, char d, char w, char s) throws IOException {
		this.addKeyListener(this);
		keyCodeleft = KeyEvent.getExtendedKeyCodeForChar(a);
		keyCoderight = KeyEvent.getExtendedKeyCodeForChar(d);
		keyCodeup = KeyEvent.getExtendedKeyCodeForChar(w);
		keyCodedown = KeyEvent.getExtendedKeyCodeForChar(s);
		setBackground(Color.BLACK);
		game = new logic.Game();
		game.load_game_file(filename);
	}
	
	logic.Game get_game() {
		return game;
	}
	
	public void print_maze(Graphics g) {
		requestFocus();
		int size = game.get_maze_size();
		g.setColor(Color.black);
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
				case 'n' :
					g.drawImage(heroburn, j * 50, i * 50, 50, 50, null);
					break;
				case 'm' :
					g.drawImage(heroburnsword, j * 50, i * 50, 50, 50, null);
					break;
				case 'M' :
					g.drawImage(herosurvivesword, j * 50, i * 50, 50, 50, null);
					break;
				case 'N' :
					g.drawImage(herosurvive, j * 50, i * 50, 50, 50, null);
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
				case 'f' :
					g.drawImage(dragonsleepsword, j * 50, i * 50, 50, 50, null);
					break;
				case 'G' :
					g.drawImage(dragonshield, j * 50, i * 50, 50, 50, null);
					break;
				case 'g' :
					g.drawImage(dragonsleepshield, j * 50, i * 50, 50, 50, null);
					break;
				case 'K' :
					g.drawImage(dragondart, j * 50, i * 50, 50, 50, null);
					break;
				case 'k' :
					g.drawImage(dragonsleepdart, j * 50, i * 50, 50, 50, null);
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
					g.drawImage(exitclosed, j * 50, i * 50, 50, 50, null);
					break;
				case 'e' :
					g.drawImage(exitopen, j * 50, i * 50, 50, 50, null);
					break;
				default:
					g.drawImage(nada, j * 50, i * 50, 50, 50, null);
					break;
				}		
			}
			int coord = game.get_maze_size() * 50 / 2 - 125;
			if (game.get_game_state() == 1) {
				g.drawImage(win, coord, coord, 250, 250, null);
				repaint(); 
			}
			if (game.get_game_state() == 2) {
				g.drawImage(lose, coord, coord, 250, 250, null);
				repaint(); 
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
/*		if (e.getKeyCode() == keyCodeleft) play(1);
		else if (e.getKeyCode() == keyCoderight) play(0);
		else if (e.getKeyCode() == keyCodeup) play(2);
		else if (e.getKeyCode() == keyCodedown) play(3);
		else if (e.getKeyCode() == dartleft) play(5);
		else if (e.getKeyCode() == dartright) play(4);
		else if (e.getKeyCode() == dartdown) play(6);
		else if (e.getKeyCode() == dartup) play(7);*/
		switch(e.getKeyCode()){
		case KeyEvent.VK_A: 
			play(1);
			break;
		case KeyEvent.VK_D: 
			play(0);
			break;
		case KeyEvent.VK_W: 
			play(2);
			break;
		case KeyEvent.VK_S: 
			play(3);
			break;
		case KeyEvent.VK_J:
			play(5);
			break;
		case KeyEvent.VK_L: 
			play(4);
			break;
		case KeyEvent.VK_I: 
			play(6);
			break;
		case KeyEvent.VK_K: 
			play(7);
			break;
		}
	}

	private void play(int direction) {
		if (game.get_game_state() == 0) {
			play_hero(direction);
			if (game.get_number_dragons() > 0) {
				maybe_fight();
				if (game.get_game_state() == 0 && game.get_number_dragons() > 0) {
					maybe_burn();
					if (game.get_dragon_type() != 0 && game.get_game_state() == 0 && game.get_number_dragons() > 0) {
						play_dragon();
						maybe_fight();
						if (game.get_game_state() == 0 && game.get_number_dragons() > 0) maybe_burn();
					}
				}
			}
		}
	}

	private void play_hero(int direction) {
		if (direction >= 4 && direction <= 7) {
			if (game.get_game_state() == 0 
			&& game.get_number_dragons() > 0 
			&& game.get_hero().get_hero_darts() > 0) {
				game.hero_dart(direction);
			}
		}
		else if (direction >= 0) {
			game.hero_turn(direction);
		}
		repaint();
	}
	
	private void play_dragon() {
		game.dragon_turn();
		repaint(); 
	}

	private void maybe_fight() {
		ArrayList<Integer> dragons_c = game.dragons_close();
		if (!dragons_c.isEmpty()) {
			game.fight(dragons_c);
			repaint(); 
		}
	}
	
	private void maybe_burn() {
		repaint();
		if (game.burn_hero()) {
			game.get_hero().set_burn();
			game.place_element(game.get_hero());
			repaint(); 
		}
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
