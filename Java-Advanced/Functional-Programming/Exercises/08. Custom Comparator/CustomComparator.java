package com.company;

import java.util.*;
import java.util.stream.Collectors;


public class CustomComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Comparator<Integer> comparator = ((f, s) -> {
            if(Math.abs(f) % 2 == 0 && Math.abs(s) % 2 == 1){
                return -1;
            } else if(Math.abs(f) % 2 == 1 && Math.abs(s) % 2 == 0){
                return 1;
            } else if((Math.abs(f) % 2 == 0 && Math.abs(s) % 2 == 0) || (Math.abs(f) % 2 == 1 && Math.abs(s) % 2 == 1)){
                if(f > s){
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        });
        nums.sort(comparator);
        nums.forEach(e -> System.out.print(e + " "));

    }
}
