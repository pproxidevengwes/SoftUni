package com.company;

import java.util.*;
import java.util.function.Consumer;

public class CustomMinFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Consumer<int[]> findMinEl = arr ->
                System.out.println(Arrays.stream(arr).min().orElse(0));
        findMinEl.accept(nums);
    }
}
