package cli;

import javafx.geometry.Pos;
import logic.Game;
import logic.Player;
import logic.Position;

import java.util.*;

public class Play {

    private Game game;

    private Vector<Position> computerBombs;

    Play(Scanner scan) {
        game = Game.Instance();
        computerBombs = new Vector<>();
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
        if (game.getNumberPlayers() == 1) {
            int dimV = game.getDimV();
            int dimH = game.getDimH();
            for (int i = 0; i < dimV; i++) {
                for (int j = 0; j < dimH; j++) {
                    Position position = Position.Instance(i, j);
                    computerBombs.add(position);
                }
            }
            Collections.shuffle(computerBombs);
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
                    else computerTurn(secondPlayer, firstPlayer);
                }
                else {
                    computerTurn(firstPlayer, secondPlayer);
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

    private void computerTurn(Player playerAttack, Player playerDefend) {
        Position position = computerBombs.remove(0);
        boolean attackResult = game.attackPosition(playerAttack, playerDefend, position);
        if (game.getState() == 0 && attackResult) {
            Vector<Position> neighbors = position.getNeighbors(game.getDimV(), game.getDimH());
            for (Position neighbor : neighbors) {
                int neighborIndex = computerBombs.indexOf(neighbor);
                if (neighborIndex >= 0) {
                    computerBombs.remove(neighborIndex);
                    computerBombs.add(0, neighbor);
                }
            }
            computerTurn(playerAttack, playerDefend);
        }
    }

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
