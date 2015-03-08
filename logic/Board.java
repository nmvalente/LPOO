package maze.logic;
import java.util.Scanner;

public class Board {
	Hero hero = new Hero(6,7);
	Dragon dragon = new Dragon(1,1);
	int ExitX, ExitY;
	int SwordX, SwordY;
	static char[][] board = {
		{'X','X','X','X','X','X','X','X','X','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X','E','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ','X',' ',' '},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
		{'X','X','X','X','X','X','X','X','X','X'}};

	Board(){
		//Hero hero = new Hero();//new Hero(7,1);
		//Dragon dragon = new Dragon(); // = new Dragon(4,1);
		ExitX = 5; 
		ExitY = 9;


		board[hero.gethX()][hero.gethY()] = hero.getSymbol();
		board[dragon.getdX()][dragon.getdY()] = 'D';
		board[ExitX][ExitY]='S';

	}
	Hero getHero() {return hero;}
	char[][] getBoard() {return board;}
	//void setBoard(char[][] b){board = b;}

	char verificaTecla(Scanner s){
		System.out.print("Introduza movimento: (E, D, C, B) or X to EXIT: ");
		char c = s.next().charAt(0);
		if(c == 'X') 
		{
			System.out.println("Decidiu abandonar o jogo");
			return c;
		}
		if((c == 'E') || (c == 'D') || (c == 'C') || (c == 'B'))
			return c;
		else
		{
			while((c != 'E') && (c != 'D') && (c != 'C') && (c != 'B') && (c != 'X'))
			{
				System.out.println("Tecla errada!");
				System.out.print("Introduza novamente o movimento pretendido: (E, D, C, B) or X to EXIT: ");
				c = s.next().charAt(0);
				if(c == 'X')
					System.out.println("Decidiu abandonar o jogo");
			}
		}
		return c;
	}


	public static void main(String[] args)
	{
		//		Hero hero = new Hero(7,1);
		//		Dragon dragon = new Dragon(4,1);
		Scanner s = new Scanner(System.in);
		Board b = new Board();
		do
		{
			b.printLabirinto();
		}
		while(b.jogada(s));
		b.printLabirinto();
		s.close();
	}


	void printLabirinto()
	{
		for(int i = 0 ; i < 10 ; i++){
			for(int j = 0 ; j < 10 ; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}

	boolean verificaEspada(char tcl){
		boolean b = false;
		if(tcl == 'E' && board[hero.hX][hero.hY-1] == 'E')
			b = true;
		else if(tcl == 'B' && board[hero.gethX()+1][hero.gethY()] == 'E')
			b = true;
		else if(tcl == 'D' && board[hero.gethX()][hero.gethY()+1] == 'E')
			b = true;
		else if(tcl == 'C' && board[hero.gethX()-1][hero.gethY()] == 'E')
			b = true;
		return b;
	}

	boolean posicaoVencedora(char tcl){
		boolean b = false;
		if(tcl == 'E' && board[hero.gethX()][hero.gethY()-1] == 'S' && board[hero.gethX()][hero.gethY()] == 'A')
			b = true;
		else if(tcl == 'B' && board[hero.gethX()+1][hero.gethY()] == 'S' && board[hero.gethX()][hero.gethY()] == 'A')
			b = true;
		else if(tcl == 'D' && board[hero.gethX()][hero.gethY()+1] == 'S' && board[hero.gethX()][hero.gethY()] == 'A')
			b = true;
		else if(tcl == 'C' && board[hero.gethX()-1][hero.gethY()] == 'S' && board[hero.gethX()][hero.gethY()] == 'A')
			b = true;
		return b;
	}

	boolean posicaoDragaoMataHeroi(){
		boolean b = false;
		if(((board[hero.gethX()][hero.gethY()-1] == 'D') || (board[hero.gethX()][hero.gethY()-1] == 'F')) && (board[hero.gethX()][hero.gethY()] == 'H'))
			b = true;
		else if(((board[hero.gethX()+1][hero.gethY()] == 'D') || (board[hero.gethX()+1][hero.gethY()] == 'F')) && (board[hero.gethX()][hero.gethY()] == 'H'))
			b = true;
		else if(((board[hero.gethX()][hero.gethY()+1] == 'D') || (board[hero.gethX()][hero.gethY()+1] == 'F')) && (board[hero.gethX()][hero.gethY()] == 'H'))
			b = true;
		else if(((board[hero.gethX()-1][hero.gethY()] == 'D') || (board[hero.gethX()-1][hero.gethY()] == 'F')) && (board[hero.gethX()][hero.gethY()] == 'H'))
			b = true;
		return b;
	}

	boolean posicaoHeroiMataDragao(){
		boolean b = false;
		if(board[hero.gethX()][hero.gethY()-1] == 'D' && board[hero.gethX()][hero.gethY()] == 'A')
			b = true;
		else if(board[hero.gethX()+1][hero.gethY()] == 'D' && board[hero.gethX()][hero.gethY()] == 'A')
			b = true;
		else if(board[hero.gethX()][hero.gethY()+1] == 'D' && board[hero.gethX()][hero.gethY()] == 'A')
			b = true;
		else if(board[hero.gethX()-1][hero.gethY()] == 'D' && board[hero.gethX()][hero.gethY()] == 'A')
			b = true;
		return b;
	}

	boolean jogada(Scanner s)
	{ 
		char tcl = verificaTecla(s);
		int tempX = hero.gethX();
		int tempY = hero.gethY();		
		if(tcl == 'X') return false;
		if(verificaEspada(tcl))
		{
			board[tempX][tempY] = ' ';
			hero.setSymbol('A');
			if(tcl == 'E') {board[tempX][tempY-1]=hero.getSymbol(); hero.sethY(tempY-1);}
			else if(tcl == 'D') {board[tempX][tempY+1]=hero.getSymbol(); hero.sethY(tempY+1);}
			else if(tcl == 'C') {board[tempX-1][tempY]=hero.getSymbol(); hero.sethX(tempX-1);}
			else if(tcl == 'B') {board[tempX+1][tempY]=hero.getSymbol(); hero.sethX(tempX+1);}
			return true;
		}
		if(posicaoVencedora(tcl))
		{
			board[tempX][tempY] = ' ';
			board[ExitX][ExitY] = 'W';
			return false;
		}

		switch(tcl)
		{
		case 'E':
		{
			if(board[hero.gethX()][hero.gethY()-1] == ' ') // se estiver a casa livre
			{
				board[tempX][tempY] = ' ';
				board[tempX][tempY-1] = hero.getSymbol();
				hero.sethY(tempY-1);
			}
			break;
		}
		case 'D':
		{
			if(board[hero.gethX()][hero.gethY()+1] == ' ') 
			{
				board[tempX][tempY] = ' ';
				board[tempX][tempY+1] = hero.getSymbol();
				hero.sethY(tempY+1);
			}
			break;
		}
		case 'B':
		{
			if(board[hero.gethX()+1][hero.gethY()] == ' ') 
			{
				board[tempX][tempY] = ' ';
				board[tempX+1][tempY] = hero.getSymbol();
				hero.sethX(tempX+1);
			}
			break;
		}
		case 'C':
		{
			if(board[hero.gethX()-1][hero.gethY()] == ' ') 
			{
				board[tempX][tempY] = ' ';
				board[tempX-1][tempY] = hero.getSymbol();
				hero.sethX(tempX-1);
			}
			break;
		}
		}	
		int oldX = dragon.getdX();
		int oldY = dragon.getdY();
		char temp = board[oldX][oldY];
		if(dragon.getAdormecido() == false)
		{
			MoveDragon mv = new MoveDragon(); 
			char P = mv.newPosition();

			//System.out.println(P);

			if(mv.verificaMovimento(board, P, dragon))
				mv.movimenta(board, P, dragon);

			if(posicaoDragaoMataHeroi())
			{
				board[oldX][oldY] = temp;
				board[hero.gethX()][hero.gethY()] = ' ';
				return false;
			}

			if(posicaoHeroiMataDragao())
			{
				board[oldX][oldY] = ' ';	
				dragon.setAdormecido(true);
			}
		}
		return true;
	}
}
