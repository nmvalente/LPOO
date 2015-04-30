package logic;

/**
 * The Class Shield - contains specific methods to deal with the shield of the game.
 */
public class Shield extends Element {
	
	/**
	 * Instantiates a new shield.
	 */
	public Shield() {
		set_state('V');
		int[] default_position = {1, 1};
		set_position(default_position);
	}
		
	/**
	 * Kills the shield, when the hero finds it.
	 */
	void kill_shield() {
		set_state(' ');
	}
	
}
