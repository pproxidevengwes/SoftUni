package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<Double,Integer> nums= new LinkedHashMap<>();

        String[] command= scanner.nextLine().split("\\s+");
        for (int i = 0; i < command.length; i++) {
            nums.putIfAbsent(Double.parseDouble(command[i]), 0);
            nums.put(Double.parseDouble(command[i]),nums.get(Double.parseDouble(command[i]))+1);
        }
        nums.entrySet().forEach(e->{
            System.out.printf("%.1f -> %d%n", e.getKey(), e.getValue());
                }

        );
    }
}
