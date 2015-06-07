package cli;

import logic.Game;
import logic.Player;
import logic.Position;

import java.util.*;

/**
 * Class Play used to play the game after it has been configured or loaded
 */
public class Play {

    /** Game that will be played */
    private Game game;

    /**
     * Instantiates a new play
     *
     * @param scan scanner used to get input from the console
     */
    Play(Scanner scan) {
        game = Game.Instance();
        Player firstPlayer;
        Player secondPlayer;
        boolean exit = false;
        if (game.getStartingPlayer() == 1) {
            firstPlayer = game.getPlayer1();
            secondPlayer = game.getPlayer2();
        }
        else {
            firstPlayer = game.getPlayer2();
            secondPlayer = game.getPlayer1();
        }
        while (game.getState() == 0 && !exit) {
            if (game.getNumberPlayers() == 2) {
                if (!playerTurn(firstPlayer, secondPlayer, scan)) exit = true;
                else if (game.getState() != 0) break;
                else if (!playerTurn(secondPlayer, firstPlayer, scan)) exit = true;
            }
            else {
                if (game.getStartingPlayer() == 1) {
                    if (!playerTurn(firstPlayer, secondPlayer, scan)) exit = true;
                    else if (game.getState() != 0) break;
                    else game.computerTurn(secondPlayer, firstPlayer);
                }
                else {
                    game.computerTurn(firstPlayer, secondPlayer);
                    if (game.getState() != 0) break;
                    else if (!playerTurn(secondPlayer, firstPlayer, scan)) exit = true;
                }
            }
        }
        System.out.println();
        if (exit) System.out.println("Goodbye!");
        else {
            if (game.getNumberPlayers() == 2) {
                if (game.getState() == 1) System.out.print(game.getPlayer1().getName());
                else System.out.print(game.getPlayer1().getName());
                System.out.println(": Congratulations, you win!");
            }
            else {
                if (game.getState() == 1) System.out.println(game.getPlayer1().getName() + ": Congratulations, you win!");
                else System.out.println(game.getPlayer1().getName() + ": Sorry, you lose!");
            }
        }
    }

    /**
     * Plays a given players turn
     *
     * @param playerAttack player whose turn it is
     * @param playerDefend other player
     * @param scan scanner used to get input from the console
     *
     * @return boolean false if the player chooses to end the game, false otherwise
     */
    private boolean playerTurn(Player playerAttack, Player playerDefend, Scanner scan) {
        int dimV = game.getDimV();
        int dimH = game.getDimH();
        char maxLine = (char)(dimV + 64);
        char maxColumn = (char)(dimH + 96);
        System.out.print(playerAttack.getName() + ": it's your turn. Press enter to continue, S to save, E to save and exit. ");
        String answer = scan.nextLine();
        if (Objects.equals(answer, "S")) {
            game.saveGame(playerAttack.getNumber());
        }
        else if (Objects.equals(answer,"E")) {
            game.saveGame(playerAttack.getNumber());
            return false;
        }
        Boolean validPosition;
        do {
            Utils.clearScreen();
            System.out.println(game.printPlayer(playerAttack, playerDefend));
            System.out.println();
            System.out.println(playerAttack.getName() + ": choose target.");
            System.out.print("LINE (A.." + maxLine + ") COLUMN (a.." + maxColumn + ")? ");
            String pos = scan.nextLine();
            Vector<Object> bombPosition = readBombPosition(pos);
            validPosition = (boolean)(bombPosition.get(0));
            if (validPosition) {
                int line = (int)(bombPosition.get(1));
                int column = (int)(bombPosition.get(2));
                Position position = Position.Instance(line, column);
                boolean attackResult = game.attackPosition(playerAttack, playerDefend, position);
                if (!attackResult) printAttackResult(playerAttack, playerDefend, position, "failed", scan);
                else {
                    printAttackResult(playerAttack, playerDefend, position, "succeeded", scan);
                    if (game.getState() == 0 && !playerTurn(playerAttack, playerDefend, scan)) return false;
                }
            }
        } while (!validPosition);
        return true;
    }

    /**
     * Reads the position of a bomb from a string
     *
     * @param position string containing the bomb position in letters
     *
     * @return vector with true or false indicating if the reading was successful, the line (number) and the column of the position
     */
    private Vector<Object> readBombPosition(String position) {
        int dimV = game.getDimV();
        int dimH = game.getDimH();
        Vector<Object> result = new Vector<>();
        if (position.length() >= 2) {
            int line = position.charAt(0) - 65;
            int column = position.charAt(1) - 97;
            if (line < dimV && column < dimH) result.add(true);
            else result.add(false);
            result.add(line);
            result.add(column);
        }
        else result.add(false);
        return result;
    }

    /**
     * After an attack, prints the result in the console
     *
     * @param playerAttack player whose attack it was
     * @param playerDefend other player
     * @param position position that was attacked
     * @param message message indicating success or failure of the attack
     * @param scan scanner used to get input from the console
     */
    void printAttackResult(Player playerAttack, Player playerDefend, Position position, String message, Scanner scan) {
        Utils.clearScreen();
        System.out.println(game.printPlayer(playerAttack, playerDefend));
        System.out.print((char)(position.getLine() + 65));
        System.out.print((char)(position.getColumn() + 97));
        System.out.print(" - Attack " + message + ". Press enter to continue. ");
        scan.nextLine();
        Utils.clearScreen();
    }

}
