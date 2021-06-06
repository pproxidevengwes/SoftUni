package com.company;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .filter(e -> e % 2 == 0)
                .toArray();
        Function<IntStream, String> formatted = str -> str
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));

        String funcOutput = formatted.apply(Arrays.stream(numbers));
        System.out.println(funcOutput);
        String sorted = formatted.apply(Arrays.stream(numbers).sorted());
        System.out.println(sorted);
    }
}
