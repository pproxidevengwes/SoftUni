import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Python {
    static int pythonLength = 1;
    static int food = 0;
    static int foodCount = 0;
    static boolean hasBeenKilled = false;
    static int foodLeft = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        ArrayDeque<String> commands = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        int[] position = new int[2];

        char[][] screen = new char[size][size];

        for (int r = 0; r < size; r++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            for (int c = 0; c < size; c++) {
                char currentSymbol = tokens[c].charAt(0);
                screen[r][c] = currentSymbol;
                if (currentSymbol == 'f') {
                    foodCount++;
                } else if (currentSymbol == 's') {
                    position[0] = r;
                    position[1] = c;
                }
            }
        }

        boolean isWinner = false;


        while (!commands.isEmpty()) {
            String command = commands.poll();
            switch (command) {
                case "up":
                    isWinner = move(position, screen, -1, 0);
                    break;
                case "down":
                    isWinner = move(position, screen, +1, 0);
                    break;
                case "left":
                    isWinner = move(position, screen, 0, -1);
                    break;
                case "right":
                    isWinner = move(position, screen, 0, +1);
                    break;
            }
           /* int foodCount = 0;
            for (char[] chars : screen) {
                for (char element : chars) {
                    if (element == 'f') {
                        foodCount++;
                    }
                }
            }*/
            if (hasBeenKilled) {
                break;
            }
        }
        if (foodCount > food) {
            foodLeft = foodCount - food;
            isWinner = true;
        }

        if (!isWinner) {
            System.out.printf("You win! Final python length is %d", pythonLength);
        } else {
            if (hasBeenKilled) {
                System.out.println("You lose! Killed by an enemy!");
            } else {
                System.out.printf("You lose! There is still %d food to be eaten.", foodLeft);
            }
        }

    }


    private static boolean move(int[] position, char[][] matrix, int rowModification, int colModification) {
        boolean hasWon = false;

        int row = position[0];
        int col = position[1];
        int newRow = isIndexInBounds(row + rowModification, matrix.length);
        int newCol = isIndexInBounds(col + colModification, matrix.length);

        if (matrix[newRow][newCol] == 'f') {
            pythonLength++;
            food++;

        } else if (matrix[newRow][newCol] == 'e') {
            hasBeenKilled = true;

        }
        matrix[newRow][newCol] = 's';
        matrix[row][col] = '*';
        position[0] = newRow;
        position[1] = newCol;

        return hasWon;
    }


    private static int isIndexInBounds(int index, int bounds) {
        if (index < 0) {
            index = bounds - 1;
        } else if (index >= bounds) {
            index = 0;
        }
        return index;
    }


}
