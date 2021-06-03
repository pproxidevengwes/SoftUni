package com.company;

import java.util.*;

public class StringMatrixRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rotation = scanner.nextLine();
        int angle = Integer.parseInt(rotation.split("[()]")[1]) % 360;

        String input = scanner.nextLine();
        List<String> lines = new ArrayList<>();
        int maxLength = input.length();

        while (!input.equals("END")) {
            lines.add(input);
            input = scanner.nextLine();
            if (input.length() > maxLength) {
                maxLength = input.length();
            }
        }
        int rows = lines.size();
        int cols = maxLength;
        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (col < lines.get(row).length()) {
                    matrix[row][col] = lines.get(row).charAt(col);
                } else {
                    matrix[row][col] = ' ';
                }
            }
        }
        if (angle == 90) {
            rotate90(rows, cols, matrix);
        } else if (angle == 180) {
            rotate180(rows, cols, matrix);
        } else if (angle == 270) {
            rotate270(rows, cols, matrix);
        } else if (angle == 0) {
            rotate0(rows, cols, matrix);
        }
    }

    private static char[][] rotate270(int rows, int cols, char[][] matrix) {
        for (int col = cols - 1; col >= 0; col--) {
            for (int row = 0; row < rows; row++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
        return matrix;
    }

    private static char[][] rotate180(int rows, int cols, char[][] matrix) {
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = cols - 1; col >= 0; col--) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
        return matrix;
    }

    private static char[][] rotate90(int rows, int cols, char[][] matrix) {
        for (int col = 0; col < cols; col++) {
            for (int row = rows - 1; row >= 0; row--) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
        return matrix;
    }

    static char[][] rotate0(int rows, int cols, char[][] matrix) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }

        return matrix;
    }


}
