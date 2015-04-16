package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;




//import cli.*;
import logic.*;

public class Maze_Test {
	
	private static String newline = System.getProperty("line.separator");

	@Test
	public void test_static() {
		Game game = new logic.Game();
		game.set_maze_type(0);
		game.set_maze_size(10);
		game.set_number_dragons(1);
		game.set_dragon_type(0);
		game.set_number_darts(0);
		game.start_game();
		assertEquals(game.get_maze_type(), 0);
		assertEquals(game.get_maze_size(), 10);
		assertEquals(game.get_number_dragons(), 1);
		assertEquals(game.get_dragon_type(), 0);
		assertEquals(game.get_number_darts(), 0);
		int[] dragon_position = {3, 1};
		int[] hero_position = {1, 1};
		int[] sword_position = {8, 1};
		int[] exit_position = {5, 9};
		assertEquals(game.get_maze().get_board_position(dragon_position), 'D');
		assertEquals(game.get_maze().get_board_position(hero_position), 'H');
		assertEquals(game.get_maze().get_board_position(sword_position), 'S');
		assertEquals(game.get_maze().get_board_position(exit_position), 'E');
		Dragon d = new Dragon();
		d.set_position(dragon_position);
		Hero h = new Hero();
		h.set_position(hero_position);
		h.set_state('H');
		Sword s = new Sword();
		s.set_position(sword_position);
		Exit e = new Exit();
		e.set_position(exit_position);
		assertEquals(game.get_dragons().size(), 1);
		assertEquals(game.get_dragons().get(0), d);
		assertEquals(game.get_hero(), h);
		assertEquals(game.get_sword(), s);
		assertEquals(game.get_exit(), e);
	}

	@Test
	public void test_static_hero_movement() {
		Game game = new logic.Game();
		game.set_maze_type(0);
		game.set_maze_size(10);
		game.set_number_dragons(1);
		game.set_dragon_type(0);
		game.set_number_darts(0);
		game.start_game();
		int[] hero_position = {1, 1};
		Hero h = new Hero();
		h.set_position(hero_position);
		h.set_state('H');
		Move_Hero mh = new Move_Hero();
		int[] hero_pos_right = {1, 4};
		mh.move_hero_right(game);
		mh.move_hero_right(game);
		mh.move_hero_right(game);
		h.set_position(hero_pos_right);
		assertEquals(game.get_hero(), h);
		int[] hero_pos_left = {1, 3};
		mh.move_hero_left(game);
		h.set_position(hero_pos_left);
		assertEquals(game.get_hero(), h);
		mh.move_hero_right(game);
		int[] hero_pos_down = {2, 4};
		h.set_position(hero_pos_down);
		mh.move_hero_down(game);
		assertEquals(game.get_hero(), h);
		int[] hero_pos_up = {1, 4};
		h.set_position(hero_pos_up);
		mh.move_hero_up(game);
		assertEquals(game.get_hero(), h);
		mh.move_hero_up(game);
		assertEquals(game.get_hero(), h);
	}

	@Test
	public void test_static_hero_dies() {
		Game game = new logic.Game();
		game.set_maze_type(0);
		game.set_maze_size(10);
		game.set_number_dragons(1);
		game.set_dragon_type(0);
		game.set_number_darts(0);
		game.start_game();
		int[] hero_position = {1, 1};
		Hero h = new Hero();
		h.set_position(hero_position);
		h.set_state('H');
		Move_Hero mh = new Move_Hero();
		mh.move_hero_down(game);
		ArrayList<Integer> dragons_c = game.dragons_close();
		game.fight(dragons_c);
		int[] hero_pos_down = {2, 1};
		h.set_position(hero_pos_down);
		h.kill_hero();
		game.compute_game_state();
		assertEquals(game.get_hero(), h);
		assertEquals(game.get_game_state(), 2);
	}

	@Test
	public void test_static_dragon_dies() {
		Game game = new logic.Game();
		game.set_maze_type(0);
		game.set_maze_size(10);
		game.set_number_dragons(1);
		game.set_dragon_type(0);
		game.set_number_darts(0);
		game.start_game();
		int[] hero_position = {8, 1};
		Hero h = new Hero();
		h.set_position(hero_position);
		h.set_state('A');
		int[] sword_position = {8, 1};
		Sword s = new Sword();
		s.set_position(sword_position);
		s.kill_sword();
		int[] dragon_position = {3, 1};
		Dragon d = new Dragon();
		d.set_position(dragon_position);
		d.kill_dragon();
		int[] exit_position = {5, 9};
		Exit e = new Exit();
		e.set_position(exit_position);
		e.open_exit();
		Move_Hero mh = new Move_Hero();
		game.choose_hero_movement(0);
		game.choose_hero_movement(0);
		game.choose_hero_movement(0);
		assertEquals(game.free_path(game.get_hero(), game.get_dragons().get(0)), false);
		game.hero_turn(3);
		game.choose_hero_movement(3);
		game.choose_hero_movement(3);
		game.choose_hero_movement(3);
		game.choose_hero_movement(4);
		game.choose_hero_movement(1);
		game.choose_hero_movement(1);
		game.choose_hero_movement(1);
		mh.move_hero_down(game);
		mh.move_hero_down(game);
		mh.move_hero_down(game);
		assertEquals(game.get_hero(), h);
		assertEquals(game.get_sword(), s);
		assertEquals(game.free_path(game.get_hero(), game.get_dragons().get(0)), true);
		game.choose_hero_movement(2);
		game.choose_hero_movement(2);
		game.choose_hero_movement(2);
		game.choose_hero_movement(2);
		ArrayList<Integer> dragons_c = game.dragons_close();
		game.fight(dragons_c);
		assertEquals(game.get_exit(), e);
		assertEquals(game.get_dragons().size(), 0);
		mh.move_hero_down(game);
		mh.move_hero_right(game);
		mh.move_hero_right(game);
		mh.move_hero_right(game);
		mh.move_hero_right(game);
		mh.move_hero_right(game);
		mh.move_hero_down(game);
		mh.move_hero_down(game);
		mh.move_hero_down(game);
		mh.move_hero_right(game);
		mh.move_hero_right(game);
		mh.move_hero_up(game);
		mh.move_hero_up(game);
		mh.move_hero_up(game);
		assertEquals(game.free_path(game.get_hero(), game.get_exit()), true);
		mh.move_hero_right(game);
		game.compute_game_state();
		assertEquals(game.get_game_state(), 1);
	}
	
	@Test
	public void testDart() {
		char [][] m1 = {
				{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', ' ', 'X', ' ', 'S'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Game game = new logic.Game();
		game.set_maze_type(0);
		game.set_maze_size(5);
		game.set_number_dragons(1);
		game.set_dragon_type(1);
		game.set_number_darts(1);
		int[] hero_position = {1, 1};
		Hero h = new Hero();
		h.set_position(hero_position);
		h.set_state('H');
		int[] dragon_position = {3, 3};
		Dragon d = new Dragon();
		d.set_position(dragon_position);
		d.set_state('D');
		int[] sword_position = {1, 3};
		int[] shield_position = {1, 3};
		int[] dart_position = {1, 2};
		int[] exit_position = {2, 4};
		Dart i = new Dart();
		i.set_position(dart_position);
		Sword s = new Sword();
		s.set_state(' ');
		s.set_position(sword_position);
		Shield v = new Shield();
		v.set_state(' ');
		v.set_position(shield_position);
		game.start_test_game(m1, hero_position, dragon_position, sword_position, shield_position, dart_position, exit_position);
		assertEquals(game.get_maze_type(), 0);
		assertEquals(game.get_maze_size(), 5);
		assertEquals(game.get_number_dragons(), 1);
		assertEquals(game.get_dragon_type(), 1);
		assertEquals(game.get_number_darts(), 1);
		assertEquals(game.get_shield(), v);
		assertEquals(game.get_sword(), s);
		assertEquals(game.get_maze().get_board_position(dragon_position), 'D');
		assertEquals(game.get_maze().get_board_position(hero_position), 'H');
		assertEquals(game.get_maze().get_board_position(sword_position), ' ');
		assertEquals(game.get_maze().get_board_position(shield_position), ' ');
		assertEquals(game.get_maze().get_board_position(exit_position), 'E');
		assertEquals(game.get_maze().get_board_position(dart_position), 'i');
		Move_Hero mh = new Move_Hero();
		h.dart_hero();
		h.dart_hero();
		h.dart_hero();
		game.get_hero().dart_hero();
		game.get_hero().dart_hero();
		game.get_hero().dart_hero();
		assertEquals(game.get_hero(), h);
		assertEquals(game.get_hero().get_hero_darts(), 3);		
		h.undart_hero();
		h.undart_hero();
		h.undart_hero();
		game.hero_dart(4);
		game.hero_dart(5);
		game.hero_dart(6);
		assertEquals(game.get_hero(), h);
		assertEquals(game.get_hero().get_hero_darts(), 0);		
		assertEquals(game.get_darts().get(0), i);
		mh.move_hero_right(game);
		h.set_position(dart_position);
		h.dart_hero();
		i.kill_dart();
		assertEquals(game.get_hero(), h);
		assertEquals(game.get_hero().get_hero_darts(), 1);		
		assertEquals(game.get_darts().size(), 0);
		int[] new_position = {1, 3};
		h.set_position(new_position);
		mh.move_hero_right(game);
		game.hero_dart(7); // 3 - down
		h.undart_hero();
		d.kill_dragon();
		assertEquals(game.get_hero(), h);	
		assertEquals(game.get_dragons().size(), 0);
	}

	@Test
	public void testBurn() {
		char [][] m1 = {
				{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', ' ', 'X', ' ', 'S'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Game game = new logic.Game();
		game.set_maze_type(0);
		game.set_maze_size(5);
		game.set_number_dragons(1);
		game.set_dragon_type(1);
		game.set_number_darts(1);
		int[] hero_position = {1, 1};
		Hero h = new Hero();
		h.set_position(hero_position);
		h.set_state('h');
		int[] dragon_position = {3, 3};
		Dragon d = new Dragon();
		d.set_position(dragon_position);
		d.set_state('D');
		int[] sword_position = {1, 3};
		int[] shield_position = {1, 3};
		int[] dart_position = {1, 2};
		int[] exit_position = {2, 4};
		Dart i = new Dart();
		i.set_position(dart_position);
		game.start_test_game(m1, hero_position, dragon_position, sword_position, shield_position, dart_position, exit_position);
		game.get_hero().set_state('h');
		game.place_element(game.get_hero());
		assertEquals(game.get_maze().get_board_position(hero_position), 'h');
		Move_Hero mh = new Move_Hero();
		assertEquals(game.get_hero(), h);
		mh.move_hero_down(game);
		int[] new_position = {2, 1};
		h.set_position(new_position);
		assertEquals(game.get_hero(), h);
		int[] final_position = {3, 1};
		h.set_position(final_position);
		mh.move_hero_down(game);
		assertEquals(game.get_hero(), h);
		h.kill_hero();
		game.burn_hero();
		assertEquals(game.get_hero(), h);
		assertEquals(game.get_game_state(), 2);
	}

	@Test
	public void testSleep() {
		char [][] m1 = {
				{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', ' ', 'X', ' ', 'S'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Game game = new logic.Game();
		game.set_maze_type(0);
		game.set_maze_size(5);
		game.set_number_dragons(1);
		game.set_dragon_type(1);
		game.set_number_darts(1);
		int[] hero_position = {1, 1};
		Hero h = new Hero();
		h.set_position(hero_position);
		h.set_state('H');
		int[] dragon_position = {3, 3};
		Dragon d = new Dragon();
		d.set_position(dragon_position);
		d.set_state('D');
		int[] sword_position = {1, 3};
		int[] shield_position = {1, 3};
		int[] dart_position = {1, 2};
		int[] exit_position = {2, 4};
		Dart i = new Dart();
		i.set_position(dart_position);
		game.start_test_game(m1, hero_position, dragon_position, sword_position, shield_position, dart_position, exit_position);
		game.get_dragons().get(0).sleep_dragon();
		d.set_state('d');
		assertEquals(game.get_dragons().get(0), d);
		game.get_dragons().get(0).arm_dragon();
		d.set_state('F');
		assertEquals(game.get_dragons().get(0), d);
		game.get_dragons().get(0).sleep_dragon();
		d.set_state('f');
		assertEquals(game.get_dragons().get(0), d);
	}
	
	@Test
	public void testLoadPrint() throws IOException {
		Game game = new logic.Game();
		game.load_game_file("save_test.txt");
		int[] hero_position = {1, 1};
		Hero h = new Hero();
		h.set_position(hero_position);
		h.set_state('H');
		int[] dragon_position = {3, 3};
		Dragon d = new Dragon();
		d.set_position(dragon_position);
		d.set_state('D');
		int[] sword_position = {1, 3};
		int[] shield_position = {1, 3};
		int[] dart_position = {1, 2};
		int[] exit_position = {2, 4};
		Dart i = new Dart();
		i.set_position(dart_position);
		Sword s = new Sword();
		s.set_state(' ');
		s.set_position(sword_position);
		Shield v = new Shield();
		v.set_state(' ');
		v.set_position(shield_position);
		Exit e = new Exit();
		e.set_state('E');
		e.set_position(exit_position);
		assertEquals(game.get_maze_type(), 0);
		assertEquals(game.get_maze_size(), 5);
		assertEquals(game.get_number_dragons(), 1);
		assertEquals(game.get_dragon_type(), 1);
		assertEquals(game.get_number_darts(), 1);
		assertEquals(game.get_shield(), v);
		assertEquals(game.get_sword(), s);
		assertEquals(game.get_exit(), e);
		assertEquals(game.get_maze().get_board_position(dragon_position), 'D');
		assertEquals(game.get_maze().get_board_position(hero_position), 'H');
		assertEquals(game.get_maze().get_board_position(sword_position), ' ');
		assertEquals(game.get_maze().get_board_position(shield_position), ' ');
		assertEquals(game.get_maze().get_board_position(exit_position), 'E');
		assertEquals(game.get_maze().get_board_position(dart_position), 'i');
		assertEquals(game.toString(), "0" + newline + "5" + newline + "1" + newline 
			+ "X;X;X;X;X;X;H;i; ;X;X; ;X; ;E;X; ; ;D;X;X;X;X;X;X;" + newline 
			+ "E;2;4" + newline + "H;1;1;0"  + newline + " ;1;3" + newline 
			+ " ;1;3" + newline + "1"  + newline + "i;1;2" + newline + "1" + newline + "D;3;3" + newline);

	}

	// a) the maze boundaries must have exactly one exit and everything else walls
	// b) the exist cannot be a corner
	private boolean checkBoundaries(Game g) {
		int countExit = 0;
		int n = g.get_maze_size();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
					int[] pos = {i, j};
					if (g.get_maze().get_board_position(pos) == 'E')
						if ((i == 0 || i == n-1) && (j == 0 || j == n-1))
							return false;
						else
							countExit++;
					else if (g.get_maze().get_board_position(pos) != 'X')
						return false;

				}
		return countExit == 1;
	}


	// d) there cannot exist 2x2 (or greater) squares with blanks only 
	// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal and walls in the other
	// d) there cannot exist 3x3 (or greater) squares with walls only
	private boolean hasSquare(Maze maze, char[][] square) {
		char [][] m = maze.get_board();
		for (int i = 0; i < m.length - square.length; i++)
			for (int j = 0; j < m.length - square.length; j++) {
				boolean match = true;
				for (int x = 0; x < square.length; x++)
					for (int y = 0; y < square.length; y++) {
						if (m[i+x][j+y] != square[x][y])
							match = false;
					}
				if (match)
					return true;
			}		
		return false; 
	}

	// c) there must exist a path between any blank cell and the maze exit 
	private boolean checkExitReachable(Game game) {
		int[] p = game.get_exit().get_position();
		char [][] m = deepClone(game.get_maze().get_board());
		visit(m, p[0], p[1]);

		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m.length; j++)
				if (m[i][j] != 'X' && m[i][j] != 'v')
					return false;

		return true; 
	}

	// auxiliary method used by checkExitReachable
	// marks a cell as visited (V) and proceeds recursively to its neighbors
	private void visit(char[][] m, int i, int j) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == 'X' || m[i][j] == 'v')
			return;
		m[i][j] = 'v';
		visit(m, i-1, j);
		visit(m, i+1, j);
		visit(m, i, j-1);
		visit(m, i, j+1);
	}

	// Auxiliary method used by checkExitReachable.
	// Gets a deep clone of a char matrix.
	private char[][] deepClone(char[][] m) {
		char[][] c = m.clone();
		for (int i = 0; i < m.length; i++)
			c[i] = m[i].clone();
		return c;
	}

	// Checks if all the arguments (in the variable arguments list) are not null and distinct
	private <T> boolean notNullAndDistinct(@SuppressWarnings("unchecked") T ... args) {
		for (int i = 0; i < args.length - 1; i++)
			for (int j = i + 1; j < args.length ; j++)
				if (args[i] == null || args[j] == null || args[i].equals(args[j]))
					return false;
		return true;
	}

	@Test
	public void testRandomMazeGenerator() throws Exception {
		int numMazes = 100;
		int maxSize = 55; // can change to any odd number >= 7

		//		Maze_Builder builder = new Maze_Builder();
		char[][] badWalls = {
				{'X', 'X', 'X'},
				{'X', 'X', 'X'},
				{'X', 'X', 'X'}};
		char[][] badSpaces = {
				{' ', ' '},
				{' ', ' '}};
		char[][] badDiag1 = {
				{'X', ' '},
				{' ', 'X'}};
		char[][] badDiag2 = {
				{' ', 'X'},
				{'X', ' '}};
		Random rand = new Random(); 
		for (int i = 0; i < numMazes; i++) {
			int size = maxSize == 7? 7 : 7 + 2 * rand.nextInt((maxSize - 7)/2);
			Game game = new logic.Game();
			game.set_maze_type(1);
			game.set_maze_size(size);
			game.set_number_dragons(2);
			game.set_dragon_type(2);
			game.set_number_darts(2);
			game.start_game();
			assertEquals(game.get_maze_type(), 1);
			assertEquals(game.get_maze_size(), size);
			assertEquals(game.get_number_dragons(), 2);
			assertEquals(game.get_dragon_type(), 2);
			assertEquals(game.get_number_darts(), 2);
			Maze m = game.get_maze();
			assertEquals(game.get_dragon_type(), 2);
			assertTrue("Invalid maze boundaries in maze:\n" + m, checkBoundaries(game));			
			assertTrue("Maze exit not reachable in maze:\n" + m, checkExitReachable(game));			
			assertNotNull("Invalid walls in maze:\n" + m, ! hasSquare(m, badWalls));
			assertNotNull("Invalid spaces in maze:\n" + m, ! hasSquare(m, badSpaces));
			assertNotNull("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiag1));
			assertNotNull("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiag2));
			assertTrue("Missing or overlapping objects in maze:\n" + m, 
					notNullAndDistinct(game.get_exit().get_position(), game.get_hero().get_position(),
							game.get_dragons().get(0).get_position(), game.get_dragons().get(game.get_number_dragons() - 1).get_position(), 
							game.get_sword().get_position(), game.get_darts().get(0).get_position(), game.get_darts().get(game.get_number_darts() - 1).get_position()));			
		}	
	}

	/**
	 * Repetidamente gera uma instancia do tipo T usando a funcao geradora, e verifica se a instancia
	 * gerada obedece a um dos predicados (funcoes de T em Boolean). No caso de nao obedecer a nenhum, 
	 * falha, mostrando a mensagem gerada pela funcao errorMessage (de T em String).
	 * Repete ate cada teste ter sucedido pelo menos umae vez, num minimo de numIter iteracoes.
	 * @param generator - gera uma instancia (funcao de () em T);
	 * @param errorMessage - gera uma mensagem em caso de erro (funcao de T em String);
	 * @param predicates - lista de predicados de teste (funcoes de T em Boolean).
	 **/

	@SafeVarargs
	public final <T> void testAlt(int minIter, Supplier<T> generator, Function<T, String> errorMessage, Predicate<T> ... predicates) {
		boolean [] tested = new boolean[predicates.length];
		int checked = 0;
		for (int iter = 0; iter < minIter && checked < predicates.length; iter++ ) {
			T x = generator.get();
			boolean found = false;
			for (int i = 0; i < predicates.length; i++)
				if (predicates[i].test(x)) {
					found = true;
					if (!tested[i]) {
						checked++;
						tested[i] = true;
					}
				}
			if (! found)		
				fail(errorMessage.apply(x));
			iter++;
		}
	}

	@Test(timeout=1000)
	public void testRandomDragon() {
		char [][] m1 = {
				{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', ' ', 'X', ' ', 'S'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};

		testAlt(1000,
				() -> {
					Game game = new logic.Game();
					game.set_maze_type(0);
					game.set_maze_size(5);
					game.set_number_dragons(1);
					game.set_dragon_type(1);
					game.set_number_darts(1);
					int[] hero_position = {1, 1};
					Hero h = new Hero();
					h.set_position(hero_position);
					h.set_state('H');
					int[] dragon_position = {3, 3};
					Dragon d = new Dragon();
					d.set_position(dragon_position);
					d.set_state('D');
					int[] sword_position = {1, 3};
					int[] shield_position = {1, 3};
					int[] dart_position = {1, 2};
					int[] exit_position = {2, 4};
					game.start_test_game(m1, hero_position, dragon_position, sword_position, shield_position, dart_position, exit_position);
					game.dragon_turn();
					return game;},
				(g) -> "Dragao em posicao invalida: (" 
					+ g.get_dragons().get(0).get_position()[0]
					+ ", "
					+ g.get_dragons().get(0).get_position()[1]
					+ ")", 
				(g) -> {
					int[] dragon_position = {3, 3};
					Dragon d1 = new Dragon();
					d1.set_position(dragon_position);
					return g.get_dragons().get(0).equals(d1);},
				(g) -> {
					int[] dragon_position = {3, 2};
					Dragon d = new Dragon();
					d.set_position(dragon_position);
					return g.get_dragons().get(0).equals(d);},
				(g) -> {
					int[] dragon_position = {2, 3};
					Dragon d = new Dragon();
					d.set_position(dragon_position);
					return g.get_dragons().get(0).equals(d);}); 
	}
}