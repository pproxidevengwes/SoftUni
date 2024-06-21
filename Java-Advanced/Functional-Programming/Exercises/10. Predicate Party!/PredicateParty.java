package com.company;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class PredicateParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> guests = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());
        String line = scanner.nextLine();
        while (!line.equals("Party!")) {
            String command = line.split(" ")[0];
            String realCommand = line.split(" ")[1];
            int guestSize = guests.size();
            if (command.equals("Remove")) {

                switch (realCommand) {
                    case "StartsWith":
                        String letter = line.split(" ")[2];
                        Predicate<String> pr = x -> x.equals(letter);
                        guests.removeIf(guest -> pr.test(guest.substring(0, letter.length())));
                        break;
                    case "EndsWith":
                        String lastLetter = line.split(" ")[2];
                        Predicate<String> prForLastLet = x -> x.equals(lastLetter);
                        guests.removeIf(guest -> prForLastLet.test(guest.endsWith(lastLetter) + ""));
                        break;
                    case "Length":
                        int l = Integer.parseInt(line.split(" ")[2]);
                        Predicate<String> prForL = x -> x.length() == l;
                        guests.removeIf(prForL);
                        break;

                }
            } else if (command.equals("Double")) {
                switch (realCommand) {
                    case "StartsWith":
                        String letter = line.split(" ")[2];
                        Predicate<String> predicate = x -> x.equals(letter);
                        for (int i = 0; i < guestSize; i++) {
                            if (predicate.test(guests.get(i).substring(0, letter.length()))) {
                                guests.add(guests.get(i));
                            }
                        }
                        break;
                    case "EndsWith":
                        String lastLetter = line.split(" ")[2];
                        Predicate<String> predicateForLastLetter = x -> x.equals(lastLetter);
                        for (int i = 0; i < guestSize; i++) {
                            if (predicateForLastLetter.test(guests.get(i).substring(guests.get(i).length() - lastLetter.length()))) {
                                guests.add(guests.get(i));
                            }
                        }
                        break;
                    case "Length":
                        int length = Integer.parseInt(line.split(" ")[2]);
                        Predicate<String> predicateForLength = x -> x.length() == length;
                        for (int i = 0; i < guestSize; i++) {
                            if (predicateForLength.test(guests.get(i))) {
                                guests.add(guests.get(i));
                            }
                        }
                        break;

                }
            }
            line = scanner.nextLine();
        }
        if (guests.isEmpty()) {
            System.out.println("Nobody is going to the party!");
        } else {
            Collections.sort(guests);
            System.out.println(String.join(", ", guests) + " are going to the party!");
        }
    }
}
