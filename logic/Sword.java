package logic;

public class Sword extends Element{

	public Sword() {
		set_state('S');
		int[] default_position = {8, 1};
		set_position(default_position);
	}
	
	void kill_sword() {
		set_state(' ');
	}

}
