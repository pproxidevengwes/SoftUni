package com.company;

import java.util.*;

public class ReverseNumbersWithAStack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        String[] split=input.split("\\s+");
        for (String s : split) {
            stack.push(Integer.parseInt(s));
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }
}
