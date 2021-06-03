package com.company;

import java.util.*;

public class MaximumSumOf2x2Submatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] rowsAndCols=readArray(scanner.nextLine(),", ");
        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        int[][] matrix = readMatrix(rows, cols, scanner,", ");


        int[][]maxSubMatrix=getMaxSumSuBMatrix2by2(matrix);
        printMatrix(maxSubMatrix);
        System.out.println(getElementsSum(maxSubMatrix));


    }

public static int[][]getMaxSumSuBMatrix2by2(int[][]matrix) {
    int maxSum=0;
    int bestRow=0;
    int bestCol=0;

    for (int row = 0; row < matrix.length - 1; row++) {
        for (int col = 0; col < matrix[row].length-1; col++) {
            int sum=matrix[row][col]+
                    matrix[row][col+1]+
                    matrix[row+1][col]+
                    matrix[row+1][col+1];
            if (sum>maxSum) {
                maxSum=sum;
                bestRow=row;
                bestCol=col;
            }
        }
    }
    return new int[][]{
            {matrix[bestRow][bestCol],matrix[bestRow][bestCol+1]},
            {matrix[bestRow+1][bestCol],matrix[bestRow+1][bestCol+1]}
    };
}

    public static void printMatrix(int[][] matrix) {
        for (int[] arr : matrix) {
            for (int element : arr) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void printCharMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char element : arr) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static int[] readArray(String line,String pattern) {
        return Arrays.stream(line.split(pattern))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static char[][] getMatricesIntersection(char[][] first, char[][] second) {
        char[][] out = new char[first.length][];

        for (int row = 0; row < first.length; row++) {
            out[row]= new char[first[row].length];
            for (int col = 0; col < first[row].length; col++) {
                out[row][col] = first[row][col] == second[row][col] ? first[row][col] : '*';
            }
        }
        return out;
    }

    public static int[][] readMatrix(int rows, int cols, Scanner scanner,String pattern) {
        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = readArray(scanner.nextLine(),pattern);
        }
        return matrix;
    }

    public static char[][] readCharMatrix(int rows, int cols, Scanner scanner) {
        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] tokens = scanner.nextLine().split(" ");
            for (int col = 0; col < tokens.length; col++) {
                matrix[row][col] = tokens[col].charAt(0);
            }
        }
        return matrix;
    }

    private static int getElementsSum(int[][] matrix) {
        return Arrays.stream(matrix).flatMapToInt(Arrays::stream).sum();
    }
}
