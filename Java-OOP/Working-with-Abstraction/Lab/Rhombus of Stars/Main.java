import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        String rombOfStars = buildRombOfStars(size);
        System.out.println(rombOfStars);
    }

    private static String buildRombOfStars(int size) {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            out.append(printLine(size - i, i)).append(System.lineSeparator());
        }

        for (int i = size - 1; i > 0; i--) {
            out.append(printLine(size - i, i)).append(System.lineSeparator());
        }
        return out.toString();
    }

    private static Object printLine(int spaces, int stars) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < spaces; i++) {
            out.append(" ");
        }
        for (int i = 0; i < stars; i++) {
            out.append("* ");
        }
        return out.toString();
    }


}
