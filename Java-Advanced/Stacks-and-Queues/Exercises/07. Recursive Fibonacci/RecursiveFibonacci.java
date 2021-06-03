package com.company;

import java.util.*;

public class RecursiveFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= Integer.parseInt(scanner.nextLine());
        System.out.println(recursiveFibonacci(n));


    }
    private static long recursiveFibonacci(int n) {
        long a = 0, b = 1, c;
        if (n == 0)
            return a;
        for (long i = 1; i <= n; i++)
        {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
