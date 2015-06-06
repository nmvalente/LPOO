package logic;

import java.util.Vector;

public class Ship {

    /** name of the type of ship, e.g. "Aircraft carrier" **/
    private String type;

    /** number of cells of the ship **/
    private int dim;

    /** symbol that identifies the ship, usually the first letter of the type, e.g. 'A' for the "Aircraft carrier" **/
    private char symbol;

    /** vector with the state of each cell in the ship: false = dead, true = alive **/
    private Vector<Boolean> state;

    /** number of live cells of the ship, initially the same as dim **/
    private int life;

    /** position of the first cell of the ship **/
    private Position position;

    /** orientation of the ship (true - horizontal, false - vertical) **/
    private boolean orientation;

    /**
     * Instantiates a new Ship
     *
     * @param types - name of the type of the ship
     * @param dims - number of cells of the ship
     * @param symbols - symbol that represents the ship
     * //@param colors - color used to represent the ship in the console
     **/
    Ship(String types, int dims, char symbols) {
        type = types;
        dim = dims;
        symbol = symbols;
        state = new Vector<>();
        for (int i = 0; i < dim; i++) state.add(true);
        life = dims;
        position = Position.Instance(26, 26); /* initial position outside of the board */
        orientation = true;
    }

    /**
     * Returns the ship's type
     *
     * @return string with the type of the ship
     **/
    String getType() {
        return type;
    }

    /**
     * Returns the ship's dimension
     *
     * @return int with the number of cells of the ship
     **/
    int getDim() {
        return dim;
    }

    /**
     * Returns the ship's symbol
     *
     * @return char with the ship's symbol
     **/
    char getSymbol() {
        return symbol;
    }

    /**
     * Returns the ship's life
     *
     * @return int with the number of of live cells of the ship
     **/
    int getLife() {
        return life;
    }

    /**
     * Returns the ship's position
     *
     * @return Position with the ship's position
     **/
    Position getPosition() {
        return position;
    }

    /**
     * Returns the ship's orientation
     *
     * @return boolean true if the orientation is horizontal, false if it is vertical
     **/
    boolean getOrientation() {
        return orientation;
    }

    /**
     * Sets the ship's position
     *
     * @param newPosition - line and column where we want to place the ship
     **/
    void setPosition(Position newPosition) {
        position = newPosition;
    }

    /**
     * Sets the ship's orientation
     *
     * @param orientations - orientation  (true - horizontal, false - vertical)
     **/
    void setOrientation(boolean orientations) {
        orientation = orientations;
    }

    /**
     * Kills a ship's cell
     *
     * @param pos - position of the cell we which to kill (relative to the first cell of the ship)
     **/
    void killCell(int pos) {
        state.set(pos, false);
        life -= 1;
    }

    /**
     * Writes the ship's information to a string
     *
     * @return string with the symbol, dimension, position and orientation of the ship
     **/
    @Override
    public String toString() {
        String out = "";
        String orient = orientation ? "H" : "V";
        out += type + " - " + symbol + " - " + dim + " - " + position + " - " + orient;
        for (Boolean cellState : state) out += " - " + cellState;
        return out;
    }

    /**
     * Returns the ships cells on the board
     *
     * @return vector with all the positions of the ship's cells
     **/
    Vector<Position> getCells() {
        Vector<Position> cells = new Vector<>();
        for (int i = 0; i < dim; i ++) {
            if (orientation) {/* horizontal */
                Position cellPosition = Position.Instance(position.getLine(), position.getColumn() + i);
                cells.add(cellPosition);
            }
            else {
                Position cellPosition = Position.Instance(position.getLine() + i, position.getColumn());
                cells.add(cellPosition);
            }
        }
        return cells;
    }

    /**
     * Checks if the ship overlaps another ship on the board
     *
     * @param otherShip - other ship with which we want to check overlapping
     *
     * @return boolean - true if they overlap, false otherwise
     **/
    boolean overlaps(Ship otherShip) {
        int otherDim = otherShip.getDim();
        Vector<Position> otherCells = otherShip.getCells();
        Vector<Position> cells = this.getCells();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < otherDim; j++) {
                if ((cells.get(i)).equals(otherCells.get(j))) return true;
            }
        }
        return false;
    }

    /**
     * Check if some of the ship's cells are outside of the board
     *
     * @param dimV - vertical dimension of the board
     * @param dimH - horizontal dimension of the board
     *
     * @return boolean - true if the ship exceeds the dimension, false otherwise
     **/
    boolean exceedsDim(int dimV, int dimH) {
        Vector<Position> cells = this.getCells();
        for (int i = 0; i < dim; i++) {
            if ((cells.get(i)).getLine() >= dimV || (cells.get(i)).getColumn() >= dimH)  return true;
        }
        return false;

    }

}
