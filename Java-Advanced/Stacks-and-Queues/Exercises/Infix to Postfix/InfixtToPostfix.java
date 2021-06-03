package com.company;

import java.util.*;

public class InfixtToPostfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");

        ArrayDeque<String> num = new ArrayDeque<>();
        ArrayDeque<String> operator = new ArrayDeque<>();
        for (String s : input) {
            if (Character.isDigit(s.charAt(0)) || Character.isLetter(s.charAt(0))) {
                num.offer(s);
            } else {
                if (!operator.isEmpty()) {
                    String lastOperator = operator.peek();
                    switch (s) {
                        case "+":
                        case "-":
                            if (!lastOperator.equals("(")) {
                                num.offer(operator.pop());
                            }
                            operator.push(s);
                            break;
                        case "*":
                        case "/":
                            if (lastOperator.equals("*")||lastOperator.equals("/")) {
                                num.offer(operator.pop());
                            }
                            operator.push(s);
                            break;
                        case "(":
                            operator.push(s);
                            break;
                        case ")":
                            while (!operator.peek().equals("(")){
                                num.offer(operator.pop());
                            }
                            operator.pop();
                            break;
                    }
                }else {
                    operator.push(s);
                }
            }
        }
while (!num.isEmpty()){
    System.out.print(num.poll()+" ");
}
while (!operator.isEmpty()){
    System.out.print(operator.pop()+" ");
}
        System.out.println();
    }
}
