import java.util.Scanner;

/**
 *
 * Main program for a mancala game. The MancalaBoard class contains
 * all the game logic.
 *
 **/

public class PlayMancala {
    public static void main(String[] args) {
        MancalaBoard board = new MancalaBoard();
        board.initialize();

        Scanner kbd = new Scanner(System.in);
        String input;

        System.out.println("Welcome to Mancala!");
        do {
            board.play();
            System.out.print("Play again (Y for yes)? ");
            input = kbd.nextLine().toUpperCase();
        } while (input.charAt(0) == 'Y');

        System.out.println("Thanks for playing.");
    }
}

