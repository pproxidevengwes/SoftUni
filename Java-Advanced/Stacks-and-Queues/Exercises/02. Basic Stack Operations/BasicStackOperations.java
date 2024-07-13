package com.company;

import java.util.*;

public class BasicStackOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt(); //elemets to push
        int s= scanner.nextInt(); //elements to pop
        int x= scanner.nextInt(); //check

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            stack.push(scanner.nextInt());
        }
        for (int i = 1; i <= s; i++) {
            stack.pop();
        }
        if (stack.contains(x)) {
            System.out.println("true");
        }else {
            if (!stack.isEmpty()) {

                System.out.println(Collections.min(stack));
            }else {
                System.out.println(0);
            }
        }

    }
}
