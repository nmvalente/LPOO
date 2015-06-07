package logic;

import cli.Play;

import java.io.*;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Class Game contains all elements necessary for the game
 */
public class Game {

    /** unique instance of a game */
    private static Game instance = null;

    /** number of human players of the game (1 or 2) */
    private int numberPlayers;

    /** player 1 - always human */
    private Player player1;

    /** player 2 - may be human or not */
    private Player player2;

    /** path to the configuration file, with information on board dimension and types of ships */
    private String configFile;

    /** path to the save file of player 1, where all relevant player info is stored */
    private String player1File;

    /** path to the save file of player 1, where all relevant player info is stored */
    private String player2File;

    /** state of the game - 0 if not finished, 1 if player 1 wins, 2 if player 2 wins */
    private int state;

    /** randomly determined player that gets the first turn (1 or 2) */
    private int startingPlayer;

    /** vector will all board positions randomized for the computer to play */
    private Vector<Position> computerBombs;

    /**
     * Instantiates a new game
     */
    private Game() {
        numberPlayers = 1;
        player1 = new Player(1, "");
        player2 = new Player(2, "");
        configFile = "";
        player1File = "";
        player2File = "";
        state = 0;
        computerBombs = new Vector<>();
    }

    /**
     * Returns the unique game instance
     *
     * @return Game unique instance of the game
     */
    public static Game Instance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Returns the number of players
     *
     * @return int number of human players
     */
    public int getNumberPlayers() {
        return numberPlayers;
    }

    /**
     * Returns the first player
     *
     * @return Player first player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Returns the second player
     *
     * @return Player second player
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Returns the game state
     *
     * @return int 0 if not finished, 1 if player 1 wins, 2 if player 2 wins
     */
    public int getState() {
        return state;
    }

    /**
     * Returns the number of lines of the game board (it's the same for both players)
     *
     * @return int with the number of lines of the game board
     */
    public int getDimV() {
        return player1.getBoard().getDimV();
    }

    /**
     * Returns the number of columns of the game board (it's the same for both players)
     *
     * @return int with the number of columns of the game board
     */
    public int getDimH() {
        return player2.getBoard().getDimH();
    }

    /**
     * Returns the number of ships for each player (it's the same for both players)
     *
     * @return int with the number of ships of each player
     */
    public int getNumberShips() {
        return player1.getShips().size();
    }

    /**
     * Reads the game specifications (board size and properties of the ships) from the configuration
     *
     * @throws IOException if there is some problem with the configuration file
     */
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
     */
    public void loadConfig() {
        resetConfig();
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
     * 2 - board to small (space occupied by all ships cells greater than double of board cells)
     * 3 - different type ships with the same symbol
     */
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

    /**
     * Initiates the board for the opponents for each player, where bomb results will be registered, and
     * if there is a single player, adds bomb positions to the attack vector for the AI
     */
    public void startGame() {
        int dimV = getDimV();
        int dimH = getDimH();
        if (numberPlayers == 1) {
            for (int i = 0; i < dimV; i++) {
                for (int j = 0; j < dimH; j++) {
                    Position position = Position.Instance(i, j);
                    computerBombs.add(position);
                }
            }
            Collections.shuffle(computerBombs);
        }
        Board opponent1 = new Board(dimV, dimH);
        Board opponent2 = new Board(dimV, dimH);
        player1.setOpponent(opponent1);
        player2.setOpponent(opponent2);
    }

    /**
     * Computes the state of the game, that is, if some player has won and which one
     */
    public void computeState() {
        if (player1.getLiveShips() == 0) state = 2;
        else if (player2.getLiveShips() == 0) state = 1;
        else state = 0;
    }

    /**
     * Prints the player with the given number
     *
     * @param playerNumber number of the player we want to print (1 or 2)
     *
     * @return string with the result of the print
     */
    public String printPlayer(int playerNumber) {
        if (playerNumber == 1) return printPlayer(player1, player2);
        else return printPlayer(player2, player1);
    }

    /**
     * Prints a given player as well as the opponents board as viewed by the player
     *
     * @param playerA player we want to print
     * @param playerB opponent of the player we want to print
     *
     * @return string with the players board and their idea of the opponents board
     */
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

    /**
     * Randomly determines the position and orientation of a given player's ships
     *
     * @param random random number generator
     * @param playerNumber number of the player whose ships we want to place
     */
    public void autoPlaceShips(Random random, int playerNumber) {
        if (playerNumber == 1) autoPlaceShips(random, player1);
        else autoPlaceShips(random, player2);
    }

    /**
     * Randomly determines the position and orientation of a given player's ships
     *
     * @param random random number generator
     * @param player player whose ships we want to place
     */
    private void autoPlaceShips(Random random, Player player) {
        player.autoPlaceShips(random);
        player.placeShipsBoard();
    }

    /**
     * Tries to change the position of a ship of a given player
     *
     * @param playerNumber number of the player whose ship we want to move
     * @param oldPos position of the ship we want to move
     * @param newPos position where we want to place the ship
     * @param newOri orientation we wish for the ship
     *
     * @return true if the change is successful, false otherwise
     */
    public boolean changeShipPosition(int playerNumber, Position oldPos, Position newPos, boolean newOri) {
        if (playerNumber == 1) return player1.changeShipPosition(oldPos, newPos, newOri);
        else return player2.changeShipPosition(oldPos, newPos, newOri);
    }

    /**
     * Prints the symbol, name and size of a given ship
     *
     * @param playerNumber number of the player whose ship we want to print
     * @param shipIndex index of the ship in the vector of player's ships
     *
     * @return string with the result of ship's information
     */
    public String printShip(int playerNumber, int shipIndex) {
        if (playerNumber == 1) return printShip(player1, shipIndex);
        else return printShip(player2, shipIndex);
    }

    /**
     * Prints the symbol, name and size of a given ship
     *
     * @param player player whose ship we want to print
     * @param shipIndex index of the ship in the vector of player's ships
     *
     * @return string with the result of ship's information
     */
    private String printShip(Player player, int shipIndex) {
        String out = "";
        Ship ship = player.getShips().get(shipIndex);
        out += ship.getSymbol() + " - " + ship.getType() + ". Size = " + ship.getDim() + ".";
        return out;
    }

    /**
     * Tries to determine a given player's ship position and orientation
     *
     * @param playerNumber number of the player whose ship we want to place
     * @param shipIndex index of the ship in the player's vector
     * @param position position where we want to place the ship
     * @param orientation orientation we want for the ship
     *
     * @return true if it is possible to place the ship in the given position and orientation, false otherwise
     */
    public boolean manualPlaceShip(int playerNumber, int shipIndex, Position position, boolean orientation) {
        if (playerNumber == 1) return player1.manualPlaceShip(shipIndex, position, orientation);
        else return player2.manualPlaceShip(shipIndex, position, orientation);
    }

    /**
     * Places a given player's ship on the board
     *
     * @param playerNumber number of the player whose ship we want to place
     * @param shipIndex index of the ship in the player's vector
     */
    public void placeShipBoard(int playerNumber, int shipIndex) {
        if (playerNumber == 1) player1.placeShipBoard(shipIndex);
        else player2.placeShipBoard(shipIndex);
    }

    /**
     * Removes a given player's ship from the board
     *
     * @param playerNumber number of the player whose ship we want to place
     * @param shipIndex index of the ship in the player's vector
     */
    public void removeShipBoard(int playerNumber, int shipIndex) {
        if (playerNumber == 1) player1.removeShipBoard(shipIndex);
        else player2.removeShipBoard(shipIndex);
    }

    /**
     * Saves both players ships and turns in the respective files as well as information on the next player
     *
     * @param playerNumber number of the player that is playing next
     */
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

    /**
     * Loads a game from the player's files
     */
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

    /**
     * Sets the number of human players of the game
     *
     * @param newNumberPlayers number of intended players (1 or 2)
     */
    public void setNumberPlayers(int newNumberPlayers) {
        numberPlayers = newNumberPlayers;
    }

    /**
     * Sets the name of player 1
     *
     * @param player1Name intended name for player 1
     */
    public void setPlayer1Name(String player1Name) {
        player1.setName(player1Name);
    }

    /**
     * Sets the name of player 2
     *
     * @param player2Name intended name for player 2
     */
    public void setPlayer2Name(String player2Name) {
        player2.setName(player2Name);
    }

    /**
     * Sets the configuration file for the game
     *
     * @param newConfigFile path to the configuration file
     */
    public void setConfigFile(String newConfigFile) {
        configFile = newConfigFile;
    }

    /**
     * Sets the save file for player 1
     *
     * @param newPlayer1File path to the save file for player 1
     */
    public void setPlayer1File(String newPlayer1File) {
        player1File = newPlayer1File;
    }

    /**
     * Sets the save file for player 2
     *
     * @param newPlayer2File path to the save file for player 1
     */
    public void setPlayer2File(String newPlayer2File) {
        player2File = newPlayer2File;
    }

    /**
     * Returns the number of the player that gets the first turn
     *
     * @return 1 if player 1 has the first turn, 2 otherwise
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }

    /**
     * Sets the number of the starting player
     *
     * @param newStartingPlayer 1 if player 1 has the first turn, 2 otherwise
     */
    public void setStartingPlayer(int newStartingPlayer) {
        startingPlayer = newStartingPlayer;
    }

    /**
     * Attacks a given position, considering the attacking and defending players
     *
     * @param playerAttack player that is attacking
     * @param playerDefend player that is defending
     * @param position position that is being attacked
     *
     * @return true if the attack is successful, false otherwise
     */
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

    /**
     * Returns the path to the save file for player 1
     *
     * @return path to the save file of player 1
     */
    public String getPlayer1File() {
        return player1File;
    }

    /**
     * Returns the path to the save file for player 2
     *
     * @return path to the save file of player 2
     */
    public String getPlayer2File() {
        return player2File;
    }

    /**
     * Resets the game configuration, removing the boards and ships for both players
     */
    public void resetConfig() {
        player1.setBoard(null);
        player2.setBoard(null);
        player1.clearShips();
        player2.clearShips();
    }

    /**
     * Plays a computer turn, attacking the next random cell, if the attack is successful, attacks the neighbors next
     *
     * @param playerAttack computer player whose turn it is
     * @param playerDefend other player
     */
    public void computerTurn(Player playerAttack, Player playerDefend) {
        Position position = computerBombs.remove(0);
        boolean attackResult = attackPosition(playerAttack, playerDefend, position);
        if (getState() == 0 && attackResult) {
            Vector<Position> neighbors = position.getNeighbors(getDimV(), getDimH());
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

}