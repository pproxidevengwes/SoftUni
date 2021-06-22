import java.util.Scanner;

public class Bee {
    static int beeRow = 0;
    static int beeCol = 0;
    static int pollinatedFlowers = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];
        for (int row = 0; row < size; row++) {
            String colElement = scanner.nextLine();
            matrix[row] = colElement.toCharArray();
            if (colElement.contains("B")) {
                beeRow = row;
                beeCol = colElement.indexOf('B');
            }
        }
        boolean isInBounds = true;
        String command = scanner.nextLine();
        while (!command.equals("End")) {
            switch (command) {
                case "up":
                    isInBounds = moveTheBee(matrix, -1, 0);
                    break;
                case "down":
                    isInBounds = moveTheBee(matrix, 1, 0);
                    break;
                case "left":
                    isInBounds = moveTheBee(matrix, 0, -1);
                    break;
                case "right":
                    isInBounds = moveTheBee(matrix, 0, 1);
                    break;
            }

            if (!isInBounds) {
                System.out.println("The bee got lost!");
                break;
            }

            command = scanner.nextLine();
        }

        if (pollinatedFlowers >= 5) {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!%n", pollinatedFlowers);
        } else {
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more%n", (5 - pollinatedFlowers));
        }

        printMatrix(matrix);
    }

    private static boolean moveTheBee(char[][] matrix, int rowModification, int colModification) {
        int currentRow = beeRow;
        int currentCol = beeCol;

        beeRow += rowModification;
        beeCol += colModification;
        matrix[currentRow][currentCol] = '.';

        if (!isIndexInBounds(beeRow, matrix) || !isIndexInBounds(beeCol, matrix)) {
            return false;
        }

        if (matrix[beeRow][beeCol] == 'f') {
            pollinatedFlowers++;
        } else if (matrix[beeRow][beeCol] == 'O') {
            moveTheBee(matrix, rowModification, colModification);
        }
        matrix[beeRow][beeCol] = 'B';
        return true;
    }

    private static boolean isIndexInBounds(int index, char[][] matrix) {
        return index >= 0 && index < matrix.length;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char symbol : arr) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
