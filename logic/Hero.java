package maze.logic;
public class Hero {
	char symbol;
	int hX, hY;
	boolean armed;
	Hero(int x, int y) {hX = x; hY = y; armed = false; symbol = 'H';}
	int gethX(){return hX;}
	int gethY(){return hY;}
	void sethX(int x){hX = x;}
	void sethY(int y){hY = y;}
	char getSymbol(){return symbol;}
	void setSymbol(char s){symbol = s;}
}
