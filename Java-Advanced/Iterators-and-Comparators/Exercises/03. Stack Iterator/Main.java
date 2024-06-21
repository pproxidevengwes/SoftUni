import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack<Integer> stack = new Stack<>();

        String line = scanner.nextLine();
        while (!line.equals("END")) {
            String[] tokens = line.split("[, ]+");
            if (tokens[0].equals("Push")) {
                for (int i = 1; i < tokens.length; i++) {
                    stack.push(Integer.parseInt(tokens[i]));
                }
            } else if (tokens[0].equals("Pop")) {
                try {
                    stack.pop();
                } catch (Exception e) {
                    System.out.println("No elements");
                }
            }
            line = scanner.nextLine();
        }
        for (int i = 1; i <= 2; i++) {
            for (Integer num : stack) {
                System.out.println(num);
            }
        }
    }
}
