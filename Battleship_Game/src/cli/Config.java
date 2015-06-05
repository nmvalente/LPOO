package cli;

import logic.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Config {

    private Game game;

    Config(Scanner scan, String configDef) {
        game = Game.Instance();
        inputFile(scan, configDef);
        readConfig();
        validateConfig();
        System.out.print("Press enter to continue. ");
        scan.nextLine();
    }

    private void inputFile(Scanner scan, String configDef) {
        System.out.print("Path to the configuration file: ");
        String configFile = scan.nextLine();
        if (!new File(configFile).isFile()) configFile = configDef;
        game.setConfigFile(configFile);
    }


    private void readConfig() {
        try {
            game.readConfig();
        }
        catch(FileNotFoundException FNFExcept) {
            System.out.println("The configuration file is not available.");
            System.out.println("A basic configuration will be used.");
            game.loadConfig();
        }
        catch (IOException IOExcept) {
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
