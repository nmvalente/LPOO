package cli;

import logic.Game;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Start {

    Start(Scanner scan, Random random, String name1Def, String name2Def, String configDef, String file1Def, String file2Def) {
        Game game = Game.Instance();
        Utils.clearScreen();
        System.out.println("Battleship!");
        System.out.println();
        System.out.print("1 - New Game / 2 - Load Game ? ");
        String answer = scan.nextLine();
        new Setup(scan, name1Def, name2Def, file1Def, file2Def);
        if (Objects.equals(answer, "2")) {
            game.loadGame();
        }
        else {
            new Config(scan, configDef);
            new Place(scan, random);
            game.startGame();
        }
        new Play(scan, random);
    }

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
