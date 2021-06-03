package com.company;

import java.util.*;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        char[][] first = readMatrix(rows, cols, scanner);
        char[][] second = readMatrix(rows, cols, scanner);

        char[][]result=getMatrixesIntersection(first, second);
        printMatrix(result);
    }
    public static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char element : arr) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private static char[][] getMatrixesIntersection(char[][] first, char[][] second) {
        char[][] out = new char[first.length][];

        for (int row = 0; row < first.length; row++) {
            out[row]= new char[first[row].length];
            for (int col = 0; col < first[row].length; col++) {
                out[row][col] = first[row][col] == second[row][col] ? first[row][col] : '*';
            }
        }
        return out;
    }


    public static char[][] readMatrix(int rows, int cols, Scanner scanner) {
        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] tokens = scanner.nextLine().split(" ");
            for (int col = 0; col < tokens.length; col++) {
                matrix[row][col] = tokens[col].charAt(0);
            }
        }
        return matrix;
    }
}
