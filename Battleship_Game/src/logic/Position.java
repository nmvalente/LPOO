package logic;

/**
 * Class Position - contains the line and the column of the position in the board
 **/
public class Position {

    private static Position[][] instance = new Position[26][26];
    private static Position outside = new Position(26, 26);

    /** line of the board of the position **/
    private int line;

    /** column of the board of the position **/
    private int column;

    /**
     * Instantiates a new Position
     *
     * @param newLine - line of the position
     * @param newColumn - column of the position
     **/
    private Position(int newLine, int newColumn) {
        line = newLine;
        column = newColumn;
    }

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
     **/
    public int getLine() {
        return line;
    }

    /**
     * Returns the position's column
     *
     * @return int with the position's column
     **/
    public int getColumn() {
        return column;
    }

    /**
     * Returns the position with letters (e.g. Ab = line 0, column 1)
     **/
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
     * @return boolean - true if the positions are the same, false otherwise
     **/
    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj instanceof Position && ((Position)obj).line == line && ((Position)obj).column == column);
    }

}
