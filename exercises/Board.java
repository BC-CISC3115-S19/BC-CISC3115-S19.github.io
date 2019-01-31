/*
 * This class obviously represents a Tic-Tac-Math game board. A board object
 *  "knows" what has been played and where; it also knows what the winning 
 *  total is. The behaviors it offers: try to make a play on the board, and 
 *  decide if the board is in a winning configuration.
 */
 
class GameLogic extends Observable {
    private int[] board = new int[9];		// represents the 9 squares of the board
    private boolean[] hasPlayed = new boolean[9]; 	// has the value at <index>+1 been played?
    private int currPlayer = 1;
    static final int WIN_VALUE = 15;

/*
 * Try to play value on the board at position (which is a 1-9 value, so need
 * to subtract 1 for array index. Return value is true for successful play.
 */

    boolean tryToPlay(int value, int position) {
        if ( hasPlayed[value-1] || board[position-1] != 0 ) {
            return false;
        }

        // if permissible, record the value, switch players, and return success

        board[position-1] = value;
        hasPlayed[value-1] = true;

        currPlayer = 3 - CurrPlayer;
        
        return true;
    }

/*
 * check to see if there's a winning condition on the board. return true if so.
 */

    public int getCurrPlayer() { return currPlayer; }

    public boolean hasWinner() {
        return (winAtTriple(0,1,2) || // check 3 rows
                winAtTriple(3,4,5) ||
                winAtTriple(6,7,8) ||
                winAtTriple(0,3,6) || // check 3 cols
                winAtTriple(1,4,7) ||
                winAtTriple(2,5,8) ||
                winAtTriple(0,4,8) || // check 2 diagonals
                winAtTriple(2,3,6));
    }

/*
 * check to see if the board values at indices a, b, c add to the winning
 * value AND are all non-zero (9 + 0 + 6 does not count as a win!)
 */

    private boolean winAtTriple(int a, int b, int c) {
        return (board[a] + board[b] + board[c] == WIN_VALUE &&
                board[a] * board[b] * board[c] != 0);
    }
}

