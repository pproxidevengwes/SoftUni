import java.util.*;

public class Snake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int foodQuantity = 0;
        int size = Integer.parseInt(scanner.nextLine());
      
        char[][] teritory = readCharMatrix(size, scanner);

        int[] coordinatesSnake = coordinates(teritory, 'S');
        int row = coordinatesSnake[0];
        int col = coordinatesSnake[1];

        while (true) {
            String command = scanner.nextLine();
         
            teritory[row][col] = '.';

            switch (command) {
                case "up":
                    row--;
                    break;
                case "down":
                    row++;
                    break;
                case "left":
                    col--;
                    break;
                case "right":
                    col++;
                    break;
            }

            if (isOutOfBounds(teritory, row, col)) {
                System.out.println("Game over!");
                break;
            }

            if (teritory[row][col] == '*') {
                foodQuantity++;
            } else if (teritory[row][col] == 'B') {
                teritory[row][col] = '.';
                int[] coordinatesBurrow = coordinates(teritory, 'B');
                row = coordinatesBurrow[0];
                col = coordinatesBurrow[1];
            }
          
            teritory[row][col] = 'S';

            if (foodQuantity == 10) {
                System.out.println("You won! You fed the snake.");
                break;
            }

        }
        System.out.println("Food eaten: " + foodQuantity);
        printMatrix(teritory);
    }

    private static boolean isOutOfBounds(char[][] matix, int row, int col) {
        return 0 > row || row >= matix.length || 0 > col || col >= matix[row].length;
    }

    private static int[] coordinates(char[][] matrix, char symbol) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == symbol) {
                    return new int[]{row, col};

                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (int element : arr) {
                System.out.print((char) element);
            }
            System.out.println();
        }
    }

    public static char[][] readCharMatrix(int size, Scanner scanner) {
        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < size; col++) {
                matrix[row][col] = line.charAt(col);
            }
        }
        return matrix;
    }


}
