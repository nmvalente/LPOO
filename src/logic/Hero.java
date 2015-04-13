package logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Hero - contains specific methods to deal with the Hero of the game.
 */
public class Hero extends Element{
	
	/** The hero's darts - number of darts the hero has. */
	private int hero_darts = 0;
	
	/**
	 * Instantiates a new hero.
	 */
	public Hero() {
		set_state('h');
		int[] default_position = {1, 1};
		set_position(default_position);
	}
		
	/**
	 * Kills the hero.
	 */
	public void kill_hero() {
		set_state(' ');
	}
	
	/**
	 * Arms the hero.
	 */
	void arm_hero() {
		if (get_state() == 'h') set_state('a');
		else set_state('A');
	}
	
	/**
	 * Shields the hero.
	 */
	void shield_hero() {
		if (get_state() == 'h') set_state('H');
		else set_state('A'); 
	}
	
	/**
	 * Sets the number of darts of the hero.
	 *
	 * @param h_darts the new number of darts of the hero
	 */
	void set_hero_darts(int h_darts) {
		hero_darts = h_darts;
	}

	/**
	 * Increases the number of darts of the hero.
	 */
	public void dart_hero() {
		hero_darts++;
	}
	
	/**
	 * Decreases the number of darts of the hero.
	 */
	public void undart_hero() {
		hero_darts--;
	}
	
	/**
	 * Gets the number of darts of the hero.
	 *
	 * @return the number of darts of the hero
	 */
	public int get_hero_darts() {
		return hero_darts;
	}
	
	/* (non-Javadoc)
	 * @see logic.Element#toString()
	 */
	public String toString() {
		String res = get_state() + ";" + get_position()[0] + ";" + get_position()[1] + ";" + hero_darts;
		return res;
	}
	
}
