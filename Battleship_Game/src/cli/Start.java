package cli;

import logic.Game;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * Class Start used to start the game and call the other classes
 */
public class Start {

    /**
     * Instantiates a new Start
     *
     * @param scan scanner used to get input from the console
     * @param random random number generator for automatic ship placement
     * @param name1Def default name for player 1
     * @param name2Def default name for player 2
     * @param configDef default configuration file
     * @param file1Def default file for player 1
     * @param file2Def default file for player 2
     */
    Start(Scanner scan, Random random, String name1Def, String name2Def, String configDef, String file1Def, String file2Def) {
        Game game = Game.Instance();
        Utils.clearScreen();
        System.out.println("Battleship!");
        System.out.println();
        System.out.print("1 - New Game / 2 - Load Game ? ");
        String answer = scan.nextLine();
        new Setup(scan, name1Def, name2Def, file1Def, file2Def);
        if (Objects.equals(answer, "2")) {
            game.loadGame(random);
            Utils.clearScreen();
        }
        else {
            new Config(scan, configDef);
            new Place(scan, random);
            game.startGame(random);
        }
        new Play(scan);
    }

    /**
     * main method for the command line interface
     *
     * @param args default main method argument
     */
    public static void main(String[] args) {
        new Utils(true, true); /* ide = true, nix = true */
        String name1 = "Player 1";
        String name2 = "Player 2";
        String config = "/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/config.txt";
        String file1 = "/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board1.txt";
        String file2 = "/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board2.txt";
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        new Start(scan, random, name1, name2, config, file1, file2);
    }
}
