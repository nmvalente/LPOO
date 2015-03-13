package logic;

public class Move_Dragon {
	
	void move_dragon(Game g, Dragon d, int[] new_pos) {
		Maze m = g.get_maze();
		char board_symbol = m.get_board_position(new_pos);
		if (board_symbol == ' ') {
			d.unarm_dragon();
			d.set_position(new_pos);
		}
		else if (board_symbol == g.get_sword().get_state() 
				|| board_symbol == g.get_shield().get_state()
				|| board_symbol == 'i') {
			d.arm_dragon();
			d.set_position(new_pos);
		}
	}
	
	void move_dragon_right(Game g, Dragon d) {
		int[] current_pos = d.get_position();
		int[] new_pos = {current_pos[0], current_pos[1]};
		new_pos[1]++;
		move_dragon(g, d, new_pos);
	}
	
	void move_dragon_left(Game g, Dragon d) {
		int[] current_pos = d.get_position();
		int[] new_pos = {current_pos[0], current_pos[1]};
		new_pos[1]--;
		move_dragon(g, d, new_pos);
	}

	void move_dragon_up(Game g, Dragon d) {
		int[] current_pos = d.get_position();
		int[] new_pos = {current_pos[0], current_pos[1]};
		new_pos[0]--;
		move_dragon(g, d, new_pos);
	}

	void move_dragon_down(Game g, Dragon d) {
		int[] current_pos = d.get_position();
		int[] new_pos = {current_pos[0], current_pos[1]};
		new_pos[0]++;
		move_dragon(g, d, new_pos);
	}

}
