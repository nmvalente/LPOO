package cli;

import logic.Game;

import java.util.Scanner;

public class Setup {

    private Game game;

    Setup(Scanner scan, String name1Def, String name2Def, String file1Def, String file2Def) {
        game = Game.Instance();
        inputNPlayers(scan);
        inputNames(scan, name1Def, name2Def);
        inputFiles(scan, file1Def, file2Def);
    }

    private void inputNPlayers(Scanner scan) {
        int numberPlayers = 1;
        System.out.print("Number of players (1/2)? ");
        if (scan.hasNextInt()) numberPlayers = scan.nextInt();
        scan.nextLine();
        if (numberPlayers != 2) numberPlayers = 1;
        game.setNumberPlayers(numberPlayers);
    }

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

