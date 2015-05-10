package logic;

/**
 * Class Position - contains the line and the column of the position in the board
 **/
public class Position {

    /** line of the board of the position **/
    private int line;

    /** column of the board of the position **/
    private int column;

    /**
     * Instantiates a new Position
     *
     * @param linep - line of the position
     * @param columnp - column of the position
     **/
    Position(int linep, int columnp) {
        line = linep;
        column = columnp;
    }

    /**
     * Returns the position's line
     *
     * @return int with the position's line
     **/
    int getLine() {
        return line;
    }

    /**
     * Returns the position's column
     *
     * @return int with the position's column
     **/
    int getColumn() {
        return column;
    }

    /**
     * Sets the position's line
     *
     * @param newLine - position's new line
     **/
    void setLine(int newLine) {
        line = newLine;
    }

    /**
     * Returns the position's column
     *
     * @param newColumn - position's new column
     **/
    void setColumn(int newColumn) {
        column = newColumn;
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
