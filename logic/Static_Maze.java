package logic;

public class Static_Maze extends Maze {
	
	public Static_Maze() {
		make_static_maze();
	}
	
	public void make_static_maze() {
		board = new char[][]{{'X','X','X','X','X','X','X','X','X','X'},
							 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
							 {'X',' ','X','X',' ','X',' ','X',' ','X'},
							 {'X',' ','X','X',' ','X',' ','X',' ','X'},
							 {'X',' ','X','X',' ','X',' ','X',' ','X'},
							 {'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
							 {'X',' ','X','X',' ','X',' ','X',' ','X'},
							 {'X',' ','X','X',' ','X',' ','X',' ','X'},
							 {'X',' ','X','X',' ',' ',' ',' ',' ','X'},
							 {'X','X','X','X','X','X','X','X','X','X'}};
	}
	
}