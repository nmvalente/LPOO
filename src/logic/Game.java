package logic;

import java.util.Random;
import java.util.ArrayList;

public class Game {

	private int maze_type;
	private int maze_size;
	private int dragon_type; // type 0 - not moving; type 1 - moving; type 2 - sleeping & moving
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
	private Move_Dragon md = new Move_Dragon();

	public Move_Dragon getMoveDragon(){return md;}
	
	public void start_test_game(char[][] stat_maze, int[] hero_pos, int[] dragon_pos, int[] exit_pos) {
		Maze_Builder mb = new Maze_Builder();
		mb.set_maze_type(maze_type);
		mb.set_maze_size(maze_size);
		exit = new Exit();
		exit.set_position(exit_pos);
		hero = new Hero();
		hero.set_position(hero_pos);
		sword = new Sword();
		sword.kill_sword();
		shield = new Shield();
		shield.kill_shield();
		hero.shield_hero();
		darts = new ArrayList<Dart>();
		dragons = new ArrayList<Dragon>();
		maze = mb.get_maze(exit, stat_maze);
		place_element(exit);
		place_element(hero);
		Dragon dragon = new Dragon();
		dragon.set_position(dragon_pos);
		dragons.add(dragon);
		place_element(dragon);
	}

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
			start_random_game();
		}
		else {
			start_static_game();
		}
	}

	private void start_static_game() {
		shield.kill_shield();
		hero.shield_hero();
		place_element(shield);
		place_element(exit);
		place_element(hero);
		place_element(sword);
		Dragon dragon = new Dragon();
		dragons.add(dragon);
		place_element(dragon);
	}

	private void start_random_game() {
		place_element(exit);
		Random rn = new Random();
		ArrayList<int[]> cells = maze.get_board_cells();
		hero.set_position(cells.remove(rn.nextInt(cells.size())));
		place_element(hero);
		int[][] hero_neigh = hero.get_neighbour_positions();
		for (int i = 0; i < hero_neigh.length; i++) {
			int[] cell = hero_neigh[i];
			for (int j = 0; j < cells.size(); j++) {
				if (cells.get(j)[0] == cell[0] && cells.get(j)[1] == cell[1]) {
					cells.remove(j);
					break;
				}
			}
		}
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

	public void set_dragon_type(int type) {
		dragon_type = type;
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

	public int get_dragon_type() {
		return dragon_type;
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
			Dragon dragon = dragons.get(i);
			for (int j = 0; j < 4; j++) {
				if (dragon.same_position(pos_neigh[j]) && dragon.get_state() != ' ') result.add(i);
			}
		}
		return result;
	}

	public boolean free_path(Element elem1, Element elem2) {
		int[] pos1 = elem1.get_position();
		int[] pos2 = elem2.get_position();
		if (pos1[0] == pos2[0]) {
			int min = Math.min(pos1[1], pos2[1]);
			int max = pos1[1] + pos2[1] - min;
			for (int i = min + 1; i < max; i ++) {
				int[] pos = {pos1[0], i};
				if (maze.get_board_position(pos) == 'X') return false;
			}
			return true;
		}
		else if (pos1[1] == pos2[1]) {
			int min = Math.min(pos1[0], pos2[0]);
			int max = pos1[0] + pos2[0] - min;
			for (int i = min + 1; i < max; i ++) {
				int[] pos = {i, pos1[1]};
				if (maze.get_board_position(pos) == 'X') return false;
			}
			return true;
		}
		else return false;
	}

	public ArrayList<Integer> dragons_burn() {
		int[][] pos_burn = hero.get_burn_positions();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < number_dragons; i++) {
			Dragon dragon = dragons.get(i);
			for (int j = 0; j < 8; j++) {
				if (dragon.same_position(pos_burn[j]) 
						&& dragon.get_state() != ' ' 
						&& dragon.get_state() != 'd' 
						&& dragon.get_state() != 'f'
						&& free_path(hero, dragon)
						) result.add(i);
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
		else {
			for (int i = 0; i < dragons_c.size(); i++) {
				int index = dragons_c.get(i);
				Dragon d = dragons.get(index);
				if (d.get_state() != 'd' && d.get_state() != 'f') {
					hero.set_state(' ');
					maze.set_position(hero);
					break;
				}
			}
		}
		if (number_dragons == 0) {
			exit.open_exit();
			maze.set_position(exit);
		}
	}

	public void burn_hero() {
		hero.set_state(' ');
		maze.set_position(hero);
		compute_game_state();
	}

	public boolean get_shielded_hero() {
		char state = hero.get_state();
		if (state == 'H' || state == 'A') return true;
		return false;
	}

	public void choose_dragon_movement(Dragon dragon) {
		//Move_Dragon md = new Move_Dragon();
		int direction = md.getMove(); 
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
		Random rn = new Random();
		for (int i = 0; i < number_dragons; i++) {
			Dragon dragon = dragons.get(i);
			if (dragon.get_state() != ' ') {
				int sleep_random = 1;
				if (dragon_type == 2) sleep_random = rn.nextInt(4);
				if (sleep_random == 0) {
					dragon.sleep_dragon();
					place_element(dragon);
				}
				else {
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
	}

	public void fight(ArrayList<Integer> dragons_c) {
		hero_vs_dragons(dragons_c);
		for (int i = 0; i < number_darts; i++) place_element(darts.get(i));
		place_element(shield);
		place_element(sword);
		place_element(hero);
		for (int i = 0; i < number_dragons; i++) place_element(dragons.get(i));
		compute_game_state();
	}
}
