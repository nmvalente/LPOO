package logic;

public class Shield extends Element {
	
	public Shield() {
		set_state('V');
		int[] default_position = {1, 1};
		set_position(default_position);
	}
		
	void kill_shield() {
		set_state(' ');
	}
	
}
