package logic;

/**
 * The Class Element - contains the state and position of a given game element, as well as methods that are common to all game elements.
 */
public class Element {

	/** The position of the element on the game board. */
	private int[] position;
	
	/** The state of the element. */
	private char state;
	
	/**
	 * Gets the position of the element.
	 *
	 * @return the position
	 */
	public int[] get_position() {
		return position;
	}
	
	/**
	 * Sets the position of the element.
	 *
	 * @param new_position the new position
	 */
	public void set_position(int[] new_position) {
		position = new_position;
	}
	
	/**
	 * Gets the state of the element.
	 *
	 * @return the state
	 */
	char get_state() {
		return state;
	}
	
	/**
	 * Sets the state of the element.
	 *
	 * @param new_state the new state
	 */
	public void set_state(char new_state) {
		state = new_state;
	}
	
	/**
	 * Gets the neighbour_positions of an element, that is the coordinates of the four cells adjacent to the element.
	 *
	 * @return the neighbour_positions
	 */
	int[][] get_neighbour_positions() {
		int[][] pos_neigh = {
				{position[0] - 1, position[1]}, 
				{position[0] + 1, position[1]},
				{position[0], position[1] - 1},
				{position[0], position[1] + 1}};
		return pos_neigh;
	}
	
	/**
	 * Gets the burn_positions, that is the coordinates of the 8 cells where the dragon's fire can reach the element. 
	 *
	 * @return the burn_positions
	 */
	int[][] get_burn_positions() {
		int[][] pos_burn = {
				{position[0] - 2, position[1]}, 
				{position[0] + 2, position[1]},
				{position[0], position[1] - 2},
				{position[0], position[1] + 2},
				{position[0] - 3, position[1]}, 
				{position[0] + 3, position[1]},
				{position[0], position[1] - 3},
				{position[0], position[1] + 3}};
		return pos_burn;
	}
	
	/**
	 * Determines if a given position is equal to the position of the element.
	 *
	 * @param other_position the other_position
	 * @return true, if the positions are the same
	 */
	boolean same_position (int[] other_position) {
		return position[0] == other_position[0] && position[1] == other_position[1];
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals (Object obj) {
		return obj != null
				&& obj instanceof Element
				&& getClass().getName() == ((Element)obj).getClass().getName()
				&& same_position(((Element)obj).get_position())
				&& ((Element)obj).get_state() == state;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = state + ";" + position[0] + ";" + position[1];
		return res;
	}
}
