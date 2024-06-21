package com.company;

import java.util.*;

public class MathPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(scanner.nextLine());
        int count = 1;

        ArrayDeque<String> children = new ArrayDeque<>();

        Collections.addAll(children, names);

        while (children.size() > 1) {
            for (int i = 0; i < n - 1; i++) {
                children.offer(children.poll());
            }
            if (isPrime(count)) {
                System.out.println("Prime " + children.peek());
            } else {
                System.out.println("Removed " + children.poll());
            }
            count++;

        }
        System.out.println("Last is " + children.poll());
    }

    private static boolean isPrime(int number) {
        if (number == 1||number==0) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
