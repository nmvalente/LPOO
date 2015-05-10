package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * Class Game - contains all elements necessary for the game
 */
public class Game {

    /** first player - always human **/
    private Player player1;

    /** second player - may be human or not **/
    private Player player2;

    /** state of the game - 0 if not finished, 1 if player 1 wins, 2 if player 2 wins **/
    private int state;

    /**
     * Instantiates a new game
     *
     * @param namePlayer1 - name of the first player
     * @param namePlayer2 - name of the second player
     **/
    public Game(String namePlayer1, String namePlayer2) {
        player1 = new Player(namePlayer1);
        player2 = new Player(namePlayer2);
        state = 0;
    }

    /**
     * Returns the first player
     *
     * @return Player - first player
     **/
    private Player getPlayer1() {
        return player1;
    }

    /**
     * Returns the second player
     *
     * @return Player - second player
     **/
    private Player getPlayer2() {
        return player2;
    }

    /**
     * Returns the game state
     *
     * @return int - 0 if not finished, 1 if player 1 wins, 2 if player 2 wins
     **/
    private int getState() {
        return state;
    }

    public int getDimV() {
        return player1.getBoard().getDimV();
    }

    public int getDimH() {
        return player1.getBoard().getDimH();
    }

    public int getNumberShips() {
        return player1.getShips().size();
    }

    /**
     * Reads the game specifications (board size and properties of the ships from a configuration file
     *
     * @param file - path to the configuration file
     **/
    public void readConfig(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String[] dimensions = line.split(" - ")[1].split(" x ");
        int dimV = Integer.parseInt(dimensions[0]);
        int dimH = Integer.parseInt(dimensions[1]);
        Board boardPlayer1 = new Board(dimV, dimH);
        Board boardPlayer2 = new Board(dimV, dimH);
        player1.setBoard(boardPlayer1);
        player2.setBoard(boardPlayer2);
        while ((line = reader.readLine()) != null) {
            String[] shipSpecs = line.split(" - ");
            for (int i = 0; i < Integer.parseInt(shipSpecs[3]); i++) {
                Ship shipPlayer1 = new Ship(shipSpecs[0], Integer.parseInt(shipSpecs[2]), shipSpecs[1].charAt(0));
                player1.addShip(shipPlayer1);
                Ship shipPlayer2 = new Ship(shipSpecs[0], Integer.parseInt(shipSpecs[2]), shipSpecs[1].charAt(0));
                player2.addShip(shipPlayer2);
            }
        }
    }

    /**
     * Loads the base game specifications when reading the configuration file fails
     **/
    public void loadConfig() {
        Board boardPlayer1 = new Board(10, 10);
        Board boardPlayer2 = new Board(10, 10);
        player1.setBoard(boardPlayer1);
        player2.setBoard(boardPlayer2);
        Ship ship = new Ship("Aircraft carrier", 5, 'A');
        player1.addShip(ship);
        ship = new Ship("Aircraft carrier", 5, 'A');
        player2.addShip(ship);
        ship = new Ship("Battleship", 4, 'B');
        player1.addShip(ship);
        ship = new Ship("Battleship", 4, 'B');
        player2.addShip(ship);
        ship = new Ship("Cruiser", 3, 'C');
        player1.addShip(ship);
        ship = new Ship("Cruiser", 3, 'C');
        player2.addShip(ship);
        for (int i = 0; i < 2; i++) {
            ship = new Ship("Destroyer", 2, 'D');
            player1.addShip(ship);
            ship = new Ship("Destroyer", 2, 'D');
            player2.addShip(ship);
            ship = new Ship("Submarine", 1, 'S');
            player1.addShip(ship);
            ship = new Ship("Submarine", 1, 'S');
            player2.addShip(ship);
        }
    }

    /**
     * Validates the game specifications: board size, space occupied by the ships
     * and different symbols for different ship types
     *
     * @return int:
     * 0 - valid configuration
     * 1 - board to big (more than 26 cells horizontally or vertically)
     * 2 - board to small (space occupied by all ships cells > 2 * board cells)
     * 3 - different type ships with the same symbol
     **/
    public int validateConfig() {
        Board board = player1.getBoard();
        Vector<Ship> ships = player1.getShips();
        if (board.getDimV() > 26 || board.getDimH() > 26) return 1; /* board too big */
        int numberBoardCells = board.getDimV() * board.getDimH();
        int numberShipCells = 0;
        for (int i = 0; i < ships.size(); i++) {
            numberShipCells += (ships.get(i)).getDim();
            for (int j = 0; j < i; j++) {
                if (!(ships.get(i)).getType().equals((ships.get(j)).getType())
                && (ships.get(i)).getSymbol() == (ships.get(j)).getSymbol()) return 3; /* repeated symbols */
            }
        }
        if (2 * numberShipCells > numberBoardCells) return 2; /* board to small for the ships */
        return 0;
    }

    public void startGame() {
        int dimV = (player1.getBoard()).getDimV();
        int dimH = (player1.getBoard()).getDimH();
        Board opponent1 = new Board(dimV, dimH);
        Board opponent2 = new Board(dimV, dimH);
        player1.setOpponent(opponent1);
        player2.setOpponent(opponent2);
    }

    public void computeState() {
        if (player1.getLiveShips() == 0) state = 2;
        else if (player2.getLiveShips() == 0) state = 1;
        else state = 0;
    }

    public String printPlayer(int playerNumber) {
        if (playerNumber == 1) return printPlayer(player1, player2);
        else return printPlayer(player2, player1);
    }

    private String printPlayer(Player playerA, Player playerB) {
        String out = "";
        Board opponent = playerA.getOpponent();
        if (opponent != null) {
            out += playerA.getName() + ":";
            for (int i = 0; i < 2 * playerA.getBoard().getDimH() + 8 - (playerA.getName()).length(); i++) {
                out += " ";
            }
            out += playerB.getName() + ":\n";
        }
        out += playerA;
        return out;
    }

    public void autoPlaceShips(Random random, int playerNumber) {
        if (playerNumber == 1) autoPlaceShips(random, player1);
        else autoPlaceShips(random, player2);
    }

    private void autoPlaceShips(Random random, Player player) {
        player.autoPlaceShips(random);
        player.placeShipsBoard();
    }

    public boolean changeShipPosition(int playerNumber, int oldLin, int oldCol, boolean oldOri, int newLin, int newCol, boolean newOri) {
        if (playerNumber == 1) return player1.changeShipPosition(new Position(oldLin, oldCol), oldOri, new Position(newLin, newCol), newOri);
        else return player2.changeShipPosition(new Position(oldLin, oldCol), oldOri, new Position(newLin, newCol), newOri);
    }

    public String printShip(int playerNumber, int shipIndex) {
        if (playerNumber == 1) return printShip(player1, shipIndex);
        else return printShip(player2, shipIndex);
    }

    private String printShip(Player player, int shipIndex) {
        String out = "";
        Ship ship = player.getShips().get(shipIndex);
        out += ship.getSymbol() + " - " + ship.getType() + ". Size = " + ship.getDim();
        return out;
    }

    public boolean manualPlaceShip(int playerNumber, int shipIndex, int line, int column, boolean orientation) {
        if (playerNumber == 1) return player1.manualPlaceShip(shipIndex, new Position(line, column), orientation);
        else return player2.manualPlaceShip(shipIndex, new Position(line, column), orientation);
    }

    public void placeShipBoard(int playerNumber, int shipIndex) {
        if (playerNumber == 1) player1.placeShipBoard(shipIndex);
        else player2.placeShipBoard(shipIndex);
    }

    public void writeShips(int playerNumber, String file) {
        if (playerNumber == 1) player1.writeShips(file);
        else player2.writeShips(file);
    }

}
