package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

class Random_Maze extends Maze{

	Random_Maze(int size, Exit exit) {
		make_random_maze(size, exit);
	}
	
	private void make_random_maze(int size, Exit exit) {
		Random rn = new Random();
		int[] exit_position = exit.get_position();
		int[] next_2_exit = {0, 0};
		if (exit_position[0] == 0) {
			next_2_exit[0] = 1;
			next_2_exit[1] = exit_position[1];
		}
		if (exit_position[0] == size - 1) {
			next_2_exit[0] = size - 2;
			next_2_exit[1] = exit_position[1];
		}
		if (exit_position[1] == 0) {
			next_2_exit[1] = 1;
			next_2_exit[0] = exit_position[0];
		}
		if (exit_position[1] == size - 1) {
			next_2_exit[1] = size - 2;
			next_2_exit[0] = exit_position[0];
		}
		ArrayList<int[]> unvisited_list = new ArrayList<int[]>();
		Stack<int[]> visited_stack = new Stack<int[]>();
		int[][] wall_not_wall = new int[size][size]; // 1 wall, 0 not wall
		board = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i % 2 == 0 || j % 2 == 0) wall_not_wall[i][j] = 1;
				else {
					wall_not_wall[i][j] = 0;
					int[] cell = {i, j};
					unvisited_list.add(cell);
				}
			}
		}
		wall_not_wall[next_2_exit[0]][next_2_exit[1]] = 0;
		unvisited_list.remove(next_2_exit);
		int[] current_cell = {next_2_exit[0], next_2_exit[1]};
		int[] next_cell;
		while (!unvisited_list.isEmpty()) {
			List<Integer> unvisited_neigh = new ArrayList<Integer>();
			for (int i = 0; i < unvisited_list.size(); i++) {
				int[] cell = unvisited_list.get(i);
				if ((cell[0] == current_cell[0] && 
						(cell[1] == current_cell[1] + 2 || cell[1] == current_cell[1] - 2)) ||
					 cell[1] == current_cell[1] && 
					 	(cell[0] == current_cell[0] + 2 || cell[0] == current_cell[0] - 2))
					 		unvisited_neigh.add(i);
			}
			if (!unvisited_neigh.isEmpty()) {
				int random_neighbour = unvisited_neigh.get(rn.nextInt(unvisited_neigh.size()));
				next_cell = unvisited_list.remove(random_neighbour);
				visited_stack.push(current_cell);
				int[] wall = {current_cell[0]+(next_cell[0]-current_cell[0])/2,
						current_cell[1]+(next_cell[1]-current_cell[1])/2};
				wall_not_wall[wall[0]][wall[1]] = 0;
			}
			else if (!visited_stack.isEmpty()) {
				next_cell = visited_stack.pop();
			}
			else {
				next_cell = unvisited_list.remove(rn.nextInt(unvisited_list.size()));
			}
			current_cell = next_cell;
		}
		ArrayList<int[]> not_wall_list = new ArrayList<int[]>();		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (wall_not_wall[i][j] == 1) board[i][j] = 'X';
				else {
					board[i][j] = ' ';
					int[] cell = {i, j};
					not_wall_list.add(cell);
				}
			}
		}
	}
	
}
