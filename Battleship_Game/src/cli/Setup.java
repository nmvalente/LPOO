package cli;

import java.io.File;
import java.util.Scanner;

public class Setup {

    private int numberPlayers;

    private String player1Name;

    private String player2Name;

    private String configFile;

    private String player1File;

    private String player2File;

    Setup(Scanner scan, String name1Def, String name2Def, String configDef, String file1Def, String file2Def) {
        inputNPlayers(scan);
        inputNames(scan, name1Def, name2Def);
        inputFiles(scan, configDef, file1Def, file2Def);
    }

    private void inputNPlayers(Scanner scan) {
        System.out.print("Number of players (1/2)? ");
        if (scan.hasNextInt()) numberPlayers = scan.nextInt();
        if (numberPlayers != 2) numberPlayers = 1;
    }

    private void inputNames(Scanner scan, String name1Def, String name2Def) {
        if (numberPlayers == 1) {
            System.out.print("Name of the player: ");
            player1Name = scan.nextLine();
            if (player1Name.length() == 0) player1Name = name1Def;
            player2Name = "Computer";
        } else {
            System.out.print("Name of the first player: ");
            player1Name = scan.nextLine();
            System.out.print("Name of the second player: ");
            player2Name = scan.nextLine();
        }
    }

    private void inputFiles(Scanner scan, String configDef, String file1Def, String file2Def) {
        System.out.print("Path to the configuration file: ");
        configFile = scan.nextLine();
        if (!new File(configFile).isFile()) configFile = configDef;
        if (numberPlayers == 1) {
            player1File = setPlayerFile(scan, " 1", file1Def);
            player2File = file2Def;
        } else {
            player1File = setPlayerFile(scan, " 1", file1Def);
            player2File = setPlayerFile(scan, " 2", file2Def);
        }
    }

    private String setPlayerFile(Scanner scan, String number, String fileDef) {
        char finalFile = 'y';
        String playerFile;
        do {
            System.out.print("Path to the board file of player" + number + ": ");
            playerFile = scan.nextLine();
            if (!new File(player1File).isFile()) {
                System.out.print("The file already exists. Do you want to replace it (y/n)? ");
                finalFile = scan.nextLine().charAt(0);
            }
        } while (finalFile != 'y');
        if (playerFile.length() == 0) playerFile = fileDef;
        return playerFile;
    }

    int getNumberPlayers() {
        return numberPlayers;
    }

    String getPlayer1Name() {
        return player1Name;
    }

    String getPlayer2Name() {
        return player2Name;
    }

    String getConfigFile() {
        return configFile;
    }

    String getPlayer1File() {
        return player1File;
    }

    String getPlayer2File() {
        return player2File;
    }

}

