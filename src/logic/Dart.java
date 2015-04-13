package logic;

/**
 * The Class Dart - contains specific methods to deal with the darts in the game.
 */
public class Dart extends Element {
	
	/**
	 * Instantiates a new dart.
	 */
	public Dart() {
		set_state('i');
		int[] default_position = {1, 1};
		set_position(default_position);
	}
		
	/**
	 * Kills the dart, so it disappears from the board when found by the Hero.
	 */
	public void kill_dart() {
		set_state(' ');
	}
	
}
