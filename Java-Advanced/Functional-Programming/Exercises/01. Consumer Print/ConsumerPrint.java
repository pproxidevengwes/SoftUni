package com.company;

import java.util.*;
import java.util.function.Consumer;

public class ConsumerPrint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");

        Consumer<String[]>printString=arr->{
            for (String name : arr) {
                System.out.println(name);
            }
        };
        printString.accept(input);
    }
}
