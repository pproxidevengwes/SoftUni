package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] rowsAndCols = readArray(scanner, "\\s+");

        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        int[][] matrix = readMatrix(scanner, rows, cols);

        int maxSum = Integer.MIN_VALUE;
        int maxRow = 0, maxCol = 0;
        for (int row = 0; row < rows - 2; row++) {
            for (int col = 0; col < cols - 2; col++) {
                int sum = matrixSum3x3(matrix, row, col);
                if (sum > maxSum) {
                    maxSum = sum;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }
        System.out.println("Sum = " + maxSum);
        for (int r = maxRow; r < maxRow + 3; r++) {
            for (int c = maxCol; c < maxCol + 3; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static int matrixSum3x3(int[][] matrix, int row, int col) {
        int sum = 0;
        for (int r = row; r < row + 3; r++) {
            for (int c = col; c < col + 3; c++) {
                sum += matrix[r][c];
            }
        }
        return sum;
    }

    private static int[][] readMatrix(Scanner scanner, int rows, int cols) {
        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = readArray(scanner, "\\s+");
        }
        return matrix;
    }

    private static int[] readArray(Scanner scanner, String separator) {
        return Arrays.stream(scanner.nextLine()
                .split(separator))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
