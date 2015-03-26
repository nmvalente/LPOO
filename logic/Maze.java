package logic;

import java.util.ArrayList;

public abstract class Maze {

	char[][] board;

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

	void set_position(Element elem) {
		int[] position = elem.get_position();
		char state = elem.get_state();
		board[position[0]][position[1]] = state;
	}

	void remove_position(Element elem) {
		int[] position = elem.get_position();
		board[position[0]][position[1]] = ' ';
	}

	public char get_board_position(int[] position) {
		return board[position[0]][position[1]];
	}

	public char[][] get_board() {
		return board;
	}
	public void setBoard(char[][] b){board = b;}
}
