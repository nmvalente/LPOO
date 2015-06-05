package extras;

import java.util.Random;

/**
 * This class represents a Battleship game grid.
 */
public class BattleshipGrid
{
	
    public static final int OUT_OF_BOUNDS = -1;

    // values to be used in the 2D grid
    public static final int EMPTY = 0;
    public static final int SHIP = 1;
    public static final int HIT = 2;
    public static final int MISS = 3;

    // directions used for shape placement
    private static final int DIRECTION_RIGHT = 0;
    private static final int DIRECTION_DOWN = 1;

    // lengths of the various ships in the game
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int DESTROYER_LENGTH = 3;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int PATROL_BOAT_LENGTH = 2;

    // array of all ship lengths
    private static final int[] SHIP_LENGTHS =
    {
        AIRCRAFT_CARRIER_LENGTH,
        BATTLESHIP_LENGTH,
        DESTROYER_LENGTH,
        SUBMARINE_LENGTH,
        PATROL_BOAT_LENGTH
    };

    // the total number of hits required to destroy all ships    
    private static int totalHitsRequired;

    // the number of rows and columns in this grid
    public final int NUM_ROWS;
    public final int NUM_COLS;

    // ::: DECLARE YOUR 2D ARRAY NAMED grid HERE
    public int grid[][];


    /**
     * Creates a new BattleshipGrid instance and randomly places
     * ships on the grid.
     *
     * @param    numRows    the number of rows in this grid
     * @param    numCols    the number of columns in this grid
     */
    public BattleshipGrid(int numRows, int numCols)
    {
    	
        NUM_ROWS = numRows;
        NUM_COLS = numCols;

        // initialize the grid so all cells are EMPTY
        initializeGrid();

        // determine the total number of hits required to win
        calculateTotalHitsRequired();

        // randomly place all of the ships on the grid
        placeAllShips();
    }
    
    /**
     * Initializes the game grid to size [NUM_ROWS][NUM_COLS]
     * and sets each element of the grid to the constant EMPTY.
     */
    private void initializeGrid()
    {
        // ::: MAKE grid A 2D ARRAY OF THE APPROPRIATE DIMENSIONS AS
        //     SPECIFIED IN THE METHOD DESCRIPTION. REMEMBER THAT YOU
        //     HAVE ALREADY DECLARED grid AS AN INSTANCE VARIABLE AND
        //     THAT YOU SHOULD NOT REDECLARE IT HERE.
    	grid = new int[NUM_ROWS][NUM_COLS];


        // ::: INITIALIZE ALL ELEMENTS OF grid TO THE VALUE EMPTY
    	for(int i = 0 ; i < NUM_ROWS ; i++)
    		for(int j = 0 ; j < NUM_COLS ; j++){
    			grid[i][j] = EMPTY;
    		}
    }

    /**
     * Calculates the total number of hits required to
     * destroy all ships and win the game.
     */
    private static void calculateTotalHitsRequired()
    {
        totalHitsRequired = 0;
        
        // add all the ship lengths together to get
        // the total number of hits required
        for (int length : SHIP_LENGTHS)
        {
            totalHitsRequired += length;
        }
    }

    /**
     * Places all of the game's ships onto the grid.
     */
    private void placeAllShips()
    {
        // ::: FILL IN THIS CODE
    }



    /**
     * Attacks the grid cell at the specified row and column.
     * If the grid cell contains:
     *   - SHIP: the value of the cell is set to HIT
     *   - HIT: the value of the cell does not change
     * Otherwise, the value of the cell is set to MISS.
     *
     * This method returns true if the attack resulted in a ship being hit,
     * and false otherwise.
     *
     * Note: this method also returns true if a cell that has already
     * been hit is attacked.
     *
     * @param    row    the row of the cell to attack
     * @param    col    the column of the cell to attack
     *
     * @return   true if the attack results in a ship being hit (even
     *           if the ship at that cell has already been hit),
     *           false otherwise
     */
    public boolean attack(int row, int col)
    {
        // ::: FILL IN THIS CODE

        return false;
    }



    /**
     * Returns true if all of the ships have been destroyed, and
     * false otherwise.
     *
     * @return   true if all ships have been destroyed, false otherwise
     */
    public boolean allDestroyed()
    {
        // ::: FILL IN THIS CODE. NOTE THAT THERE IS A VARIABLE NAMED
        //     totalHitsRequired THAT CONTAINS THE TOTAL NUMBER OF HITS
        //     REQUIRED TO DESTROY ALL SHIPS

        return false;
    }



    /**
     * Determine if the cell at the specified row and column is in
     * the grid's bounds.
     *
     * @param    row    the cell's row
     * @param    col    the cell's column
     *
     * @return   true if the cell is in bounds, false otherwise
     */
    private boolean inBounds(int row, int col)
    {
        return ((row >= 0) && (row < NUM_ROWS) &&
                (col >= 0) && (col < NUM_COLS));        
    }

    /**
     * Returns the value of the cell at [row][col] if the cell is in
     * bounds. Otherwise, returns OUT_OF_BOUNDS.
     *
     * @param    row    the cell's row
     * @param    col    the cell's column
     *
     * @return   if in bounds, the value of the cell at [row][col],
     *           otherwise OUT_OF_BOUNDS
     */
    public int getCell(int row, int col)
    {
        if (inBounds(row, col))
            return grid[row][col];
        else
            return OUT_OF_BOUNDS;
    }

    /**
     * Prints the grid.
     */
    public void printGrid()
    {
        // ::: FILL IN THIS CODE
    }
}
