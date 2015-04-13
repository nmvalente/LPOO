package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

/**
 * The Class Game - contains all the game elements, as well as the board the game mecanics.
 */
public class Game {

	/** The type of maze: 0 - static, 1 - random. */
	private int maze_type;
	
	/** The size of the maze. */
	private int maze_size;
	
	/** The type of dragons: 0 - not moving; 1 - moving; 2 - sleeping & moving. */
	private int dragon_type;
	
	/** The maze for the game. */
	private Maze maze;
	
	/** The exit of the board. */
	private Exit exit;
	
	/** The hero. */
	private Hero hero;
	
	/** The sword. */
	private Sword sword;
	
	/** The shield. */
	private Shield shield;
	
	/** The darts. */
	private ArrayList<Dart> darts;
	
	/** The dragons. */
	private ArrayList<Dragon> dragons;
	
	/** The number of live dragons in the game. */
	private int number_dragons;
	
	/** The number of darts on the board. */
	private int number_darts;
	
	/** The state of the game: 0 - game continues; 1 - hero wins, 2 - hero loses. */
	private int state = 0;
	
	/** The newline character. */
	private static String newline = System.getProperty("line.separator");
	
	/**
	 * Starts a new test_game with the provided elements, to use during tests.
	 *
	 * @param stat_maze the static maze
	 * @param hero_pos the hero's position
	 * @param dragon_pos the single dragon's position
	 * @param sword_pos the sword's position
	 * @param shield_pos the shield's position
	 * @param dart_pos the single dart's position
	 * @param exit_pos the position of the exit
	 */
	public void start_test_game(char[][] stat_maze, int[] hero_pos, int[] dragon_pos, int[] sword_pos, int[] shield_pos, int[] dart_pos, int[] exit_pos) {
		Maze_Builder mb = new Maze_Builder();
		mb.set_maze_type(maze_type);
		mb.set_maze_size(maze_size);
		exit = new Exit();
		exit.set_position(exit_pos);
		hero = new Hero();
		hero.set_position(hero_pos);
		sword = new Sword();
		sword.set_position(sword_pos);
		sword.kill_sword();
		shield = new Shield();
		shield.set_position(shield_pos);
		shield.kill_shield();
		hero.shield_hero();
		darts = new ArrayList<Dart>();
		Dart dart = new Dart();
		dart.set_position(dart_pos);
		darts.add(dart);
		dragons = new ArrayList<Dragon>();
		maze = mb.get_maze(exit, stat_maze);
		place_element(exit);
		place_element(hero);
		place_element(dart);
		Dragon dragon = new Dragon();
		dragon.set_position(dragon_pos);
		dragons.add(dragon);
		place_element(dragon);
	}

	/**
	 * Starts a new game.
	 */
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

	/**
	 * Used to start a static game.
	 */
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

	/**
	 * Used to start a random game.
	 */
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
	
	/**
	 * Loads a game file, making a new game with the information on the file.
	 *
	 * @param filename the path and name of the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void load_game_file(String filename) throws IOException {
	    try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
	        String line = br.readLine();
	        maze_type = Integer.parseInt(line);
	        line = br.readLine();
	        maze_size = Integer.parseInt(line);
	        line = br.readLine();
	        dragon_type = Integer.parseInt(line);
	        line = br.readLine();
	        char[][] board = load_board(line);
	        line = br.readLine();
	        load_exit(line);
			Maze_Builder mb = new Maze_Builder();
			mb.set_maze_type(0);
			mb.set_maze_size(maze_size);
			maze = mb.get_maze(exit, board);
	        line = br.readLine();
	        load_hero(line);
	        line = br.readLine();
	        load_sword(line);
	        line = br.readLine();
	        load_shield(line);
	        line = br.readLine();
			place_element(exit);
			place_element(sword);
			place_element(shield);
	        number_darts = Integer.parseInt(line);
			darts = new ArrayList<Dart>();
	        for (int i = 0; i < number_darts; i++) {
	        	line = br.readLine();
		        Dart dart = load_dart(line);
		        darts.add(dart);
				place_element(dart);
	        }
	        line = br.readLine();
	        number_dragons = Integer.parseInt(line);
			dragons = new ArrayList<Dragon>();
	        for (int i = 0; i < number_dragons; i++) {
	        	line = br.readLine();
		        Dragon dragon = load_dragon(line);
		        dragons.add(dragon);
				place_element(dragon);
	        }
			place_element(hero);
	    }
		
	}

	/**
	 * Makes a dragon from the appropriate line extracted from the file.
	 *
	 * @param line the line with the dragon's information
	 * @return the dragon
	 */
	private Dragon load_dragon(String line) {
		String[] dragon_info = line.split(";");
		char dragon_state = dragon_info[0].charAt(0);
		int[] dragon_pos = {Integer.parseInt(dragon_info[1]), Integer.parseInt(dragon_info[2])};
		Dragon dragon = new Dragon();
		dragon.set_state (dragon_state);
		dragon.set_position(dragon_pos);
		return dragon;
	}

	/**
	 * Makes a dart from the appropriate line extracted from the file.
	 *
	 * @param line the line with the dart's information
	 * @return the dart
	 */
	private Dart load_dart(String line) {
		String[] dart_info = line.split(";");
		char dart_state = dart_info[0].charAt(0);
		int[] dart_pos = {Integer.parseInt(dart_info[1]), Integer.parseInt(dart_info[2])};
		Dart dart = new Dart();
		dart.set_state (dart_state);
		dart.set_position (dart_pos);
		return dart;
	}

	/**
	 * Makes the shield from the appropriate line extracted from the file.
	 *
	 * @param line the line with the shield's information
	 * @return the shield
	 */
	private void load_shield(String line) {
		String[] shield_info = line.split(";");
		char shield_state = shield_info[0].charAt(0);
		int[] shield_pos = {Integer.parseInt(shield_info[1]), Integer.parseInt(shield_info[2])};
		shield = new Shield();
		shield.set_state (shield_state);
		shield.set_position (shield_pos);
	}

	/**
	 * Makes the sword from the appropriate line extracted from the file.
	 *
	 * @param line the line with the sword's information
	 * @return the sword
	 */
	private void load_sword(String line) {
		String[] sword_info = line.split(";");
		char sword_state = sword_info[0].charAt(0);
		int[] sword_pos = {Integer.parseInt(sword_info[1]), Integer.parseInt(sword_info[2])};
		sword = new Sword();
		sword.set_state (sword_state);
		sword.set_position (sword_pos);
	}

	/**
	 * Makes the hero from the appropriate line extracted from the file.
	 *
	 * @param line the line with the hero's information
	 * @return the hero
	 */
	private void load_hero(String line) {
		String[] hero_info = line.split(";");
		char hero_state = hero_info[0].charAt(0);
		int[] hero_pos = {Integer.parseInt(hero_info[1]), Integer.parseInt(hero_info[2])};
		int hero_darts = Integer.parseInt(hero_info[3]);
		hero = new Hero();
		hero.set_state (hero_state);
		hero.set_position (hero_pos);
		hero.set_hero_darts(hero_darts);
	}

	/**
	 * Makes the exit from the appropriate line extracted from the file.
	 *
	 * @param line the line with the exit's information
	 * @return the exit
	 */
	private void load_exit(String line) {
		String[] exit_info = line.split(";");
		char exit_state = exit_info[0].charAt(0);
		int[] exit_pos = {Integer.parseInt(exit_info[1]), Integer.parseInt(exit_info[2])};
		exit = new Exit();
		exit.set_state (exit_state);
		exit.set_position (exit_pos);
	}

	/**
	 * Makes the game board from the appropriate line extracted from the file.
	 *
	 * @param line the line with the board's information
	 * @return the board matrix
	 */
	private char[][] load_board(String line) {
		char[][] board = new char[maze_size][maze_size];
		int position = 0;
		for (int i = 0; i < maze_size; i++) {
			for (int j = 0; j < maze_size; j++) {
				board[i][j] = line.charAt(position);
				position += 2;
			}
		}
		return board;
	}

	/**
	 * Places a game element on the board.
	 *
	 * @param elem the element to be placed
	 */
	public void place_element(Element elem) {
		maze.set_position(elem);
	}

	/**
	 * Removes a game element from the board.
	 *
	 * @param elem the element to be removed
	 */
	private void remove_element(Element elem) {
		maze.remove_position(elem);
	}

	/**
	 * Sets the maze type.
	 *
	 * @param type the new maze type
	 */
	public void set_maze_type(int type) {
		maze_type = type;
	}

	/**
	 * Sets the maze size.
	 *
	 * @param size the new maze size
	 */
	public void set_maze_size(int size) {
		maze_size = size;
	}

	/**
	 * Sets the dragon type.
	 *
	 * @param type the new dragon type
	 */
	public void set_dragon_type(int type) {
		dragon_type = type;
	}

	/**
	 * Sets the number of dragons.
	 *
	 * @param number the new number of dragons
	 */
	public void set_number_dragons(int number) {
		number_dragons = number;
	}

	/**
	 * Sets the number of darts.
	 *
	 * @param number the new number of darts
	 */
	public void set_number_darts(int number) {
		number_darts = number;
	}

	/**
	 * Gets the maze type.
	 *
	 * @return the maze type
	 */
	public int get_maze_type() {
		return maze_type;
	}

	/**
	 * Gets the maze size.
	 *
	 * @return the maze size
	 */
	public int get_maze_size() {
		return maze_size;
	}

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze get_maze() {
		return maze;
	}

	/**
	 * Gets the hero.
	 *
	 * @return the hero
	 */
	public Hero get_hero() {
		return hero;
	}

	/**
	 * Gets the sword.
	 *
	 * @return the sword
	 */
	public Sword get_sword() {
		return sword;
	}

	/**
	 * Gets the shield.
	 *
	 * @return the shield
	 */
	public Shield get_shield() {
		return shield;
	}

	/**
	 * Gets the darts.
	 *
	 * @return the darts
	 */
	public ArrayList<Dart> get_darts() {
		return darts;
	}

	/**
	 * Gets the exit.
	 *
	 * @return the exit
	 */
	public Exit get_exit() {
		return exit;
	}

	/**
	 * Gets the dragons.
	 *
	 * @return the dragons
	 */
	public ArrayList<Dragon> get_dragons() {
		return dragons;
	}

	/**
	 * Gets the number of dragons.
	 *
	 * @return the number of dragons
	 */
	public int get_number_dragons() {
		return number_dragons;
	}

	/**
	 * Gets the dragon type.
	 *
	 * @return the dragon type
	 */
	public int get_dragon_type() {
		return dragon_type;
	}

	/**
	 * Gets the number of darts.
	 *
	 * @return the number of darts
	 */
	public int get_number_darts() {
		return number_darts;
	}

	/**
	 * Decreases the number of dragons.
	 */
	void dec_number_dragons() {
		number_dragons--;
	}

	/**
	 * Decreases the number of darts.
	 */
	void dec_number_darts() {
		number_darts--;
	}

	/**
	 * Computes the game state, that is if the game continues, or the hero won, or the hero lost.
	 */
	public void compute_game_state() {
		if (hero.get_state() == ' ') state = 2;
		else {
			int[] hero_pos = hero.get_position();
			if (hero_pos[0] == 0 || hero_pos[1] == 0 
					|| hero_pos[0] == maze_size - 1 || hero_pos[1] == maze_size - 1) state = 1;
		}
	}

	/**
	 * Gets the game state.
	 *
	 * @return the game state
	 */
	public int get_game_state() {
		return state;
	}

	/**
	 * Determines the dragons close to the hero.
	 *
	 * @return the array list with the indices of the dragons close to the hero
	 */
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

	/**
	 * Determines if there is a free path (no walls) between to elements.
	 *
	 * @param elem1 the first element, usually the hero or a dragon
	 * @param elem2 the second element, usually a dragon or the hero
	 * @return true, if the path is clear
	 */
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

	/**
	 * Determines a list of dragons that can burn the hero
	 *
	 * @return the array list with the indices of the dragons that can burn the hero
	 */
	private ArrayList<Integer> dragons_burn() {
		int[][] pos_burn = hero.get_burn_positions();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < number_dragons; i++) {
			Dragon dragon = dragons.get(i);
			for (int j = 0; j < 8; j++) {
				if (dragon.same_position(pos_burn[j]) 
						&& dragon.get_state() != ' ' 
						&& dragon.get_state() != 'd' 
						&& dragon.get_state() != 'f'
						&& dragon.get_state() != 'g'
						&& dragon.get_state() != 'k'
						&& free_path(hero, dragon)
						) result.add(i);
			}
		}
		return result;
	}

	/**
	 * Whenever the hero is close to one or more dragons, this function determines the result of the fight.
	 *
	 * @param dragons_c the dragons close to the hero
	 */
	private void hero_vs_dragons(ArrayList<Integer> dragons_c) {
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
				if (d.get_state() != 'd' && d.get_state() != 'f' && d.get_state() != 'g' && d.get_state() != 'k') {
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

	/**
	 * Tries to burn the hero.
	 *
	 * @return true, if the hero is burned
	 */
	public boolean burn_hero() {
		if (!get_shielded_hero()) {
			ArrayList<Integer> dragons_b = dragons_burn();
			if (!dragons_b.isEmpty()) {
				hero.set_state(' ');
				maze.set_position(hero);
				compute_game_state();
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines if the hero has a shield
	 *
	 * @return true. if the hero has a shield
	 */
	private boolean get_shielded_hero() {
		char state = hero.get_state();
		if (state == 'H' || state == 'A') return true;
		return false;
	}

	/**
	 * Chooses the next movement for a dragon.
	 *
	 * @param dragon the dragon
	 */
	private void choose_dragon_movement(Dragon dragon) {
		Move_Dragon md = new Move_Dragon();
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

	/**
	 * Chooses the next movement for the hero.
	 *
	 * @param direction the direction the hero wishes to follow
	 */
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

	/**
	 * Chooses the movement of a dar thrown by the hero.
	 *
	 * @param direction the direction of the dart
	 */
	private void choose_dart_movement(int direction) {
		Throw_Dart td = new Throw_Dart();
		switch (direction) {
		case 4 :
			td.throw_dart_right(this);
			break;
		case 5 :
			td.throw_dart_left(this);
			break;
		case 6 :
			td.throw_dart_up(this);
			break;
		case 7 :
			td.throw_dart_down(this);
			break;
		}
	}

	/**
	 * Computes the hero's turn in the game.
	 *
	 * @param direction the direction the hero wishes to follow
	 */
	public void hero_turn(int direction) {
		remove_element(hero);
		choose_hero_movement(direction);
		place_element(hero);
		compute_game_state();
	}

	/**
	 * To use when the hero throws a dart.
	 *
	 * @param direction the direction the dart is thrown
	 */
	public void hero_dart(int direction) {
		choose_dart_movement(direction);
		if (number_dragons == 0) {
			exit.open_exit();
			maze.set_position(exit);
		}
	}

	/**
	 * Computes the dragons' turn.
	 */
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
					for (int j = 0; j < number_dragons; j++) {
						place_element(dragons.get(j));
					}
				}
			}
		}		
	}

	/**
	 * Computes a fight and its result.
	 *
	 * @param dragons_c the dragons close to the hero
	 */
	public void fight(ArrayList<Integer> dragons_c) {
		hero_vs_dragons(dragons_c);
		for (int i = 0; i < number_darts; i++) place_element(darts.get(i));
		place_element(shield);
		place_element(sword);
		place_element(hero);
		for (int i = 0; i < number_dragons; i++) place_element(dragons.get(i));
		compute_game_state();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = "";
		res += maze_type + newline + maze_size + newline + dragon_type + newline + maze + newline 
				+ exit + newline + hero + newline + sword + newline + shield + newline
				+ number_darts + newline;
		for (int i = 0; i < number_darts; i++) {
			res += darts.get(i) + newline;
		}
		res += number_dragons + newline;
		for (int i = 0; i < number_dragons; i++) {
			res += dragons.get(i) + newline;
		}
		return res;
	}
}
