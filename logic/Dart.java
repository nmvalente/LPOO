package logic;

public class Dart extends Element {
	
	public Dart() {
		set_state('i');
		int[] default_position = {1, 1};
		set_position(default_position);
	}
		
	void kill_dart() {
		set_state(' ');
	}
	
}
