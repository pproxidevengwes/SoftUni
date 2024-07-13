package com.company;

import java.util.*;

public class BalancedParentheses {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        boolean areBalanced = false;
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == '(' || current == '[' || current == '{') {
                stack.push(current);
            } else if (current == ')' || current == ']' || current == '}') {
                if (stack.isEmpty()) {
                    areBalanced=false;
                    break;
                }
                char lastOpen = stack.pop();
                if (lastOpen == '(' && current == ')') {
                    areBalanced = true;
                } else if (lastOpen == '[' && current == ']') {
                    areBalanced = true;

                } else if (lastOpen == '{' && current == '}') {
                    areBalanced = true;

                }else {
                    areBalanced=false;
                    break;
                }
            }
        }
        if (areBalanced) {

            System.out.println("YES");
        } else {

            System.out.println("NO");
        }
    }

}
