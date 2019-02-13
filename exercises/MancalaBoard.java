import java.util.Arrays;
import java.util.Scanner;

public class MancalaBoard {

    // if one player's pits are empty, the game is over
    final int GAMEOVER[] = {0,0,0,0,0,0};

    // number of pits for each player
    final int BOARDSIZE = 6;
    
    // these values represent the number of 'stones' in the pits and mancalas of players 1 and 2
    
    int mancala1, mancala2;
    int[] pits1 = new int[BOARDSIZE];
    int[] pits2 = new int[BOARDSIZE];

    /**
     *
     * Sets all board values to beginning-of-game values.
     *
     **/

    void initialize() {
        mancala1 = mancala2 = 0;
        Arrays.fill(pits1, 4);
        Arrays.fill(pits2, 4);
    }

    /**
     *
     * Coordinates the play of one game.
     *
     **/

    void play() {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Here's the board; your pits are on the bottom, and your mancala on the right:");
        do {
            display();
            System.out.print("Which pit will you play from? ");
            int pit = kbd.nextInt();
            // call a method that plays from the selected pit
        } while (getWinner() < 0);

    }

    /**
     *
     * Displays the current state of the game.
     *
     **/

    void display() {
        System.out.println("   1   2   3   4   5   6  ");
        System.out.println("--------------------------");
        displayPits(pits1);

        System.out.println();
        System.out.format("%4d", mancala1);
        System.out.print("                ");
        System.out.format("%4d", mancala2);
        System.out.println();
        System.out.println();
        displayPits(pits2);
        System.out.println("--------------------------");
        System.out.println();
    }

    /**
     *
     * Displays the number of stones in the pits of one player.
     *
     **/

    void displayPits(int[] pits) {
        for (int i=0; i<pits.length; i++) {
            System.out.format("%4d", pits[i]);
        }
        System.out.println();
    }

    /**
     *
     * if game is over, return 1 or 2 to indicate winning player or 0 if tie; if not over return -1.
     *
     **/

    int getWinner() {
       if ((Arrays.compare(pits1, GAMEOVER) == 0) || (Arrays.compare(pits2,GAMEOVER) == 0)) {
          sweep();
            if (mancala1 > mancala2)
                return 1;
            else if (mancala2 > mancala1)
                return 2;
            else
                return 0;
       } else
          return -1;
    } 

    /**
     *
     * At endgame, player with remaining stones 'captures' them into her mancala.
     *
     **/

    void sweep() {
        for (int i=0; i<BOARDSIZE; i++) {
            mancala1 += pits1[i];
            mancala2 += pits2[i];
            pits1[i] = pits2[i] = 0;
        }
    }
}


