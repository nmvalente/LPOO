package logic;

import java.util.Random;

/**
 * The Class Random_Exit is used to randomly generate a board exit for the game.
 */
class Random_Exit {
	
	/**
	 * Instantiates a new random_exit.
	 *
	 * @param g the game
	 * @param exit the previously instantiated exit we whose position we will randomize
	 */
	Random_Exit(Game g, Exit exit) {
		Random rn = new Random();
		int exit_wall = rn.nextInt(4);
		int size = g.get_maze_size();
		int exit_cell = rn.nextInt(size - 2) + 1;
		switch (exit_wall) {
		case 0 : // cima
			exit.set_position(new int[] {0, exit_cell});
			break;
		case 1 : // baixo
			exit.set_position(new int[] {size - 1, exit_cell});
			break;
		case 2 : // direita
			exit.set_position(new int[] {exit_cell, size - 1});
			break;
		case 3 : // esquerda
			exit.set_position(new int[] {exit_cell, 0});
			break;
		}
	}

}
