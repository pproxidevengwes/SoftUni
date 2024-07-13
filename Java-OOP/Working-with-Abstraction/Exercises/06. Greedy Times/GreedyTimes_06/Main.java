package GreedyTimes_06;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long capacity = Long.parseLong(scanner.nextLine());

        Bag bag = new Bag(capacity);

        String[] input = scanner.nextLine().split("\\s+");

        for (int i = 0; i < input.length; i += 2) {
            String item = input[i];
            long weight = Long.parseLong(input[i + 1]);

            if (item.length() == 3) {
                bag.addCash(item, weight);
            } else if (item.toLowerCase(Locale.ROOT).endsWith("gem") && item.length() > 3) {
                bag.addGems(item, weight);
            } else if (item.equalsIgnoreCase("gold")) {
                bag.addGold(weight);
            }
        }
        System.out.println(bag.toString());
    }
}
