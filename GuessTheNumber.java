import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int low = 1;
        int high = 100;
        int guess;
        String response;

        System.out.println("Think of a number between 1 and 100.");
        System.out.println("I will try to guess it!");

        while (low <= high) {
            guess = (low + high) / 2;
            System.out.println("Is your number " + guess + "?");
            System.out.print("Enter 'higher', 'lower', or 'correct': ");
            response = scanner.nextLine().toLowerCase();

            if (response.equals("correct")) {
                System.out.println("Yay! I guessed your number!");
                break;
            } else if (response.equals("higher")) {
                low = guess + 1;
            } else if (response.equals("lower")) {
                high = guess - 1;
            } else {
                System.out.println("Please respond with 'higher', 'lower', or 'correct'.");
            }
        }

        if (low > high) {
            System.out.println("Hmm, something went wrong. Are you sure you answered correctly?");
        }

        scanner.close();
    }
}
