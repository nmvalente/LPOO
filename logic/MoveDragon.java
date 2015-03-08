package maze.logic;
import java.util.Random;

public class MoveDragon {

	char newPosition() {
		Random r = new Random();	
		char posicoes[] = new char[4];
		posicoes[0] = 'E';
		posicoes[1] = 'D';
		posicoes[2] = 'C';
		posicoes[3] = 'B';
		int pos = r.nextInt(4);	
		return posicoes[pos];
	}

	void movimentaEsquerda(char[][] b, Dragon dragon){
		int x = dragon.getdX();
		int y = dragon.getdY();
		if(dragon.getEspada())
		{
			b[x][y] = 'E';
			b[x][y-1] = 'D';
			dragon.setEspada(false);
		}
		else
		{
			if(b[x][y-1] == 'E')
			{
				b[x][y] = ' ';
				b[x][y-1] = 'F';
				dragon.setEspada(true);
			}
			else if(b[x][y-1] == ' ') 
			{
				b[x][y] = ' ';
				b[x][y-1] = 'D';
			}
		}
		dragon.setdXdY(x,y-1);
	}

	void movimentaDireita(char[][] b, Dragon dragon){
		int x = dragon.getdX();
		int y = dragon.getdY();
		if(dragon.getEspada())
		{
			b[x][y] = 'E';
			b[x][y+1] = 'D';
			dragon.setEspada(false);
		}
		else
		{
			if(b[x][y+1] == 'E')
			{
				b[x][y] = ' ';
				b[x][y+1] = 'F';
				dragon.setEspada(true);
			}
			else if(b[x][y+1] == ' ')
			{
				b[x][y] = ' ';
				b[x][y+1] = 'D';
			}
		}
		dragon.setdXdY(x,y+1);
	}
	void movimentaCima(char[][] b, Dragon dragon){
		int x = dragon.getdX();
		int y = dragon.getdY();
		if(dragon.getEspada())
		{
			b[x][y] = 'E';
			b[x-1][y] = 'D';
			dragon.setEspada(false);
		}
		else
		{
			if(b[x-1][y] == 'E')
			{
				b[x][y] = ' ';
				b[x-1][y] = 'F';
				dragon.setEspada(true);
			}
			else if(b[x-1][y] == ' ')
			{
				b[x][y] = ' ';
				b[x-1][y] = 'D';
			}
		}
		dragon.setdXdY(x-1,y);
	}
	void movimentaBaixo(char[][] b, Dragon dragon){
		int x = dragon.getdX();
		int y = dragon.getdY();
		if(dragon.getEspada())
		{
			b[x][y] = 'E';
			b[x+1][y] = 'D';
			dragon.setEspada(false);
		}
		else
		{
			if(b[x+1][y] == 'E')
			{
				b[x][y] = ' ';
				b[x+1][y] = 'F';
				dragon.setEspada(true);
			}
			else if(b[x+1][y] == ' ')
			{
				b[x][y] = ' ';
				b[x+1][y] = 'D';
			}
		}
		dragon.setdXdY(x+1,y);
	}

	boolean verificaMovimento(char[][] b, char P, Dragon d){
		if(P == 'E' && ((b[d.getdX()][d.getdY()-1] == 'X') || (b[d.getdX()][d.getdY()-1] == 'H') || (b[d.getdX()][d.getdY()-1] == 'S') || (b[d.getdX()][d.getdY()-1] == 'A')))
			return false;
		else if(P == 'D' && ((b[d.getdX()][d.getdY()+1] == 'X') || (b[d.getdX()][d.getdY()+1] == 'H') || (b[d.getdX()][d.getdY()+1] == 'S') || (b[d.getdX()][d.getdY()+1] == 'A')))
			return false;
		else if(P == 'B' && ((b[d.getdX()+1][d.getdY()] == 'X') || (b[d.getdX()+1][d.getdY()] == 'H') || (b[d.getdX()+1][d.getdY()] == 'S') || (b[d.getdX()+1][d.getdY()] == 'A')))
			return false;
		else if(P == 'C' && ((b[d.getdX()-1][d.getdY()] == 'X') || (b[d.getdX()-1][d.getdY()] == 'H') || (b[d.getdX()-1][d.getdY()] == 'S') || (b[d.getdX()-1][d.getdY()] == 'A')))
			return false;
		else return true;
	}

	void movimenta(char[][] b,  char P, Dragon d) {
		if(P == 'E')
			movimentaEsquerda(b,d);
		else if(P == 'D')
			movimentaDireita(b,d);
		else if(P == 'C')
			movimentaCima(b,d);
		else if(P == 'B')
			movimentaBaixo(b,d);
	}
}