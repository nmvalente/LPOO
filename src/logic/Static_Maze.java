package logic;

/**
 * The Class Static_Maze is used to generate a random maze for the game.
 */
class Static_Maze extends Maze {
	
	/**
	 * Instantiates a new static_maze, using the predefined board.
	 */
	public Static_Maze() {
		make_static_maze();
	}

	/**
	 * Instantiates a new static_maze, using a provided game board.
	 *
	 * @param stat_maze the static maze we which to use
	 */
	Static_Maze(char[][] stat_maze) {
		board = stat_maze;
	}

	/**
	 * Makes static predefined maze board.
	 */
	private void make_static_maze() {
		board = new char[][]{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}};
	}
		
}
