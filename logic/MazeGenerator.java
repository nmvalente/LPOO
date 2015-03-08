package maze.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MazeGenerator {

	private int height, width;
	private int [][]mazeG;
	public MazeGenerator(int dimention) {
		height = dimention;
		width = dimention;
		mazeG = new int[height][width];
	}

	public static void main(String[] args)
	{
		MazeGenerator maze = new MazeGenerator(11);
		maze.generateMaze();
		int [][] tabuleiro = maze.getMaze();
		for(int i = 0 ; i < maze.height ; i++)
		{
			for(int j = 0 ; j < maze.width ; j++)
			{
				if(tabuleiro[i][j] == 1)
					tabuleiro[i][j] = 'X';
				else tabuleiro[i][j] = ' ';
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
	}


public int[][] getMaze()
{
	return mazeG;
}

public void generateMaze() {
	for (int i = 0; i < height; i++)
		for (int j = 0; j < width; j++)
			mazeG[i][j] = 1;

	Random rand = new Random();
	// r for row、c for column
	// Generate random r
	int r = rand.nextInt(height);
	while (r % 2 == 0) {
		r = rand.nextInt(height);
	}
	// Generate random c
	int c = rand.nextInt(width);
	while (c % 2 == 0) {
		c = rand.nextInt(width);
	}
	// Starting cell
	mazeG[r][c] = 0;

	//　Allocate the maze with recursive method
	recursion(r, c);

}

public void recursion(int r, int c) {
	// 4 random directions
	Integer[] randDirs = generateRandomDirections();
	// Examine each direction
	for (int i = 0; i < randDirs.length; i++) {

		switch(randDirs[i]){
		case 1: // Up
			//　Whether 2 cells up is out or not
			if (r - 2 <= 0)
				continue;
			if (mazeG[r - 2][c] != 0) {
				mazeG[r-2][c] = 0;
				mazeG[r-1][c] = 0;
				recursion(r - 2, c);
			}
			break;
		case 2: // Right
			// Whether 2 cells to the right is out or not
			if (c + 2 >= width - 1)
				continue;
			if (mazeG[r][c + 2] != 0) {
				mazeG[r][c + 2] = 0;
				mazeG[r][c + 1] = 0;
				recursion(r, c + 2);
			}
			break;
		case 3: // Down
			// Whether 2 cells down is out or not
			if (r + 2 >= height - 1)
				continue;
			if (mazeG[r + 2][c] != 0) {
				mazeG[r+2][c] = 0;
				mazeG[r+1][c] = 0;
				recursion(r + 2, c);
			}
			break;
		case 4: // Left
			// Whether 2 cells to the left is out or not
			if (c - 2 <= 0)
				continue;
			if (mazeG[r][c - 2] != 0) {
				mazeG[r][c - 2] = 0;
				mazeG[r][c - 1] = 0;
				recursion(r, c - 2);
			}
			break;
		}
	}

}

public Integer[] generateRandomDirections() {
	ArrayList<Integer> randoms = new ArrayList<Integer>();
	for (int i = 0; i < 4; i++)
		randoms.add(i + 1);
	Collections.shuffle(randoms);

	return randoms.toArray(new Integer[4]);
}

}