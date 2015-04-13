package logic;

import java.util.ArrayList;

/**
 * The Class Maze - constains the game board.
 */
public abstract class Maze {

	/** A char matrix that represents the board of the game. */
	char[][] board;

	/**
	 * Gets all the empty cells of the board (where new elements can be place.
	 *
	 * @return Array list with the positions of all empty board cells
	 */
	ArrayList<int[]> get_board_cells() {
		ArrayList<int[]> cells = new ArrayList<int[]>();
		int board_size = board.length;
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (board[i][j] == ' ') {
					int[] cell = {i, j};
					cells.add(cell);
				}
			}
		}
		return cells;
	}

	/**
	 * Places a given element on the board.
	 *
	 * @param elem the element to be placed.
	 */
	void set_position(Element elem) {
		int[] position = elem.get_position();
		char state = elem.get_state();
		board[position[0]][position[1]] = state;
	}

	/**
	 * Removes a given element from the board.
	 *
	 * @param elem the element to be placed.
	 */
	void remove_position(Element elem) {
		int[] position = elem.get_position();
		board[position[0]][position[1]] = ' ';
	}

	/**
	 * Gets the character of a certain board position.
	 *
	 * @param position the position we which to obtain
	 * @return the character in that position
	 */
	public char get_board_position(int[] position) {
		return board[position[0]][position[1]];
	}

	/**
	 * Gets the game board.
	 *
	 * @return the game board
	 */
	public char[][] get_board() {
		return board;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = "";
		for (int i = 0; i < board.length; i++ ) {
			for (int j = 0; j < board.length; j++) {
				res += board[i][j] +";";
			}
		}
		return res;
	}
}
