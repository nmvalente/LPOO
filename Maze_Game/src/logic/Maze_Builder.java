package logic;

/**
 * The Class Maze_Builder is used to build the game board.
 */
class Maze_Builder {

	/** The size of the board. */
	private int size;
	
	/** The type of the board (0 - static, 1 - random). */
	private int type; 
	
	/**
	 * Sets the type of game board.
	 *
	 * @param tp the new board type
	 */
	void set_maze_type(int tp) {
		type = tp;
	}
	
	/**
	 * Sets the size of the board.
	 *
	 * @param sz the new board size
	 */
	void set_maze_size(int sz) {
		size = sz;
	}
	
	/**
	 * Gets the maze for the game.
	 *
	 * @param exit the exit of the maze
	 * @return the maze or game board
	 */
	Maze get_maze(Exit exit) {
		if (type == 0) return new Static_Maze();
		else return new Random_Maze(size, exit);
	}

	/**
	 * Gets the maze for the game.
	 *
	 * @param exit the exit of the maze
	 * @param stat_maze the static maze we want to use
	 * @return the maze
	 */
	Maze get_maze(Exit exit, char[][] stat_maze) {
		return new Static_Maze(stat_maze);
	}
}
