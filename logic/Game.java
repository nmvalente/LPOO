package logic;

import java.util.Random;
import java.util.ArrayList;

public class Game {
	
	private int maze_type;
	private int maze_size;
	private Maze maze;
	private Exit exit;
	private Hero hero;
	private Sword sword;
	private Shield shield;
	private ArrayList<Dart> darts;
	private ArrayList<Dragon> dragons;
	private int number_dragons;
	private int number_darts;
	private int state = 0; // state 0 - game continues; state 1 - hero wins, state 2 - hero loses
	
	public void start_game() {
		Maze_Builder mb = new Maze_Builder();
		mb.set_maze_type(maze_type);
		mb.set_maze_size(maze_size);
		exit = new Exit();
		hero = new Hero();
		sword = new Sword();
		shield = new Shield();
		darts = new ArrayList<Dart>();
		dragons = new ArrayList<Dragon>();
		if (maze_type == 1) {
			new Random_Exit(this, exit);
		}
		maze = mb.get_maze(exit);
		if (maze_type == 1) {
			place_element(exit);
			Random rn = new Random();
			ArrayList<int[]> cells = maze.get_board_cells();
			hero.set_position(cells.remove(rn.nextInt(cells.size())));
			place_element(hero);
			sword.set_position(cells.remove(rn.nextInt(cells.size())));
			place_element(sword);
			shield.set_position(cells.remove(rn.nextInt(cells.size())));
			place_element(shield);
			for (int i = 0; i < number_darts; i++) {
				Dart dart = new Dart();
				dart.set_position(cells.remove(rn.nextInt(cells.size())));
				darts.add(dart);
				place_element(dart);
			}
			for (int i = 0; i < number_dragons; i++) {
				Dragon dragon = new Dragon();
				dragon.set_position(cells.remove(rn.nextInt(cells.size())));
				dragons.add(dragon);
				place_element(dragon);
			}
		}
		else {
			shield.kill_shield();
			place_element(shield);
			place_element(exit);
			place_element(hero);
			place_element(sword);
			Dragon dragon = new Dragon();
			dragons.add(dragon);
			place_element(dragon);
		}
	}
	
	public void place_element(Element elem) {
		maze.set_position(elem);
	}
	
	public void remove_element(Element elem) {
		maze.remove_position(elem);
	}

	public void set_maze_type(int type) {
		maze_type = type;
	}

	public void set_maze_size(int size) {
		maze_size = size;
	}
	
	public void set_number_dragons(int number) {
		number_dragons = number;
	}
	
	public void set_number_darts(int number) {
		number_darts = number;
	}
	
	public int get_maze_size() {
		return maze_size;
	}

	public Maze get_maze() {
		return maze;
	}
	
	public Hero get_hero() {
		return hero;
	}
	
	public Sword get_sword() {
		return sword;
	}
	
	public Shield get_shield() {
		return shield;
	}

	public ArrayList<Dart> get_darts() {
		return darts;
	}

	public Exit get_exit() {
		return exit;
	}

	public ArrayList<Dragon> get_dragons() {
		return dragons;
	}

	public int get_number_dragons() {
		return number_dragons;
	}
	
	public int get_number_darts() {
		return number_darts;
	}
	
	public void dec_number_dragons() {
		number_dragons--;
	}

	public void dec_number_darts() {
		number_darts--;
	}

	public void compute_game_state() {
		if (hero.get_state() == ' ') state = 2;
		else {
			int[] hero_pos = hero.get_position();
			if (hero_pos[0] == 0 || hero_pos[1] == 0 
					|| hero_pos[0] == maze_size - 1 || hero_pos[1] == maze_size - 1) state = 1;
		}
	}
	
	public int get_game_state() {
		return state;
	}
	
	public ArrayList<Integer> dragons_close() {
		int[][] pos_neigh = hero.get_neighbour_positions();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < number_dragons; i++) {
			for (int j = 0; j < 4; j++) {
				Dragon dragon = dragons.get(i);
				if (dragon.same_position(pos_neigh[j]) && dragon.get_state() != ' ') result.add(i);
			}
		}
		return result;
	}
	
	public void hero_vs_dragons(ArrayList<Integer> dragons_c) {
		char hero_state = hero.get_state();
		if (hero_state == 'A' || hero_state == 'a') {
			for (int i = 0; i < dragons_c.size(); i++) {
				int index = dragons_c.get(i);
				Dragon d = dragons.remove(index);
				d.kill_dragon();
				place_element(d);
				dec_number_dragons();
			}
		}
		else if (dragons_c.size() > 0) {
			hero.set_state(' ');
			maze.set_position(hero);			
		}
		if (number_dragons == 0) {
			exit.open_exit();
			maze.set_position(exit);
		}
	}
	
	public void choose_dragon_movement(Dragon dragon) {
		Random rn = new Random();
		Move_Dragon md = new Move_Dragon();
		int direction = rn.nextInt(4); 
		switch (direction) {
		case 0 :
			md.move_dragon_right(this, dragon);
			break;
		case 1 :
			md.move_dragon_left(this, dragon);
			break;
		case 2 :
			md.move_dragon_up(this, dragon);
			break;
		case 3 :
			md.move_dragon_down(this, dragon);
			break;
		}		
	}
	
	public void choose_hero_movement(int direction) {
		Move_Hero mh = new Move_Hero();
		switch (direction) {
		case 0 :
			mh.move_hero_right(this);
			break;
		case 1 :
			mh.move_hero_left(this);
			break;
		case 2 :
			mh.move_hero_up(this);
			break;
		case 3 :
			mh.move_hero_down(this);
			break;
		}				
	}
	
	public void choose_dart_movement(int direction) {
		Throw_Dart td = new Throw_Dart();
		switch (direction) {
		case 0 :
			td.throw_dart_right(this);
			break;
		case 1 :
			td.throw_dart_left(this);
			break;
		case 2 :
			td.throw_dart_up(this);
			break;
		case 3 :
			td.throw_dart_down(this);
			break;
		}
	}
	
	public void hero_turn(int direction) {
		remove_element(hero);
		choose_hero_movement(direction);
		place_element(hero);
		compute_game_state();
	}

	public void hero_dart(int direction) {
		choose_dart_movement(direction);
	}
	
	public void dragon_turn() {
		for (int i = 0; i < number_dragons; i++) {
			Dragon dragon = dragons.get(i);
			if (dragon.get_state() != ' ') {
				remove_element(dragon);
				choose_dragon_movement(dragon);
				for (int j = 0; j < number_darts; j++) {
					place_element(darts.get(j));
				}
				place_element(shield);
				place_element(sword);
				place_element(hero);
				place_element(dragon);
			}
		}		
	}

	public void fight(ArrayList<Integer> dragons_c) {
		hero_vs_dragons(dragons_c);
		place_element(hero);
		for (int i = 0; i < number_dragons; i++) place_element(dragons.get(i));
		place_element(shield);
		for (int i = 0; i < number_darts; i++) place_element(darts.get(i));
		compute_game_state();
	}
	
}
