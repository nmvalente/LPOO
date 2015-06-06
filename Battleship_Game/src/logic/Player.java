package logic;

import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;

/**
 *  Class Player - contains all the information of a game player
 **/
public class Player {

    private int number;

    /** name of the player **/
    private String name;

    /** vector with pointers for the player's ships **/
    private Vector<Ship> ships;

    /** number of live ships of the player **/
    private int liveShips;

    /** player's board, initially empty, later with the players ships **/
    private Board board;

    /** player's idea of the opponent's board, with the player's guesses **/
    private Board opponent;

    /**
     * Instantiates a new Player
     *
     * @param newName - player's name
     **/
    Player(int newNumber, String newName) {
        number = newNumber;
        name = newName;
        ships = new Vector<>();
        liveShips = 0;
        board = null;
        opponent = null;
    }

    /**
     * Adds a ship to the player's ships
     *
     * @param ship - ship we want to add
     **/
    void addShip(Ship ship) {
        ships.add(ship);
        liveShips++;
    }

    /**
     * Sets the board of the player
     *
     * @param newBoard - new board
     **/
    void setBoard(Board newBoard) {
        board = newBoard;
    }

    /**
     * Sets the idea of the board of the opponent
     *
     * @param newOpponent - new board for the opponent
     **/
    void setOpponent(Board newOpponent) {
        opponent = newOpponent;
    }

    /**
     * Returns the name of the player
     *
     * @return string with the name of the player
     **/
    public String getName() {
        return name;
    }

    /**
     * Returns the vector ships of the player
     *
     * @return vector<Ship> with the player's ships
     **/
    Vector<Ship> getShips() {
        return ships;
    }

    /**
     * Returns the number of live ships of the player
     *
     * @return int with the number of live ships
     **/
    int getLiveShips() {
        return liveShips;
    }

    /**
     * Returns the board of the player
     *
     * @return Board - player's board
     **/
    Board getBoard() {
        return board;
    }

    /**
     * Returns the board of the opponent
     *
     * @return Board - opponent's board
     **/
    Board getOpponent() {
        return opponent;
    }

    /**
     * Writes the player's ships to a file
     *
     * @param writer - connection to the file where we want to write
     **/
    void writeShips(Writer writer) throws IOException {
        String boardDim = board.getDimV() + " - " + board.getDimH() + " - " + ships.size();
        writer.write(boardDim);
        writer.write("\n");
        for (Ship ship : ships) {
            writer.write(ship.toString());
            writer.write("\n");
        }
    }

    /**
     * Checks if a given ship overlaps any other ship of the player
     *
     * @param shipIndex - index of the ship we want to check
     *
     * @return boolean - true if there is some overlap with the given ship, false otherwise
     **/
    private boolean shipOverlaps(int shipIndex) {
        for (int i = 0; i < ships.size(); i++) {
            if (i != shipIndex && (ships.get(shipIndex)).overlaps(ships.get(i))) return true;
        }
        return false;
    }

    /**
     * Randomly places the player's ships
     *
     * Note: the ships are not placed on the board, only their information is altered
     **/
    void autoPlaceShips(Random random) {
        int dimVb = board.getDimV();
        int dimHb = board.getDimH();
        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            do {
                int line = random.nextInt(dimVb);
                int column = random.nextInt(dimHb);
                ship.setPosition(Position.Instance(line, column));
                ship.setOrientation(random.nextInt(2) != 0); /* random orientation */
            }
            while (shipOverlaps(i) || ship.exceedsDim(dimVb, dimHb));
        /* checks if the position is valid, otherwise chooses another one */
        }
    }

    /**
     * Sets the position of a player's ship
     *
     * @param shipIndex - index of the ship whose position we want to change
     * @param position - position of the board where we want to place the ship
     * @param orientation - orientation vertical or horizontal for the ship
     *
     * @return boolean - true if the position is valid, false otherwise
     **/
    boolean manualPlaceShip(int shipIndex, Position position, boolean orientation) {
        int dimVb = board.getDimV();
        int dimHb = board.getDimH();
        Ship ship = ships.get(shipIndex);
        ship.setPosition(position);
        ship.setOrientation(orientation);
        return !(shipOverlaps(shipIndex) || ship.exceedsDim(dimVb, dimHb));
    }

    /**
     * Changes the position of a player's ship
     *
     * @param oldPosition - current position of the board where the ship is
     * @param newPosition - position of the board where we want to place the ship
     * @param newOrient - intended orientation for the ship
     *
     * @return boolean - true if the change is valid, false otherwise
     **/
    boolean changeShipPosition(Position oldPosition, Position newPosition, boolean newOrient) {
        int shipIndex = board.getPosition(oldPosition);
        if (shipIndex < 0) return false; /* if there is no ship in the given position we do nothing else */
        else {
            oldPosition = ships.get(shipIndex).getPosition();
            boolean oldOrient = ships.get(shipIndex).getOrientation();
            removeShipBoard(shipIndex);
            if (manualPlaceShip(shipIndex, newPosition, newOrient)) {
                placeShipBoard(shipIndex);
                return true;
            /* if the new position is valid, we place the ship in the board */
            }
            else {
                manualPlaceShip(shipIndex, oldPosition, oldOrient);
                placeShipBoard(shipIndex);
                return false;
            /* otherwise we place the ship back where it was */
            }
        }
    }

    /**
     * Removes a player's ship from the board
     *
     * @param shipIndex - index of the ship in the vector of ships of the player
     **/
    private void removeShipBoard(int shipIndex) {
        Ship ship = ships.get(shipIndex);
        Vector<Position> cells = ship.getCells();
        int dim = ship.getDim();
        for (int i = 0; i < dim; i++) board.setPosition(cells.get(i), -1);
    }

    /**
     * Places a player's ship on the board
     *
     * @param shipIndex - index of the ship in the vector of ships of the player
     **/
    void placeShipBoard(int shipIndex) {
        Ship ship = ships.get(shipIndex);
        Vector<Position> cells = ship.getCells();
        int dim = ship.getDim();
        for (int i = 0; i < dim; i++) board.setPosition(cells.get(i), shipIndex);
    }

    /**
     * Places all the ships in the board
     **/
    void placeShipsBoard() {
        for (int i = 0; i < ships.size(); i++) placeShipBoard(i);
    }

    /**
     * Writes the player's and the opponent's boards to a string
     *
     * @return string with the boards containing the ships or the guesses
     **/
    @Override
    public String toString() {
        String out = "   ";
        for (int i = 0; i < board.getDimH(); i++) {
            out += (char)(i + 97) + " ";
        }
        if (opponent != null) {
            out += "         ";
            for (int i = 0; i < opponent.getDimH(); i++) {
                out += (char)(i + 97) + " ";
            }
        }
        out += "\n";
        for (int i = 0; i < board.getDimV(); i++) {
            out += " " + (char)(i + 65) + " ";
            for (int j = 0; j < board.getDimH(); j++) {
                int index = board.getPosition(Position.Instance(i, j));
                if (index == -1) out += ". ";
                else if (index == -2) out += "- ";
                else if (index == -3) out += "* ";
                else out += (ships.get(index)).getSymbol() + " ";
            }
            if (opponent != null) {
                out += "       " + (char)(i + 65) + " ";
                for (int j = 0; j < opponent.getDimH(); j++) {
                    int index = opponent.getPosition(Position.Instance(i, j));
                    if (index == -1) out += ". ";
                    else if (index == -2) out += "- ";
                    else out += "* ";
                }
            }
            out += "\n";
        }
        return out;
    }

    void attackFailure(Position position) {
        opponent.setPosition(position, -2);
    }

    void defendFailure(Position position) {
        board.setPosition(position, -2);
    }

    void attackSuccess(Position position) {
        opponent.setPosition(position, -3);
    }

    void defendSuccess(Position position) {
        int index = board.getPosition(position);
        Ship ship = ships.get(index);
        boolean shipOrientation = ship.getOrientation();
        Position shipPosition = ship.getPosition();
        int cellPos;
        if (shipOrientation) cellPos = position.getColumn() - shipPosition.getColumn();
        else cellPos = position.getLine() - shipPosition.getLine();
        ship.killCell(cellPos);
        if (ship.getLife() == 0) liveShips--;
        board.setPosition(position, -3);
    }

    void setName(String playerName) {
        name = playerName;
    }

    void writeOpponent(Writer writer) throws IOException {
        String boardDim = opponent.getDimV() + " - " + opponent.getDimH();
        writer.write(boardDim);
        String water = "";
        String fire = "";
        for (int i = 0; i < opponent.getDimV(); i++) {
            for (int j = 0; j < opponent.getDimH(); j++) {
                Position position = Position.Instance(i, j);
                if (opponent.getPosition(position) == -2) water += " - " + position;
                else if (opponent.getPosition(position) == -3) fire += " - " + position;
            }
        }
        if (water.length() > 0) water = water.substring(3);
        if (fire.length() > 0) fire = fire.substring(3);
        writer.write("\n" + water);
        writer.write("\n" + fire);
    }

    boolean readOpponent(BufferedReader reader) throws IOException {
        String fileLine;
        String[] dimensions;
        int dimV;
        int dimH;
        fileLine = reader.readLine();
        dimensions = fileLine.split(" - ");
        dimV = Integer.parseInt(dimensions[0]);
        dimH = Integer.parseInt(dimensions[1]);
        Board opponentBoard = new Board(dimV, dimH);
        fileLine = reader.readLine();
        if (fileLine != null) {
            String[] waters = fileLine.split(" - ");
            for (String water : waters) {
                if (water.length() > 1) {
                    int line = water.charAt(0) - 65;
                    int column = water.charAt(1) - 97;
                    Position position = Position.Instance(line, column);
                    opponentBoard.setPosition(position, -2);
                }
            }
        }
        fileLine = reader.readLine();
        if (fileLine != null) {
            String[] fires = fileLine.split(" - ");
            for (String fire : fires) {
                if (fire.length() > 1) {
                    int line = fire.charAt(0) - 65;
                    int column = fire.charAt(1) - 97;
                    Position position = Position.Instance(line, column);
                    opponentBoard.setPosition(position, -3);
                }
            }
        }
        setOpponent(opponentBoard);
        fileLine = reader.readLine();
        return Objects.equals(fileLine, "true");
    }

    void readShips(BufferedReader reader) throws IOException {
        String fileLine = reader.readLine();
        String[] dimensions = fileLine.split(" - ");
        int dimV = Integer.parseInt(dimensions[0]);
        int dimH = Integer.parseInt(dimensions[1]);
        int numberShips = Integer.parseInt(dimensions[2]);
        Board playerBoard = new Board(dimV, dimH);
        setBoard(playerBoard);
        for (int i = 0; i < numberShips; i++) {
            fileLine = reader.readLine();
            String[] shipSpecs = fileLine.split(" - ");
            Ship ship = new Ship(shipSpecs[0], Integer.parseInt(shipSpecs[2]), shipSpecs[1].charAt(0));
            int line = shipSpecs[3].charAt(0) - 65;
            int column = shipSpecs[3].charAt(1) - 97;
            Position position = Position.Instance(line, column);
            ship.setPosition(position);
            boolean orientation = Objects.equals(shipSpecs[4], "H");
            ship.setOrientation(orientation);
            for (int j = 0; j < ship.getDim(); j++) {
                if (Objects.equals(shipSpecs[5 + j], "false")) ship.killCell(j);
            }
            addShip(ship);
        }
        placeShipsBoard();
    }

    void getBombResults(Player otherPlayer) {
        Board opponentBoard = otherPlayer.getOpponent();
        int dimV = opponentBoard.getDimV();
        int dimH = opponentBoard.getDimH();
        for (int i = 0; i < dimV; i++) {
            for (int j = 0; j < dimH; j++) {
                Position position = Position.Instance(i, j);
                int value = opponentBoard.getPosition(position);
                if (value!= -1) board.setPosition(position, value);
            }
        }
    }

    public int getNumber() {
        return number;
    }
}
