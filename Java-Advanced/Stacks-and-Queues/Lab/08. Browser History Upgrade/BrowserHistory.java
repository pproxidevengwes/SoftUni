package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> history = new ArrayDeque<>();
        ArrayDeque<String> forwards = new ArrayDeque<>();

        String line = scanner.nextLine();

        String currentURL = "";

        while (!line.equals("Home")) {
            if (line.equals("back")) {
                if (!history.isEmpty()) {
                    forwards.push(currentURL);
                    currentURL = history.pop();
                } else {
                    System.out.println("no previous URLs");
                    line = scanner.nextLine();
                    continue;
                }
            } else if (line.equals("forward")) {
                if (!forwards.isEmpty()) {
                    history.push(currentURL);
                    currentURL = forwards.pop();
                } else {
                    System.out.println("no next URLs");
                    line = scanner.nextLine();
                    continue;
                }
            } else {
                if (!currentURL.equals("")) {
                    history.push(currentURL);
                    if (!forwards.isEmpty()) {
                        forwards.clear();
                    }
                }
                currentURL = line;
            }
            System.out.println(currentURL);
            line = scanner.nextLine();
        }
    }
}
