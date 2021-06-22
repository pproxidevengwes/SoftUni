import java.util.Scanner;

public class Revolt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int lines = Integer.parseInt(scanner.nextLine());

        int[] position = new int[2];

        char[][] matrix = new char[size][size];
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();

            if (line.contains("f")) {
                position[0] = row;
                position[1] = line.indexOf("f");
            }

        }
        boolean isWinner = false;

        while (lines-- > 0 && !isWinner) {
            String command = scanner.nextLine();

            switch (command) {
                case "up":
                    isWinner = movePlayer(position, matrix, -1, 0);
                    break;
                case "down":
                    isWinner = movePlayer(position, matrix, +1, 0);
                    break;
                case "left":
                    isWinner = movePlayer(position, matrix, 0, -1);
                    break;
                case "right":
                    isWinner = movePlayer(position, matrix, 0, +1);
                    break;
            }

        }
        if (isWinner) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
        }

        printMatrix(matrix);
    }

    private static boolean movePlayer(int[] position, char[][] matrix, int rowModification, int colModification) {
        boolean hasWon = false;

        int row = position[0];
        int col = position[1];
        int newRow = isInBounds(row + rowModification, matrix.length);
        int newCol = isInBounds(col + colModification, matrix.length);

        if (matrix[newRow][newCol] == 'F') {
            hasWon = true;
        } else if (matrix[newRow][newCol] == 'B') {
            newRow = isInBounds(newRow + rowModification, matrix.length);
            newCol = isInBounds(newCol + colModification, matrix.length);
            if (matrix[newRow][newCol] == 'F') {
                hasWon = true;
            }
        } else if (matrix[newRow][newCol] == 'T') {
            newRow = row;
            newCol = col;
        }
        matrix[newRow][newCol] = 'f';
        matrix[row][col] = '-';
        position[0] = newRow;
        position[1] = newCol;

        return hasWon;
    }

    private static int isInBounds(int index, int bounds) {
        if (index < 0) {
            index = bounds - 1;
        } else if (index >= bounds) {
            index = 0;
        }
        return index;
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
