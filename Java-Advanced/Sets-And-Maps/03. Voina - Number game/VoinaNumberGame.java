package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class VoinaNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashSet<Integer> firstPlayer = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(LinkedHashSet::new));

        LinkedHashSet<Integer> secondPlayer = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(LinkedHashSet::new));

        int round = 0;
        while (round < 50) {
            round++;
            if (firstPlayer.isEmpty() || secondPlayer.isEmpty()) {
                break;
            }
            int cardOfFirst = firstPlayer.iterator().next();
            firstPlayer.remove(cardOfFirst);
            int cardOfSecond = secondPlayer.iterator().next();
            secondPlayer.remove(cardOfSecond);
            if (cardOfFirst > cardOfSecond) {
                firstPlayer.add(cardOfFirst);
                firstPlayer.add(cardOfSecond);
            } else if (cardOfSecond > cardOfFirst){
                secondPlayer.add(cardOfFirst);
                secondPlayer.add(cardOfSecond);
            }
        }

        if (firstPlayer.size() > secondPlayer.size()) {
            System.out.println("First player win!");
        } else if (secondPlayer.size() > firstPlayer.size()){
            System.out.println("Second player win!");
        } else {
            System.out.println("Draw!");
        }
    }
}
