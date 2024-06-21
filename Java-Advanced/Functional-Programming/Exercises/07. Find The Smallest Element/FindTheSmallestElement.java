package com.company;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class FindTheSmallestElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer>nums= Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Consumer<List<Integer>>printMinElement=list->{
            int min=Integer.MAX_VALUE;
            int index=-1;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)<=min) {
                    min=list.get(i);
                    index=i;
                }
            }
            System.out.println(index);
        };
        printMinElement.accept(nums);
    }
}
