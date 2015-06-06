package cli;

import logic.Game;
import logic.Player;
import logic.Position;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Play {

    private Game game;

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
            if (!playerTurn(firstPlayer, secondPlayer, scan)) exit = true;
            else if (game.getState() != 0) break;
            else if (!playerTurn(secondPlayer, firstPlayer, scan)) exit = true;
        }
        System.out.println();
        if (exit) System.out.println("Goodbye!");
        else {
            if (game.getState() == 1) System.out.print(game.getPlayer1().getName());
            else System.out.print(game.getPlayer1().getName());
            System.out.println(": Congratulations, you win!");
        }
    }

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
        Position position;
        int line;
        int column;
        String pos;
        Vector bombPosition;
        Boolean validPosition;
        do {
            Utils.clearScreen();
            System.out.println(game.printPlayer(playerAttack, playerDefend));
            System.out.println();
            System.out.println(playerAttack.getName() + ": choose target.");
            System.out.print("LINE (A.." + maxLine + ") COLUMN (a.." + maxColumn + ")? ");
            pos = scan.nextLine();
            bombPosition = readBombPosition(pos);
            validPosition = (boolean)(bombPosition.get(0));
            if (validPosition) {
                line = (int)(bombPosition.get(1));
                column = (int)(bombPosition.get(2));
                position = Position.Instance(line, column);
                int index = playerDefend.getBoard().getPosition(position);
                if (index == -1) {
                    playerDefend.defendFailure(position);
                    playerAttack.attackFailure(position);
                    printAttackResult(playerAttack, playerDefend, position, "failed", scan);
                }
                else if (index < -1) {
                    printAttackResult(playerAttack, playerDefend, position, "failed", scan);
                }
                else {
                    playerDefend.defendSuccess(position);
                    playerAttack.attackSuccess(position);
                    printAttackResult(playerAttack, playerDefend, position, "succeeded", scan);
                    game.computeState();
                    if (!playerTurn(playerAttack, playerDefend, scan)) return false;
                }
            }
        } while (!validPosition);
        return true;
    }

    private Vector readBombPosition(String position) {
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
