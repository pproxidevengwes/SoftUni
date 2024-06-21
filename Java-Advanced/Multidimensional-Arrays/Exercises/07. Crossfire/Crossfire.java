package com.company;

import java.util.*;

public class Crossfire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dimension = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimension[0]);
        int cols = Integer.parseInt(dimension[1]);

        List<List<Integer>> matrix = new ArrayList<>();

        int counter = 1;
        for (int row = 0; row < rows; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                matrix.get(row).add(counter++);
            }
        }
        String command = scanner.nextLine();
        while (!command.equals("Nuke it from orbit")) {
            String[] tokens = command.split("\\s+");
            int currentRow = Integer.parseInt(tokens[0]);
            int currentCol = Integer.parseInt(tokens[1]);
            int radius = Integer.parseInt(tokens[2]);

            for (int row = currentRow - radius; row <= currentRow + radius; row++) {
                if (isInRange(matrix, row, currentCol) && row != currentRow) {
                    matrix.get(row).remove(currentCol);
                }
            }
            for (int col = currentCol + radius; col >= currentCol - radius; col--) {
                if (isInRange(matrix, currentRow, col)) {
                    matrix.get(currentRow).remove(col);
                }
            }
            matrix.removeIf(List::isEmpty);
            command = scanner.nextLine();
        }
        printing(matrix);
    }

    private static boolean isInRange(List<List<Integer>> matrix, int row, int col) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }

    public static void printing(List<List<Integer>> matrix) {
        for (List<Integer> integers : matrix) {
            for (int col = 0; col < integers.size(); col++) {
                if (col == integers.size() - 1) {
                    System.out.print(integers.get(col));
                } else {
                    System.out.print(integers.get(col) + " ");

                }
            }
            System.out.println();
        }
    }
}
