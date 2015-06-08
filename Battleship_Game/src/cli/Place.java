package cli;

import logic.Game;
import logic.Position;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * Class Place used to place the players ships on the board after the configuration of a new game and before playing
 */
public class Place {

    /** Game in which we will be placing the players' ships */
    private Game game;

    /**
     * Instantiates a new Place
     *
     * @param scan scanner used to get input from the console
     * @param random random number generator for the automatic ship placement
     */
    public Place(Scanner scan, Random random) {
        game = Game.Instance();
        placeShips(scan, random);
        game.setStartingPlayer(random.nextInt(2) + 1);
    }

    /**
     * Chooses the type of placement (manual or automatic) for the ships of a given player
     *
     * @param scan scanner used to get input from the console
     * @param random random number generator for the automatic ship placement
     * @param playerName name of the player whose ship placement will be chosen
     * @param playerNumber number of the player whose ship placement will be chosen
     */
    private void choosePlacing(Scanner scan, Random random, String playerName, int playerNumber) {
        System.out.print(playerName + ": place ships automatically (y/n)? ");
        String answer = scan.nextLine();
        if (Objects.equals(answer, "y")) {
            game.autoPlaceShips(random, playerNumber);
            System.out.println();
            System.out.println(game.printPlayer(playerNumber));
            repositionShips(scan, playerName, playerNumber);
        }
        else placePlayerShips(scan, playerName, playerNumber);
    }

    /**
     * Manually places a players ships on the board
     *
     * @param scan scanner used to get input from the console
     * @param playerName name of the player whose ships will be manually placed
     * @param playerNumber number of the player whose ships will be manually placed
     */
    private void placePlayerShips(Scanner scan, String playerName, int playerNumber) {
        char maxLine = (char)(game.getDimV() + 65);
        char maxColumn = (char)(game.getDimH() + 97);
        System.out.println(playerName + " - distribute fleet:");
        System.out.println("");
        int line, column;
        Position position = null;
        boolean validPosition;
        boolean orientation = true;
        for (int i = 0; i < game.getNumberShips(); i++) {
            do {
                Utils.clearScreen();
                System.out.println(game.printPlayer(playerNumber));
                System.out.println(game.printShip(playerNumber, i));
                System.out.print("LINE (A.." + maxLine + ") COLUMN (a.." + maxColumn + ") ORIENTATION (H V)? ");
                String pos = scan.nextLine();
                Vector<Object> shipPosition = readShipPosition(pos);
                validPosition = (boolean)(shipPosition.get(0));
                if (validPosition) {
                    line = (int) (shipPosition.get(1));
                    column = (int) (shipPosition.get(2));
                    position = Position.Instance(line, column);
                    orientation = (boolean) (shipPosition.get(3));
                }
            } while (!validPosition || !game.manualPlaceShip(playerNumber, i, position, orientation));
            game.placeShipBoard(playerNumber, i);
        }
        repositionShips(scan, playerName, playerNumber);
    }

    /**
     * Allows the player to change the position of some ships
     *
     * @param scan scanner used to get input from the console
     * @param playerName name of the player that will be asked about repositioning their ships
     * @param playerNumber number of the player that will be asked about repositioning their ships
     */
    private void repositionShips(Scanner scan, String playerName, int playerNumber) {
        String reposition;
        do {
            Utils.clearScreen();
            System.out.println(game.printPlayer(playerNumber));
            System.out.print(playerName + ": reposition ships (y/n)? ");
            reposition = scan.nextLine();
            if (Objects.equals(reposition, "y")) changeShipPosition(scan, playerNumber);
        } while (Objects.equals(reposition, "y"));
    }

    /**
     * Reads a ship position and orientation from a string extracting the resulting elements to a vector
     *
     * @param position string with the position in letters and orientation (H or V)
     *
     * @return vector with the result of the reading: true or false to indicate if reading was successful, line (int), column (int) and orientation (true or false)
     */
    private Vector<Object> readShipPosition(String position) {
        int dimV = game.getDimV();
        int dimH = game.getDimH();
        Vector<Object> result = new Vector<>();
        if (position.length() >= 3) {
            int line = position.charAt(0) - 65;
            int column = position.charAt(1) - 97;
            boolean orientation = (position.charAt(2) == 'H');
            if (line < dimV && column < dimH) result.add(true);
            else result.add(false);
            result.add(line);
            result.add(column);
            result.add(orientation);
        }
        else result.add(false);
        return result;
    }

    /**
     * Tries to change a given player's ship position
     *
     * @param scan scanner used to get input from the console
     * @param playerNumber number of the player whose whip will be repositioned
     */
    private void changeShipPosition(Scanner scan, int playerNumber) {
        char maxLine = (char)(game.getDimV() + 65);
        char maxColumn = (char)(game.getDimH() + 97);
        int oldLin, oldCol, newLin, newCol;
        Position oldPosition = null;
        Position newPosition = null;
        boolean validOldPos;
        boolean validNewPos = false;
        boolean newOri = true;
        do {
            System.out.println("");
            System.out.print("Current position: LINE (A.." + maxLine + ") COLUMN (a.." + maxColumn + ") ORIENTATION (H V)? ");
            String oldPos = scan.nextLine();
            Vector<Object> oldShipPosition = readShipPosition(oldPos);
            validOldPos = (boolean)(oldShipPosition.get(0));
            if (validOldPos) {
                oldLin = (int) (oldShipPosition.get(1));
                oldCol = (int) (oldShipPosition.get(2));
                oldPosition = Position.Instance(oldLin, oldCol);
                System.out.print("New position: LINE (A.." + maxLine + ") COLUMN (a.." + maxColumn + ") ORIENTATION (H V)? ");
                String newPos = scan.nextLine();
                Vector<Object> newShipPosition = readShipPosition(newPos);
                validNewPos = (boolean) (newShipPosition.get(0));
                if (validNewPos) {
                    newLin = (int) (newShipPosition.get(1));
                    newCol = (int) (newShipPosition.get(2));
                    newPosition = Position.Instance(newLin, newCol);
                    newOri = (boolean) (newShipPosition.get(3));
                }
            }
        }
        while (!validOldPos || !validNewPos || !game.changeShipPosition(playerNumber, oldPosition, newPosition, newOri));
    }

    /**
     * Chooses type of placement for each players ships
     *
     * @param scan scanner used to get input from the console
     * @param random random number generator for when automatic placement is chosen
     */
    private void placeShips(Scanner scan, Random random) {
        Utils.clearScreen();
        choosePlacing(scan, random, game.getPlayer1().getName(), 1);
        if (game.getNumberPlayers() == 1) {
            game.autoPlaceShips(random, 2);
        }
        else {
            Utils.clearScreen();
            choosePlacing(scan, random, game.getPlayer2().getName(), 2);
        }
        Utils.clearScreen();
    }

}
