package logic;

import java.util.Random;

public class Random_Exit {
	
	public Random_Exit(Game g, Exit exit) {
		Random rn = new Random();
		int exit_wall = rn.nextInt(4);
		int size = g.get_maze_size();
		int exit_cell = rn.nextInt(size - 2) + 1;
		switch (exit_wall) {
		case 0 : // cima
			exit.set_position(new int[] {0, exit_cell});
			break;
		case 1 : // baixo
			exit.set_position(new int[] {size - 1, exit_cell});
			break;
		case 2 : // direita
			exit.set_position(new int[] {exit_cell, size - 1});
			break;
		case 3 : // esquerda
			exit.set_position(new int[] {exit_cell, 0});
			break;
		}
	}

}
