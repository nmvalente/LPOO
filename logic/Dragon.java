package maze.logic;
public class Dragon {
	int dX, dY;
	boolean adormecido, espada;

	Dragon(int x, int y){dX = x; dY = y; adormecido = false; espada = false;}
	void setdXdY(int x, int y){dX = x; dY = y;}
	int getdX(){return dX;}
	int getdY(){return dY;}
	void setAdormecido(boolean b){adormecido = b;}
	boolean getAdormecido(){return adormecido;}
	void setEspada(boolean b){espada = b;}
	boolean getEspada(){return espada;}

}