package com.company;

import java.util.*;

public class FindTheRealQueen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] chessboard = readCharMatrix(8, 8, scanner);
        for (int row = 0; row < chessboard.length; row++) {
            for (int col = 0; col < chessboard[row].length; col++) {
                if (chessboard[row][col] == 'q' && isValidQueen(row, col, chessboard)) {
                    System.out.println(row + " " + col);
                }
            }
        }
    }

    private static boolean isValidQueen(int row, int col, char[][] chessboard) {
        for (int rowDirection = -1; rowDirection <= 1; rowDirection++) {
            for (int colDirection = -1; colDirection <= 1; colDirection++) {
                if (rowDirection == 0 && colDirection == 0) {
                    continue;
                }
                int currentRow = row + rowDirection;
                int currentCol = col + colDirection;
                boolean valid = isValidPosition(chessboard, currentRow, currentCol);
                while (valid) {
                    if ('q' == chessboard[currentRow][currentCol]) {
                        return false;
                    }
                    currentRow += rowDirection;
                    currentCol += colDirection;
                    valid = isValidPosition(chessboard, currentRow, currentCol);
                }
            }
        }

        return true;
    }

    private static boolean isValidPosition(char[][] chessboard, int currentRow, int currentCol) {
        return currentRow >= 0 && currentRow < chessboard.length && currentCol >= 0 && currentCol < chessboard.length;
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
}
