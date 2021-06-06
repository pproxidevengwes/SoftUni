package com.company;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CountUppercaseWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Predicate<String> startsWitsCapitalLetter = str -> Character.isUpperCase(str.charAt(0));

        String[] words = Arrays.stream(scanner.nextLine().split(" "))
                .filter(startsWitsCapitalLetter)
                .toArray(String[]::new);
        System.out.println(words.length);
        Arrays.stream(words).forEach(System.out::println);

    }
}
