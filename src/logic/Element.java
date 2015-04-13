package logic;

public class Element {

	private int[] position;
	private char state;
	
	public int[] get_position() {
		return position;
	}
	
	public void set_position(int[] new_position) {
		position = new_position;
	}
	
	char get_state() {
		return state;
	}
	
	public void set_state(char new_state) {
		state = new_state;
	}
	
	int[][] get_neighbour_positions() {
		int[][] pos_neigh = {
				{position[0] - 1, position[1]}, 
				{position[0] + 1, position[1]},
				{position[0], position[1] - 1},
				{position[0], position[1] + 1}};
		return pos_neigh;
	}
	
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
	
	boolean same_position (int[] other_position) {
		return position[0] == other_position[0] && position[1] == other_position[1];
	}
	
	public boolean equals (Object obj) {
		return obj != null
				&& obj instanceof Element
				&& getClass().getName() == ((Element)obj).getClass().getName()
				&& same_position(((Element)obj).get_position())
				&& ((Element)obj).get_state() == state;
	}
	
	public String toString() {
		String res = state + ";" + position[0] + ";" + position[1];
		return res;
	}
}
