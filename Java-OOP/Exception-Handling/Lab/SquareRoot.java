import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SquareRoot {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        try {
            double number = sqrt(input);
            System.out.println(number);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Good bye");
        }
    }

    private static double sqrt(String num) {
        for (char symbol : num.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                throw new NumberFormatException("Invalid number");
            }
        }
        int number = Integer.parseInt(num);
        return Math.sqrt(number);
    }
}
