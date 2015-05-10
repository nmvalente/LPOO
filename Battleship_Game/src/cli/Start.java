package cli;

import logic.Game;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Start extends Setup {

    Game game;

    public Start(Scanner scan, String name1Def, String name2Def, String configDef, String file1Def, String file2Def) {
        super(scan, name1Def, name2Def, configDef, file1Def, file2Def);
        game = new Game(getPlayer1Name(), getPlayer2Name());
        readConfig();
        validateConfig();
        System.out.print("Press enter to continue. ");
        String temp = scan.nextLine();
    }

    private void readConfig() {
        try {
            game.readConfig(getConfigFile());
        }
        catch(FileNotFoundException FNFExcept) {
            System.out.println("The configuration file is not available.");
            System.out.println("A basic configuration will be used.");
            game.loadConfig();
        }
        catch (IOException ioexception) {
            System.out.println("The contents of the configuration file are not as expected.");
            System.out.println("A basic configuration will be used.");
            game.loadConfig();
        }
    }

    private void validateConfig() {
        int validation = game.validateConfig();
        if (validation > 0) {
            if (validation == 1) {
                System.out.println("The board size from the file is too big.");
                System.out.println("Each dimension, vertical and horizontal, must be at most 26.");
            }
            else if (validation == 2)
                System.out.println("The board size from the file is too small for the total number of cells of the ships.");
            else if (validation == 3)
                System.out.println("The configuration file contains one or more invalid colors.");
            else if (validation == 4)
                System.out.println("There are ships of different types with the same symbol in the configuration file.");
            else if (validation == 5)
                System.out.println("There are ships of different types with the same color in the configuration file.");
            System.out.println("A basic configuration will be used.");
            game.loadConfig();
        }
    }

}
