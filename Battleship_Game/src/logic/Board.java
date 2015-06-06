package logic;

/**
 * Class Board - contains the structure of the game board
 **/
public class Board {

    /** height of the board - number of lines - vertical dimension **/
    private int dimV;

    /** width of the board - number of columns - horizontal dimension **/
    private int dimH;

    /** matrix with the index of the ship placed in each position of the board (-1 if there is no ship) **/
    private int[][] matrix;

    /**
     * Instantiates a new Board
     *
     * @param dimVb - vertical dimension of the board
     * @param dimHb - horizontal dimension of the board
     **/
    Board(int dimVb, int dimHb) {
        dimV = dimVb;
        dimH = dimHb;
        matrix = new int[dimV][dimH];
        for (int i = 0; i < dimV; i++) {
            for (int j = 0; j < dimH; j++) {
                matrix[i][j] = -1;
            }
        }
    }

    /**
     * Returns the vertical dimension of the board
     *
     * @return int with the number of lines of the board
     **/
    int getDimV() {
        return dimV;
    }

    /**
     * Returns the horizontal dimension of the board
     *
     * @return int with the number of columns of the board
     **/
    int getDimH() {
        return dimH;
    }

    /**
     * Returns the value of a board position
     *
     * @param position - position of the board we want to check
     *
     * @return int - value of the board position
     **/
    int getPosition(Position position) {
        return matrix[position.getLine()][position.getColumn()];
    }

    /**
     * Sets a board position
     *
     * @param position - position of the board we want to change
     * @param value - value we want to place in the board
     **/
    void setPosition(Position position, int value) {
        matrix[position.getLine()][position.getColumn()] = value;
    }

}
