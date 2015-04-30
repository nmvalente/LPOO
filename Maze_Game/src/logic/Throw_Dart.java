package logic;

/**
 * The Class Throw_Dart is used when the hero throws a dart.
 */
class Throw_Dart {
		
	/**
	 * Determines if there is a dragon in a given position which can be hit by the hero's dart. If a dragon exists it is killed by the dart.
	 *
	 * @param g the game
	 * @param h the hero
	 * @param new_pos the position we want to check
	 * @return true, if a dragon is found
	 */
	private boolean find_dragon(Game g, Hero h, int[] new_pos) {
		for (int i = 0; i < g.get_number_dragons(); i ++) {
			Dragon d = g.get_dragons().get(i);
			if (d.same_position(new_pos) && g.free_path(h, d)) {
				d.kill_dragon();
				g.place_element(d);
				g.get_dragons().remove(i);
				g.dec_number_dragons();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Throws a dart to the right.
	 *
	 * @param g the game
	 */
	void throw_dart_right(Game g) {
		Hero h = g.get_hero();
		h.undart_hero();
		int[] hero_pos = h.get_position();
		for (int i = hero_pos[1] + 1; i < g.get_maze_size(); i++) {
			int[] new_pos = {hero_pos[0], i};
			if (find_dragon(g, h, new_pos)) {
				return;
			}
		}
	}
	
	/**
	 * Throws a dart to the left.
	 *
	 * @param g the game
	 */
	void throw_dart_left(Game g) {
		Hero h = g.get_hero();
		h.undart_hero();
		int[] hero_pos = h.get_position();
		for (int i = hero_pos[1] - 1; i >= 0; i--) {
			int[] new_pos = {hero_pos[0], i};
			if (find_dragon(g, h, new_pos)) {
				return;
			}
		}
	}

	/**
	 * Throws a dart up.
	 *
	 * @param g the game
	 */
	void throw_dart_up(Game g) {
		Hero h = g.get_hero();
		h.undart_hero();
		int[] hero_pos = h.get_position();
		for (int i = hero_pos[0] - 1; i >= 0; i--) {
			int[] new_pos = {i, hero_pos[1]};
			if (find_dragon(g, h, new_pos)) {
				return;
			}
		}
	}

	/**
	 * Throws a dart down.
	 *
	 * @param g the game
	 */
	void throw_dart_down(Game g) {
		Hero h = g.get_hero();
		h.undart_hero();
		int[] hero_pos = h.get_position();
		for (int i = hero_pos[0] + 1; i < g.get_maze_size(); i++) {
			int[] new_pos = {i, hero_pos[1]};
			if (find_dragon(g, h, new_pos)) {
				return;
			}
		}
	}

}
