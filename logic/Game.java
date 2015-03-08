package maze.logic;
import java.util.Scanner;


public class Game{
	Board b;
	//Game(){b = new Board();}

	Board getBoard() {return b;}

	int configuration()
	{
		System.out.println("Please enter dimension n: ");
		Scanner s = new Scanner(System.in);
		s.close();
		return s.nextInt();
	}	
}