package cli;

import logic.Player;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Place extends Start {

    public Place(Scanner scan, Random random, String name1Def, String name2Def, String configDef, String file1Def, String file2Def) {
        super(scan, name1Def, name2Def, configDef, file1Def, file2Def);
        placeShips(scan, random);
    }

    private void choosePlacing(Scanner scan, Random random, String playerName, int playerNumber) {
        char answer;
        System.out.print(playerName + ": place ships automatically (y/n)? ");
        answer = scan.nextLine().charAt(0);
        if (answer == 'y') {
            game.autoPlaceShips(random, playerNumber);
            System.out.println(game.printPlayer(playerNumber));
            char reposition;
            do {
                System.out.print(playerName + ": reposition ships (y/n)? ");
                reposition = scan.nextLine().charAt(0);
                if (reposition == 'y') changeShipPosition(scan, playerNumber);
            }
            while (reposition == 'y');
        }
        else placePlayerShips(scan, playerName, playerNumber);
    }

    private void placePlayerShips(Scanner scan, String playerName, int playerNumber) {
        char maxLine = (char)(game.getDimV() + 65);
        char maxColumn = (char)(game.getDimH() + 97);
        System.out.println(playerName + " - distribute fleet:");
        System.out.println("");
        int line, column;
        boolean orientation, validPosition;
        for (int i = 0; i < game.getNumberShips(); i++) {
            do {
                System.out.println(game.printPlayer(playerNumber));
                System.out.println(game.printShip(playerNumber, i));
                System.out.print("LINE (A.." + maxLine + ") COLUMN (a.." + maxColumn + ") ORIENTATION (H V)? ");
                String pos = scan.nextLine();
                Vector shipPosition = readShipPosition(pos);
                validPosition = (boolean)(shipPosition.get(0));
                line = (int)(shipPosition.get(1));
                column = (int)(shipPosition.get(2));
                orientation = (boolean)(shipPosition.get(3));
            } while (!validPosition || !game.manualPlaceShip(playerNumber, i, line, column, orientation));
            game.placeShipBoard(playerNumber, i);
        }
        System.out.println(game.printPlayer(playerNumber));
        char reposition;
        do {
            System.out.print(playerName + ": reposition ships (y/n)? ");
            reposition = scan.nextLine().charAt(0);
            if (reposition == 'y') changeShipPosition(scan, playerNumber);
        } while (reposition == 'y');
    }

    private Vector readShipPosition(String position) {
        int dimV = game.getDimV();
        int dimH = game.getDimH();
        Vector result = new Vector();
        if (position.length() >= 3) {
            int line = (int)(position.charAt(0) - 65);
            int column = (int)(position.charAt(1) - 97);
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

    private void changeShipPosition(Scanner scan, int playerNumber) {
        char maxLine = (char)(game.getDimV() + 65);
        char maxColumn = (char)(game.getDimH() + 97);
        int oldLin, oldCol, newLin, newCol;
        boolean validOldPos, validNewPos, oldOri, newOri;
        do {
            System.out.println("");
            System.out.println("Current position: LINE (A.." + maxLine + ") COLUMN (a.." + maxColumn + ") ORIENTATION (H V)? ");
            String oldPos = scan.nextLine();
            Vector oldShipPosition = readShipPosition(oldPos);
            validOldPos = (boolean)(oldShipPosition.get(0));
            oldLin = (int)(oldShipPosition.get(1));
            oldCol = (int)(oldShipPosition.get(2));
            oldOri = (boolean)(oldShipPosition.get(3));
            System.out.println("New position: LINE (A.." + maxLine + ") COLUMN (a.." + maxColumn + ") ORIENTATION (H V)? ");
            String newPos = scan.nextLine();
            Vector newShipPosition = readShipPosition(newPos);
            validNewPos = (boolean)(newShipPosition.get(0));
            newLin = (int)(newShipPosition.get(1));
            newCol = (int)(newShipPosition.get(2));
            newOri = (boolean)(newShipPosition.get(3));
        }
        while (!validOldPos || !validNewPos || !game.changeShipPosition(playerNumber, oldLin, oldCol, oldOri, newLin, newCol, newOri));
        System.out.println(game.printPlayer(playerNumber));
    }

    private void placeShips(Scanner scan, Random random) {
        clearScreen();
        choosePlacing(scan, random, getPlayer1Name(), 1);
        game.writeShips(1, getPlayer1File());
        if (getNumberPlayers() == 1) {
            game.autoPlaceShips(random, 2);
        }
        else {
            clearScreen();
            choosePlacing(scan, random, getPlayer2Name(), 2);
        }
        game.writeShips(2, getPlayer2File());
        System.out.println("");
    }

    public void clearScreen() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) Runtime.getRuntime().exec("cls");
            else System.out.print("\033c");
        } catch (Exception e) {
            for (int i = 0; i < 100; i++) System.out.println("");
        }
    }

}
