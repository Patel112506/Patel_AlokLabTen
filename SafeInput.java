import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int userInput;
        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            while (!console.hasNextInt()) {
                console.next();
                System.out.print("That's not a valid number. " + prompt + " [" + low + " - " + high + "]: ");
            }
            userInput = console.nextInt();
        } while (userInput < low || userInput > high);
        return userInput;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        String response;
        System.out.print(prompt + " [Y/N]: ");
        response = console.next().toUpperCase();
        while (!response.equals("Y") && !response.equals("N")) {
            System.out.print("Invalid input. " + prompt + " [Y/N]: ");
            response = console.next().toUpperCase();
        }
        return response.equals("Y");
    }
}
