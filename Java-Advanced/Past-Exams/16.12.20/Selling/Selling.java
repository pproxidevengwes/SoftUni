import java.util.Scanner;

public class Selling {
    static int money = 0;
    static int row;
    static int col;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        char[][] bakery = readCharMatrix(size, scanner);
        int[] sellerPosition = coordinates(bakery, 'S');
        row = sellerPosition[0];
        col = sellerPosition[1];

        boolean isOutOfTheBakery = false;
        String command = scanner.nextLine();
        while (true) {
            switch (command) {
                case "up":
                    isOutOfTheBakery = moveAndCollect(bakery, row - 1, col);
                    break;
                case "down":
                    isOutOfTheBakery = moveAndCollect(bakery, row + 1, col);
                    break;
                case "left":
                    isOutOfTheBakery = moveAndCollect(bakery, row, col - 1);
                    break;
                case "right":
                    isOutOfTheBakery = moveAndCollect(bakery, row, col + 1);
                    break;
            }
            if (money >= 50) {
                System.out.println("Good news! You succeeded in collecting enough money!");
                break;
            }
            if (isOutOfTheBakery) {
                System.out.println("Bad news, you are out of the bakery.");
                break;
            }
            command = scanner.nextLine();
        }
        System.out.println("Money: " + money);
        printMatrix(bakery);
    }

    public static char[][] readCharMatrix(int size, Scanner scanner) {
        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
        }
        return matrix;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char symbol : arr) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

    private static int[] coordinates(char[][] matrix, char symbol) {
        int[] coordinates = new int[0];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == symbol) {
                    coordinates = new int[]{row, col};
                }
            }
        }
        return coordinates;
    }

    private static boolean moveAndCollect(char[][] matrix, int newRow, int newCol) {
        matrix[row][col] = '-';

        if (!outOfBounds(matrix, newRow, newCol)) {
            return true;
        }
        char symbol = matrix[newRow][newCol];

        if (Character.isDigit(symbol)) {
            money += Character.getNumericValue(symbol);
        } else if (symbol == 'O') {
            matrix[newRow][newCol] = '-';
            int[] pillarPosition = coordinates(matrix, 'O');
            newRow = pillarPosition[0];
            newCol = pillarPosition[1];
        }

        matrix[newRow][newCol] = 'S';
        row = newRow;
        col = newCol;

        return false;
    }

    private static boolean outOfBounds(char[][] matrix, int row, int col) {
        return 0 <= row && row < matrix.length &&
                0 <= col && col < matrix[row].length;
    }
}
