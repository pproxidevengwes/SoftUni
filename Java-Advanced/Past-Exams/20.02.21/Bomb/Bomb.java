import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bomb {
    static int bombs = 0;
    static int row = 1;
    static int col = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        ArrayDeque<String> queue = Arrays.stream(scanner.nextLine().split("\\s*"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        char[][] matrix = readCharMatrix(size, size, scanner);

        while (!queue.isEmpty()) {
            String command = queue.poll();
            int result = 0;
            switch (command) {
                case "up":
                    result = move(matrix, row - 1, col);
                    break;
                case "down":
                    result = move(matrix, row + 1, col);
                    break;
                case "left":
                    result = move(matrix, row, col - 1);
                    break;
                case "right":
                    result = move(matrix, row, col + 1);
                    break;
            }
            if (result == -1) {
                System.out.println("Congratulations! You found all bombs!");
                return;
            }
            if (result == 1) {
                System.out.println("END! " + bombs + " bombs left on the field");
                return;
            }
        }
        System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)", bombs, row, col);
    }

    private static int move(char[][] matrix, int newRow, int newCol) {
        if (outOfBounds(matrix, newRow, newCol)) {
            return 0;
        }
        matrix[row][col] = '+';
        row = newRow;
        col = newCol;

        if (matrix[newRow][newCol] == 'B') {
            matrix[row][col] = 's';
            bombs--;
            return -1;
        } else if (matrix[newRow][newCol] == 'e') {
            matrix[row][col] = 's';
            return 1;
        }
        matrix[row][col] = 's';
        return 0;
    }

    private static boolean outOfBounds(char[][] matrix, int row, int col) {
        return 0 <= row && row < matrix.length &&
                0 <= col && col < matrix[row].length;
    }

    public static char[][] readCharMatrix(int rows, int cols, Scanner scanner) {
        char[][] matrix = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            String[] tokens = scanner.nextLine().split(" ");
            for (int c = 0; c < tokens.length; c++) {
                char current = tokens[c].charAt(0);
                matrix[r][c] = tokens[c].charAt(0);
                if (current == 'B') {
                    bombs++;
                } else if (current == 's') {
                    row = r;
                    col = c;
                }
            }
        }
        return matrix;
    }
}
