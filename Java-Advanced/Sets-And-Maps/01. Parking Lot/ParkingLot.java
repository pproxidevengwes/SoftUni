package com.company;

import java.util.*;

public class ParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashSet<String> parkingLot = new LinkedHashSet<>();
        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String car = input.split("\\s+")[1];
            if (input.contains("IN")) {
                parkingLot.add(car);
            } else {
                parkingLot.remove(car);
            }
            input = scanner.nextLine();
        }
        if (parkingLot.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            System.out.println(String.join(System.lineSeparator(), parkingLot));
        }
    }
}
