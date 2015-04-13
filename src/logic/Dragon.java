package logic;

public class Dragon extends Element{
	
	public Dragon() {
		set_state('D');
		int[] default_position = {3, 1};
		set_position(default_position);
	}
	
	public void kill_dragon() {
		set_state(' ');
	}
	
	public void arm_dragon() {
		set_state('F');
	}

	public void shield_dragon() {
		set_state('G');
	}

	public void dart_dragon() {
		set_state('K');
	}

	void unarm_dragon() {
		set_state('D');
	}
	
	public void sleep_dragon() {
		if (get_state() == 'D' || get_state() == 'd') set_state('d');
		else if (get_state() == 'F' || get_state() == 'f') set_state('f');
		else if (get_state() == 'G' || get_state() == 'g') set_state('g');
		else if (get_state() == 'K' || get_state() == 'k') set_state('k');
	}

}
