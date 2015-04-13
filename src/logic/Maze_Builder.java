package logic;

class Maze_Builder {

	private int size;
	private int type; 
	
	void set_maze_type(int tp) {
		type = tp;
	}
	
	void set_maze_size(int sz) {
		size = sz;
	}
	
	Maze get_maze(Exit exit) {
		if (type == 0) return new Static_Maze();
		else return new Random_Maze(size, exit);
	}

	Maze get_maze(Exit exit, char[][] stat_maze) {
		return new Static_Maze(stat_maze);
	}
}
