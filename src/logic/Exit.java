package logic;

/**
 * The Class Exit - contains specific methods to deal with the exit of the game board.
 */
public class Exit extends Element {

	/**
	 * Instantiates a new exit.
	 */
	public Exit() {
		set_state('E');
		int[] default_position = {5, 9};
		set_position(default_position);
	}
	
	/**
	 * Opens the exit.
	 */
	public void open_exit() {
		set_state('e');
	}

}
