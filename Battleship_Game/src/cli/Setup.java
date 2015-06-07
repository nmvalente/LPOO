package cli;

import logic.Game;

import java.util.Random;
import java.util.Scanner;

/**
 * Class Setup used to do initial game setup: obtain number of players, player names and save files
 */
public class Setup {

    /** Game that we will setup */
    private Game game;

    /**
     * Instantiates a new setup
     *
     * @param scan scanner used to get input from the console
     * @param name1Def default name for player 1
     * @param name2Def default name for player 2
     * @param file1Def default file for player 1
     * @param file2Def default file for player 2
     */
    Setup(Scanner scan, String name1Def, String name2Def, String file1Def, String file2Def) {
        game = Game.Instance();
        inputNPlayers(scan);
        inputNames(scan, name1Def, name2Def);
        inputFiles(scan, file1Def, file2Def);
    }
	// for gui
	public Setup(Scanner scan, String file1, String file2, Random random)
	{
		game = Game.Instance();
		game.setNumberPlayers(2);
		game.setPlayer1File(file1);
		game.setPlayer2File(file2);
		new Place(scan, random);
		game.startGame();
	}
    /**
     * Gets the number of players from the console
     *
     * @param scan scanner used to get input from the console
     */
    private void inputNPlayers(Scanner scan) {
        int numberPlayers = 1;
        System.out.print("Number of players (1/2)? ");
        if (scan.hasNextInt()) numberPlayers = scan.nextInt();
        scan.nextLine();
        if (numberPlayers != 2) numberPlayers = 1;
        game.setNumberPlayers(numberPlayers);
    }

    /**
     * Gets the player names from the console
     *
     * @param scan scanner used to get input from the console
     * @param name1Def default name for player 1 in case an empty name is chosen
     * @param name2Def default name for player 2 in case an empty name is chosen
     */
    private void inputNames(Scanner scan, String name1Def, String name2Def) {
        String player1Name;
        String player2Name;
        if (game.getNumberPlayers() == 1) {
            System.out.print("Name of the player: ");
            player1Name = scan.nextLine();
            if (player1Name.length() == 0) player1Name = name1Def;
            player2Name = "Computer";
        } else {
            System.out.print("Name of the first player: ");
            player1Name = scan.nextLine();
            if (player1Name.length() == 0) player1Name = name1Def;
            System.out.print("Name of the second player: ");
            player2Name = scan.nextLine();
            if (player2Name.length() == 0) player2Name = name2Def;
        }
        game.setPlayer1Name(player1Name);
        game.setPlayer2Name(player2Name);
    }

    /**
     * Gets the player files from the console
     *
     * @param scan scanner used to get input from the console
     * @param file1Def default path for the file of player 1 in case an empty file is chosen
     * @param file2Def default path for the file of player 2 in case an empty file is chosen
     */
    private void inputFiles(Scanner scan, String file1Def, String file2Def) {
        String player1File;
        String player2File;
        if (game.getNumberPlayers() == 1) {
            player1File = setPlayerFile(scan, " 1", file1Def);
            player2File = file2Def;
        } else {
            player1File = setPlayerFile(scan, " 1", file1Def);
            player2File = setPlayerFile(scan, " 2", file2Def);
        }
        game.setPlayer1File(player1File);
        game.setPlayer2File(player2File);
    }

    /**
     * Gets a given player file from the console
     *
     * @param scan scanner used to get input from the console
     * @param number number of the player whose file we want to get
     * @param fileDef default file path
     *
     * @return string with the path to the player file
     */
    private String setPlayerFile(Scanner scan, String number, String fileDef) {
//        String finalFile = "y";
        String playerFile;
//        do {
            System.out.print("Path to the board file of player" + number + ": ");
            playerFile = scan.nextLine();
//            if (!new File(playerFile).isFile()) {
//                System.out.print("The file already exists. Do you want to replace it (y/n)? ");
//                finalFile = scan.nextLine();
//            }
//        } while (Objects.equals(finalFile, "n"));
        if (playerFile.length() == 0) playerFile = fileDef;
        return playerFile;
    }
    
}

