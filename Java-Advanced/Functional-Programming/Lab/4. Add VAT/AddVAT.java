package com.company;

import java.util.*;
import java.util.function.Function;

public class AddVAT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Function<String, Double> mapWithVat = str -> Double.parseDouble(str) * 1.2;

        System.out.println("Prices with Vat");
        Arrays.stream(scanner.nextLine().split(", ")).map(mapWithVat)
                .forEach(e -> System.out.printf("%.2f%n", e));
    }
}
