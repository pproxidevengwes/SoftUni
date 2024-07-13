package com.company;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class AppliedArithmetics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Function<int[], int[]> add = arr -> Arrays.stream(arr).map(e -> e + 1).toArray();

        Function<int[], int[]> multiply = arr -> Arrays.stream(arr).map(e -> e * 2).toArray();

        Function<int[], int[]> substract = arr -> Arrays.stream(arr).map(e -> e - 1).toArray();

        Consumer<int[]> print = arr ->
                Arrays.stream(arr).forEach(e -> System.out.print(e + " "));

        String line = scanner.nextLine();
        while (!line.equals("end")) {
            switch (line) {
                case "add":
                    nums = add.apply(nums);
                    break;
                case "multiply":
                    nums = multiply.apply(nums);
                    break;
                case "subtract":
                    nums = substract.apply(nums);
                    break;
                case "print":
                    print.accept(nums);
                    System.out.println();
                    break;
            }
            line = scanner.nextLine();
        }
    }
}
