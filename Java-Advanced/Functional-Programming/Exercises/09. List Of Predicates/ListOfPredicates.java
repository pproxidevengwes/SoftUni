package com.company;

import java.util.*;
import java.util.function.Predicate;


public class ListOfPredicates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] nums = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Predicate<Integer> predicate = x -> {
            for (int num : nums) {
                if (x % num != 0) {
                    return false;
                }
            }
            return true;
        };
        getAllNumsInRange(n).stream().filter(predicate).forEach(e -> System.out.print(e + " "));
    }

    private static List<Integer> getAllNumsInRange(int n) {
        List<Integer> numsInRange = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numsInRange.add(i);
        }
        return numsInRange;

    }
}
