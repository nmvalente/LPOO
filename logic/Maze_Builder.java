package logic;

public class Maze_Builder {

	private int size;
	private int type; 
	
	public void set_maze_type(int tp) {
		type = tp;
	}
	
	public void set_maze_size(int sz) {
		size = sz;
	}
	
	public Maze get_maze(Exit exit) {
		if (type == 0) return new Static_Maze();
		else return new Random_Maze(size, exit);
	}
}
