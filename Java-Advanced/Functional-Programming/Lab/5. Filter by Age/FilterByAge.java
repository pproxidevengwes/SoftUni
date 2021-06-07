package com.company;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterByAge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Integer> people = new LinkedHashMap<>();

        while (n-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            people.put(name, age);
        }
        String ageCond = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());
        String format = scanner.nextLine();

        Predicate<Map.Entry<String, Integer>> ageFilter = getAgeFilterPredicate(age, ageCond);

        people.entrySet().stream()
                .filter(getAgeFilterPredicate(age, ageCond))
                .forEach(getFormatter(format));

    }

    private static Consumer<Map.Entry<String, Integer>> getFormatter(String format) {
        if (format.equals("name")) {
            return entry -> System.out.println(entry.getKey());
        } else if (format.equals("age")) {
            return entry -> System.out.println(entry.getValue());
        }
        return entry -> System.out.println(entry.getKey() + " - " + entry.getValue());
    }


    private static Predicate<Map.Entry<String, Integer>> getAgeFilterPredicate(int age, String ageCond) {
        if (ageCond.equals("older")) {
            return e -> e.getValue() >= age;
        }
        return e -> e.getValue() <= age;
    }
}
