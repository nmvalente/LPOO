package logic;

/**
 * The Class Dragon - contains specific methods to deal with the dragons in the game.
 */
public class Dragon extends Element{
	
	/**
	 * Instantiates a new dragon.
	 */
	public Dragon() {
		set_state('D');
		int[] default_position = {3, 1};
		set_position(default_position);
	}
	
	/**
	 * Kills the dragon.
	 */
	public void kill_dragon() {
		set_state(' ');
	}
	
	/**
	 * Arms the dragon, that is, places it on top of the Sword.
	 */
	public void arm_dragon() {
		set_state('F');
	}

	/**
	 * Shields the dragon, that is, places it on top of the Shield.
	 */
	public void shield_dragon() {
		set_state('G');
	}

	/**
	 * Darts the dragon, that is, places it on top of a Dart.
	 */
	public void dart_dragon() {
		set_state('K');
	}

	/**
	 * Unarms the dragon, that is, moves it away from the Sword, the Shield or a Dart.
	 */
	void unarm_dragon() {
		set_state('D');
	}
	
	/**
	 * Sets the dragon state to sleep mode.
	 */
	public void sleep_dragon() {
		if (get_state() == 'D' || get_state() == 'd') set_state('d');
		else if (get_state() == 'F' || get_state() == 'f') set_state('f');
		else if (get_state() == 'G' || get_state() == 'g') set_state('g');
		else if (get_state() == 'K' || get_state() == 'k') set_state('k');
	}

}
