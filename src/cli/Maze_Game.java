package cli;

import java.util.ArrayList;
import java.util.Scanner;

public class Maze_Game {

	private static final int SLEEP_TIME = 1000;
	logic.Game game;

	public Maze_Game(Scanner scan) {
		game = new logic.Game();
		System.out.print("Maze type (0 - static, 1 - random)? ");
		int number = scan.nextInt();
		game.set_maze_type(number);
		if (number == 1) {
			System.out.print("Maze size? ");
			number = scan.nextInt();
			game.set_maze_size(number);
			System.out.print("Number of dragons? ");
			number = scan.nextInt();
			game.set_number_dragons(number);
			System.out.print("Type of dragons (0 - not moving, 1 - moving, 2 - sleeping and moving)? ");
			number = scan.nextInt();
			game.set_dragon_type(number);
			System.out.print("Number of darts? ");
			number = scan.nextInt();
			game.set_number_darts(number);
		}
		else { 
			game.set_maze_size(10);
			game.set_number_dragons(1);
			game.set_dragon_type(0);
			game.set_number_darts(0);
		}
		game.start_game();
	}

	public void print_maze() {
		int size = game.get_maze_size();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int[] position = {i, j};
				System.out.print(game.get_maze().get_board_position(position)+" ");
			}
			System.out.println();
		}
	}

	private static void play_hero(Scanner scan, Maze_Game mg) {
		System.out.println();		
		System.out.print("Hero: w, a, s, d? ");
		char hero_direction = scan.next().charAt(0);
		int direction = 0;
		switch (hero_direction) {
		case 'w' :
			direction = 2;
			break;
		case 'a' :
			direction = 1;
			break;
		case 's' :
			direction = 3;
			break;
		case 'd' :
			direction = 0;
			break;
		}				
		mg.game.hero_turn(direction);
		System.out.println();
		mg.print_maze();
	}

	private static void hero_dart(Scanner scan, Maze_Game mg) {
		System.out.println();		
		System.out.print("Dart: w, a, s, d, q? ");
		char dart_direction = scan.next().charAt(0);
		int direction = 4;
		switch (dart_direction) {
		case 'w' :
			direction = 2;
			break;
		case 'a' :
			direction = 1;
			break;
		case 's' :
			direction = 3;
			break;
		case 'd' :
			direction = 0;
			break;
		}				
		if (direction < 4) mg.game.hero_dart(direction);
		System.out.println();
		mg.print_maze();
	}

	private static void play_dragon(Maze_Game mg) {
		System.out.println();		
		System.out.print("Dragons...");
		try {
			Thread.sleep(SLEEP_TIME);
		}
		catch(Exception e) {
			System.out.println("Exception caught");
		}			
		System.out.println("\n");
		mg.game.dragon_turn();
		mg.print_maze();
	}

	private static void maybe_fight(Maze_Game mg) {
		ArrayList<Integer> dragons_c = mg.game.dragons_close();
		if (!dragons_c.isEmpty()) {
			System.out.println();		
			System.out.print("Fight!!!");
			try {
				Thread.sleep(SLEEP_TIME);
			}
			catch(Exception e) {
				System.out.println("Exception caught");
			}			
			System.out.println("\n");
			mg.game.fight(dragons_c);
			mg.print_maze();
		}
	}

	private static void maybe_burn(Maze_Game mg) {
		if (mg.game.burn_hero()) {
			System.out.println();		
			System.out.print("Burn!!!");
			try {
				Thread.sleep(SLEEP_TIME);
			}
			catch(Exception e) {
				System.out.println("Exception caught");
			}			
			System.out.println("\n");
			mg.print_maze();
		}
	}

	private static void game_result(Maze_Game mg) {
		System.out.println();		
		if (mg.game.get_game_state() == 1) System.out.print("You Win!!!");
		else System.out.print("You Lose!!!");
	}
	
	private static void play(Scanner scan, Maze_Game mg) {
		System.out.println();
		mg.print_maze();
		do {
			play_hero(scan, mg);
			if (mg.game.get_number_dragons() > 0) {
				maybe_fight(mg);
				if (mg.game.get_game_state() == 0 && mg.game.get_number_dragons() > 0) {
					maybe_burn(mg);
					if (mg.game.get_game_state() == 0 
							&& mg.game.get_number_dragons() > 0 
							&& mg.game.get_hero().get_hero_darts() > 0) hero_dart(scan, mg);
					if (mg.game.get_dragon_type() != 0 && mg.game.get_game_state() == 0 && mg.game.get_number_dragons() > 0) {
						play_dragon(mg);
						maybe_fight(mg);
						if (mg.game.get_game_state() == 0 && mg.game.get_number_dragons() > 0) maybe_burn(mg);
					}
				}
			}
		}
		while (mg.game.get_game_state() == 0);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		Maze_Game mg = new Maze_Game(scan);
		play(scan, mg);
		game_result(mg);
		scan.close();
	}	
}
