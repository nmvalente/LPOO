package cli;

import logic.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class Config used to configure a new game in the command line interface
 */
public class Config {

    /** Game that will be configured */
    private Game game;

    /**
     * Instantiates a new config
     *
     * @param scan scanner used to get input from the console
     * @param configDef path to the default configuration file
     */
    Config(Scanner scan, String configDef) {
        game = Game.Instance();
        inputFile(scan, configDef);
        readConfig();
        validateConfig();
        System.out.print("Press enter to continue. ");
        scan.nextLine();
    }

    /**
     * Gets the path to the configuration file from the console
     *
     * @param scan scanner used to get input from the console
     * @param configDef path to the default configuration file
     */
    private void inputFile(Scanner scan, String configDef) {
        System.out.print("Path to the configuration file: ");
        String configFile = scan.nextLine();
        if (!new File(configFile).isFile()) configFile = configDef;
        game.setConfigFile(configFile);
    }

    /**
     * Reads the configuration file and configures the game accordingly
     */
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

    /**
     * Validates de configuration that was read and if there is some problem loads a basic configuration
     */
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
