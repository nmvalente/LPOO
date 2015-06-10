package logic;

import java.util.Vector;

/**
 * Class Position contains the line and the column of the position in the board
 */
public class Position {

    /** array with the pointers to each position according to its line and column */
    private static Position[][] instance = new Position[26][26];

    /** pointer to the single position outside of the board */
    private static Position outside = new Position(26, 26);

    /** line of the board of the position */
    private int line;

    /** column of the board of the position */
    private int column;

    /**
     * Instantiates a new Position
     *
     * @param newLine line of the position
     * @param newColumn column of the position
     */
    public Position(int newLine, int newColumn) {
        line = newLine;
        column = newColumn;
    }

    /**
     * Returns the unique instance of a position given its line and column
     *
     * @param newLine line of the position we want to obtain
     * @param newColumn column of the position we want to obtain
     * @return unique Position with the given line and column
     */
    public static Position Instance(int newLine, int newColumn) {
        if (newLine == 26 && newColumn == 26) return outside;
        else if (newLine > 25 || newColumn > 25) return null;
        else {
            if (instance[newLine][newColumn] == null) {
                instance[newLine][newColumn] = new Position(newLine, newColumn);
            }
            return instance[newLine][newColumn];
        }
    }

    /**
     * Returns the position's line
     *
     * @return int with the position's line
     */
    public int getLine() {
        return line;
    }

    /**
     * Returns the position's column
     *
     * @return int with the position's column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the position with letters (e.g. Ab = line 0, column 1)
     *
     * @return string with the position in letters
     */
    @Override
    public String toString() {
        String out = "";
        out += (char)(line + 65);
        out += (char)(column + 97);
        return out;
    }

    /**
     * Checks if two positions are the same
     *
     * @return boolean true if the positions are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj instanceof Position && ((Position)obj).line == line && ((Position)obj).column == column);
    }

    /**
     * Returns a vector with the neighbor positions of this position
     *
     * @param dimV number of lines of the board
     * @param dimH number of columns of the board
     * @return vector with the neighbor positions
     */
    public Vector<Position> getNeighbors(int dimV, int dimH) {
        Vector<Position> result = new Vector<>();
        if (line < dimV - 1) result.add(Position.Instance(line + 1, column));
        if (column < dimH - 1) result.add(Position.Instance(line, column + 1));
        if (line > 0) result.add(Position.Instance(line - 1, column));
        if (column > 0) result.add(Position.Instance(line, column - 1));
        return result;
    }
}
