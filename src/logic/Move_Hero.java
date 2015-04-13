package logic;

/**
 * The Class Move_Hero is used to move the hero in the game board.
 */
public class Move_Hero {
	
	/**
	 * Moves the hero.
	 *
	 * @param g the game
	 * @param new_pos the new position where we want to place the hero
	 */
	private void move_hero(Game g, int[] new_pos) {
		Maze m = g.get_maze();
		Hero h = g.get_hero();
		char board_symbol = m.get_board_position(new_pos);
		if (board_symbol == ' ') {
			h.set_position(new_pos);
		}
		else if (board_symbol == g.get_sword().get_state()) {
			h.arm_hero();
			h.set_position(new_pos);			
			g.get_sword().kill_sword();
		}
		else if (board_symbol == g.get_shield().get_state()) {
			h.shield_hero();
			h.set_position(new_pos);
			g.get_shield().kill_shield();
		}
		else if (board_symbol == 'i') {
			h.dart_hero();
			h.set_position(new_pos);
			for (int i = 0; i < g.get_number_darts(); i++) {
				Dart d = g.get_darts().get(i);
				if (d.same_position(new_pos)) {
					d.kill_dart();
					g.get_darts().remove(i);
					g.dec_number_darts();
					break;
				}
			}
		}
		else if (board_symbol == 'e' && g.get_exit().get_state() == 'e') {
			h.set_position(new_pos);
		}
	}
	
	/**
	 * Moves the hero to the right.
	 *
	 * @param g the game
	 */
	public void move_hero_right(Game g) {
		int[] current_pos = g.get_hero().get_position();
		int[] new_pos = {current_pos[0], current_pos[1] + 1};
		move_hero(g, new_pos);
	}
	
	/**
	 * Moves the hero to the left.
	 *
	 * @param g the game
	 */
	public void move_hero_left(Game g) {
		int[] current_pos = g.get_hero().get_position();
		int[] new_pos = {current_pos[0], current_pos[1] - 1};
		move_hero(g, new_pos);
	}

	/**
	 * Moves the hero up.
	 *
	 * @param g the game
	 */
	public void move_hero_up(Game g) {
		int[] current_pos = g.get_hero().get_position();
		int[] new_pos = {current_pos[0] - 1, current_pos[1]};
		move_hero(g, new_pos);
	}

	/**
	 * Moves the hero down.
	 *
	 * @param g the game
	 */
	public void move_hero_down(Game g) {
		int[] current_pos = g.get_hero().get_position();
		int[] new_pos = {current_pos[0] + 1, current_pos[1]};
		move_hero(g, new_pos);
	}

}
