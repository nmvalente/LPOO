package logic;

import java.util.Random;

/**
 * The Class Move_Dragon is used to move the dragons in the game board.
 */
class Move_Dragon {

	/** A random number generator */
	private Random r;
	
	/**
	 * Instantiates a new move_dragon.
	 */
	public Move_Dragon(){
		r = new Random();
	}

	/**
	 * Gets the move, that is, the random number that indicates the dragon's next move.
	 *
	 * @return the move
	 */
	public int getMove(){
		return r.nextInt(4);
	}

	/**
	 * Moves the dragon.
	 *
	 * @param g the game
	 * @param d the dragon
	 * @param new_pos the new position where we intend to place the dragon
	 */
	private void move_dragon(Game g, Dragon d, int[] new_pos) {
		Maze m = g.get_maze();
		char board_symbol = m.get_board_position(new_pos);
		if (board_symbol == ' ') {
			d.unarm_dragon();
			d.set_position(new_pos);
		}
		else if (board_symbol == g.get_sword().get_state()) {
			d.arm_dragon();
			d.set_position(new_pos);
		}
		else if (board_symbol == g.get_shield().get_state()) {
			d.shield_dragon();
			d.set_position(new_pos);
		}
		else if (board_symbol == 'i') {
			d.dart_dragon();
			d.set_position(new_pos);
		}
	}

	/**
	 * Moves the dragon to the right.
	 *
	 * @param g the game
	 * @param d the dragon
	 */
	void move_dragon_right(Game g, Dragon d) {
		int[] current_pos = d.get_position();
		int[] new_pos = {current_pos[0], current_pos[1]};
		new_pos[1]++;
		move_dragon(g, d, new_pos);
	}

	/**
	 * Moves the dragon to the left.
	 *
	 * @param g the game
	 * @param d the dragon
	 */
	void move_dragon_left(Game g, Dragon d) {
		int[] current_pos = d.get_position();
		int[] new_pos = {current_pos[0], current_pos[1]};
		new_pos[1]--;
		move_dragon(g, d, new_pos);
	}

	/**
	 * Moves the dragon up.
	 *
	 * @param g the game
	 * @param d the dragon
	 */
	void move_dragon_up(Game g, Dragon d) {
		int[] current_pos = d.get_position();
		int[] new_pos = {current_pos[0], current_pos[1]};
		new_pos[0]--;
		move_dragon(g, d, new_pos);
	}

	/**
	 * Moves the dragon down.
	 *
	 * @param g the game
	 * @param d the dragon
	 */
	void move_dragon_down(Game g, Dragon d) {
		int[] current_pos = d.get_position();
		int[] new_pos = {current_pos[0], current_pos[1]};
		new_pos[0]++;
		move_dragon(g, d, new_pos);
	}

}
