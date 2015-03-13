package logic;

public class Dragon extends Element{
	
	public Dragon() {
		set_state('D');
		int[] default_position = {3, 1};
		set_position(default_position);
	}
	
	void kill_dragon() {
		set_state(' ');
	}
	
	void arm_dragon() {
		set_state('F');
	}

	void unarm_dragon() {
		set_state('D');
	}

}
