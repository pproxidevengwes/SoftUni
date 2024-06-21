package com.company;

import java.util.*;

public class SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        ArrayDeque<String> stack = new ArrayDeque<>();
        String text = "";
        for (int i = 0; i < n; i++) {
            String command = scanner.nextLine();
            if (command.split(" ")[0].equals("1")) {
                stack.push(text);
                text += command.split(" ")[1];
            } else if (command.split(" ")[0].equals("2")) {
                stack.push(text);
                int count = Integer.parseInt(command.split(" ")[1]);
                text = text.substring(0, text.length() - count);
            } else if (command.split(" ")[0].equals("3")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                System.out.println(text.charAt(index - 1));
            } else if (command.equals("4")) {
                text = stack.pop();
            }
        }
    }
}
