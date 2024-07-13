package com.company;

import java.util.*;
import java.util.function.Predicate;


public class PredicateForNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        String[] names = scanner.nextLine().split("\\s+");

        Predicate<String> checkLength = name -> name.length() <= length;
        Arrays.stream(names).filter(checkLength).forEach(System.out::println);

    }
}