package com.company;

import java.util.*;

public class TheHeiganDance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double damageToHeigan = Double.parseDouble(scanner.nextLine());
        int player = 18500;
        double heigan = 3000000;
        String currentSpell = "";

        int rows = 15;
        int cols = 15;
        int currentRow = 7;
        int currentCol = 7;

        while (true) {
            if (player >= 0) {
                heigan -= damageToHeigan;
            }
            if (currentSpell.equals("Cloud")) {
                player -= 3500;
            }
            if (heigan <= 0 || player <= 0) {
                break;
            }
            String[] input = scanner.nextLine().split("\\s+");
            String spell = input[0];
            int row = Integer.parseInt(input[1]);
            int col = Integer.parseInt(input[2]);

            if (spell.equals("Cloud") || spell.equals("Eruption")) {
                if (isDamage(row, col, currentRow, currentCol)) {

                    if (!isDamage(row, col, currentRow - 1, currentCol) && !isWall(currentRow - 1, rows)) {
                        currentRow--;
                        currentSpell = "";
                    } else if (!isDamage(row, col, currentRow, currentCol+1) && !isWall(currentCol + 1, cols)) {
                        currentCol++;
                        currentSpell = "";
                    } else if (!isDamage(row, col, currentRow + 1, currentCol) && !isWall(currentRow + 1, rows)) {
                        currentRow++;
                        currentSpell = "";

                    } else if (!isDamage(row, col, currentRow , currentCol- 1) && !isWall(currentCol - 1, cols)) {
                        currentCol--;
                        currentSpell = "";
                    } else {
                        if (spell.equals("Cloud")) {
                            player -= 3500;
                            currentSpell = "Cloud";
                        } else {
                            player -= 6000;
                            currentSpell = "Eruption";
                        }
                    }
                }
            }
        }
        if (heigan<=0) {
            System.out.println("Heigan: Defeated!");
        }else {
            System.out.printf("Heigan: %.2f%n", heigan);
        }
        if (player<=0) {
            System.out.printf("Player: Killed by %s%n", currentSpell.equals("Cloud") ? "Plague Cloud" : "Eruption");
        }else {
            System.out.printf("Player: %d%n", player);
        }
        System.out.printf("Final position: %d, %d%n", currentRow,currentCol);
    }

    private static boolean isWall(int move, int end) {
        return move < 0 || move >= end;

    }

    private static boolean isDamage(int row, int col, int currentRow, int currentCol) {
        return (row - 1 <= currentRow && currentRow <= row + 1) && (col - 1 <= currentCol && currentCol <= col + 1);

    }
}
