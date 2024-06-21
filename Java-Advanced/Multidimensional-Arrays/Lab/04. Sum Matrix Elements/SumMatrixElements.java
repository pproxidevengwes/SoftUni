package com.company;

import java.util.*;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] rowsAndCols = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] tokens = scanner.nextLine().split(", ");
            for (int col = 0; col < tokens.length; col++) {
                matrix[row][col]= Integer.parseInt(tokens[col]);
            }
        }
        System.out.println(rows);
        System.out.println(cols);
        System.out.println(getElementsSum(matrix));
    }

    private static int getElementsSum(int[][] matrix) {
        int sum=0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                sum+=matrix[row][col];
            }
        }
        return sum;
    }

}
