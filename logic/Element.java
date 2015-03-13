package logic;

public class Element {

	private int[] position;
	private char state;
	
	int[] get_position() {
		return position;
	}
	
	void set_position(int[] new_position) {
		position = new_position;
	}
	
	char get_state() {
		return state;
	}
	
	void set_state(char new_state) {
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
	
	boolean same_position (int[] other_position) {
		if (position[0] == other_position[0] && position[1] == other_position[1]) return true;
		else return false;
	}
	
}
