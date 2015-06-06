package logic;

import java.io.*;
import java.util.Random;
import java.util.Vector;

/**
 * Class Game - contains all elements necessary for the game
 */
public class Game {

    private static Game instance = null;

    private int numberPlayers;

    /**
     * player 1 - always human
     **/
    private Player player1;

    /**
     * player 2 - may be human or not
     **/
    private Player player2;

    private String configFile;

    private String player1File;

    private String player2File;

    /**
     * state of the game - 0 if not finished, 1 if player 1 wins, 2 if player 2 wins
     **/
    private int state;

    private int startingPlayer;

    /**
     * Instantiates a new game
     **/
    private Game() {
        numberPlayers = 1;
        player1 = new Player(1, "");
        player2 = new Player(2, "");
        configFile = "";
        player1File = "";
        player2File = "";
        state = 0;
    }

    public static Game Instance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }

    /**
     * Returns the first player
     *
     * @return Player - first player
     **/
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Returns the second player
     *
     * @return Player - second player
     **/
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Returns the game state
     *
     * @return int - 0 if not finished, 1 if player 1 wins, 2 if player 2 wins
     **/
    public int getState() {
        return state;
    }

    public int getDimV() {
        return player1.getBoard().getDimV();
    }

    public int getDimH() {
        return player2.getBoard().getDimH();
    }

    public int getNumberShips() {
        return player1.getShips().size();
    }

    /**
     * Reads the game specifications (board size and properties of the ships from the configuration file
     **/
    public void readConfig() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
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
            for (int i = 0; i < Integer.parseInt(shipSpecs[0]); i++) {
                Ship shipPlayer1 = new Ship(shipSpecs[1], Integer.parseInt(shipSpecs[2]), shipSpecs[3].charAt(0));
                player1.addShip(shipPlayer1);
                Ship shipPlayer2 = new Ship(shipSpecs[1], Integer.parseInt(shipSpecs[2]), shipSpecs[3].charAt(0));
                player2.addShip(shipPlayer2);
            }
        }
        reader.close();
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
        }
        for (int i = 0; i < 2; i++) {
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

    public String printPlayer(Player playerA, Player playerB) {
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

    public boolean changeShipPosition(int playerNumber, Position oldPos, Position newPos, boolean newOri) {
        if (playerNumber == 1) return player1.changeShipPosition(oldPos, newPos, newOri);
        else return player2.changeShipPosition(oldPos, newPos, newOri);
    }

    public String printShip(int playerNumber, int shipIndex) {
        if (playerNumber == 1) return printShip(player1, shipIndex);
        else return printShip(player2, shipIndex);
    }

    private String printShip(Player player, int shipIndex) {
        String out = "";
        Ship ship = player.getShips().get(shipIndex);
        out += ship.getSymbol() + " - " + ship.getType() + ". Size = " + ship.getDim() + ".";
        return out;
    }

    public boolean manualPlaceShip(int playerNumber, int shipIndex, Position position, boolean orientation) {
        if (playerNumber == 1) return player1.manualPlaceShip(shipIndex, position, orientation);
        else return player2.manualPlaceShip(shipIndex, position, orientation);
    }

    public void placeShipBoard(int playerNumber, int shipIndex) {
        if (playerNumber == 1) player1.placeShipBoard(shipIndex);
        else player2.placeShipBoard(shipIndex);
    }

    public void removeShipBoard(int playerNumber, int shipIndex) {
        if (playerNumber == 1) player1.removeShipBoard(shipIndex);
        else player2.removeShipBoard(shipIndex);
    }

    public void saveGame(int playerNumber) {
        try {
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(player1File), "utf-8"));
            player1.writeShips(writer1);
            player1.writeOpponent(writer1);
            if (playerNumber == 1) writer1.write("\n" + "true");
            else writer1.write("\n" + "false");
            writer1.close();
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(player2File), "utf-8"));
            player2.writeShips(writer2);
            player2.writeOpponent(writer2);
            if (playerNumber == 2) writer2.write("\n" + "true");
            else writer2.write("\n" + "false");
            writer2.close();
        } catch (IOException ignored) {}
    }

    public void loadGame() {
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(player1File));
            player1.readShips(reader1);
            if (player1.readOpponent(reader1)) startingPlayer = 1;
            else startingPlayer = 2;
            BufferedReader reader2 = new BufferedReader(new FileReader(player2File));
            player2.readShips(reader2);
            player2.readOpponent(reader2);
            player1.getBombResults(player2);
            player2.getBombResults(player1);
        } catch (IOException ignored) {}
    }

    public void setNumberPlayers(int newNumberPlayers) {
        numberPlayers = newNumberPlayers;
    }

    public void setPlayer1Name(String player1Name) {
        player1.setName(player1Name);
    }

    public void setPlayer2Name(String player2Name) {
        player2.setName(player2Name);
    }

    public void setConfigFile(String newConfigFile) {
        configFile = newConfigFile;
    }

    public void setPlayer1File(String newPlayer1File) {
        player1File = newPlayer1File;
    }

    public void setPlayer2File(String newPlayer2File) {
        player2File = newPlayer2File;
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(int newStartingPlayer) {
        startingPlayer = newStartingPlayer;
    }

    public boolean attackPosition(Player playerAttack, Player playerDefend, Position position) {
        int index = playerDefend.getBoard().getPosition(position);
        if (index == -1) {
            playerDefend.defendFailure(position);
            playerAttack.attackFailure(position);
            return false;
        }
        else if (index < -1) {
            return false;
        }
        else {
            playerDefend.defendSuccess(position);
            playerAttack.attackSuccess(position);
            computeState();
            return true;
        }
    }

    public String getPlayer1File() {
        return player1File;
    }

    public String getPlayer2File() {
        return player2File;
    }

    public void resetConfig() {
        player1.setBoard(null);
        player2.setBoard(null);
        player1.clearShips();
        player2.clearShips();
    }
}