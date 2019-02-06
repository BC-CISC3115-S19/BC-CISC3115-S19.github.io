import java.util.Scanner;

public class Exercise0 {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter two numbers: ");

        if (keyboard.hasNextInt()) {
            int firstNum = keyboard.nextInt();
            if (keyboard.hasNextInt()) {
                int secondNum = keyboard.nextInt();
                System.out.println(firstNum + secondNum);
            } else if (keyboard.hasNextDouble()) {
                double secondNum = keyboard.nextDouble();
                // print sum
            } else { // error
            }
            
        } else if (keyboard.hasNextDouble()) {
            double firstNum = keyboard.nextDouble();
            // don't care type because output will be double
            double secondNum = keyboard.nextDouble();
            System.out.println(firstNum + secondNum);
        } else {
            // error
        }

        } else {
            System.out.println("Looks like there's something wrong with your first number...");
        }
    }
}
