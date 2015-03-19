package logic;

public class Exit extends Element {

	public Exit() {
		set_state('E');
		int[] default_position = {5, 9};
		set_position(default_position);
	}
	
	public void open_exit() {
		set_state('e');
	}

}
