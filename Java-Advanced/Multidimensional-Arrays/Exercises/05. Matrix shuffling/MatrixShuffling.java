package com.company;

import java.util.*;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[]dimentions= Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = dimentions[0];
        int cols = dimentions[1];
        String[][] matrix = new String[rows][cols];
        fillMatrix(matrix, rows, cols, scanner);

        String command = scanner.nextLine();
        while (!command.equals("END")) {
            if (!validateCommand(command, rows, cols)) {
                System.out.println("Invalid input!");
            } else {
                String[] tokens = command.split("\\s+");
                int rowFirst = Integer.parseInt(tokens[1]);
                int colFirst = Integer.parseInt(tokens[2]);
                int rowSecond = Integer.parseInt(tokens[3]);
                int colSecond = Integer.parseInt(tokens[4]);

                String el1 = matrix[rowFirst][colFirst];
                String el2 = matrix[rowSecond][colSecond];

                matrix[rowFirst][colFirst] = el2;
                matrix[rowSecond][colSecond] = el1;

                printMatrix(matrix, rows, cols);
            }
            command = scanner.nextLine();
        }
    }


    private static boolean validateCommand(String command, int rows, int cols) {
        String[] tokens = command.split("\\s+");
        String name = tokens[0];
        if (!name.equals("swap") || tokens.length != 5) {
            return false;
        }
        int rowFirst = Integer.parseInt(tokens[1]);
        int colFirst = Integer.parseInt(tokens[2]);
        int rowSecond = Integer.parseInt(tokens[1]);
        int colSecond = Integer.parseInt(tokens[2]);

        if (rowFirst >= rows || colFirst >= cols || rowSecond >= rows || colSecond >= cols ||
                rowFirst < 0 || rowSecond < 0 || colFirst < 0 || colSecond < 0) {
            return false;
        }
        return true;
    }

    private static void fillMatrix(String[][] matrix, int rows, int cols, Scanner scanner) {
        for (int row = 0; row < rows; row++) {
            matrix[row]= scanner.nextLine().split("\\s+");

        }
    }

    public static void printMatrix(String[][] matrix, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
