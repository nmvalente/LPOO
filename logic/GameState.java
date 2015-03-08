package maze.logic;
public class GameState {
	Board board;
	boolean state = true;
	GameState(Board b, boolean stat){
		board = b;
		state = stat;
	}
	
	void setGameState(boolean b){state = b;}
	boolean getGameState(){return state;}
}