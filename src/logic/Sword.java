package logic;

/**
 * The Class Sword - contains specific methods to deal with the sword of the game.
 */
public class Sword extends Element{

	/**
	 * Instantiates a new sword.
	 */
	public Sword() {
		set_state('S');
		int[] default_position = {8, 1};
		set_position(default_position);
	}
	
	/**
	 * Kills the sword, when the hero finds it.
	 */
	public void kill_sword() {
		set_state(' ');
	}

}
