package com.company;

import java.util.*;

public class PrintDiagonalsOfSquareMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readIntMatrix(scanner);
        int col = 0;
        for (int row = 0; row < matrix.length; row++) {
            System.out.print(matrix[row][col] + " ");
            col++;
        }
        System.out.println();
        col = 0;
        for (int row = matrix.length - 1; row >= 0; row--) {
            System.out.print(matrix[row][col] + " ");
            col++;
        }
        System.out.println();
    }

    private static int[][] readIntMatrix(Scanner scanner) {
        int rows = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[rows][];
        for (int row = 0; row < rows; row++) {
            matrix[row] = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}
