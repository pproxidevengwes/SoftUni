package com.company;

import java.util.*;

public class SoftUniParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashSet<String>VIPs= new LinkedHashSet<>();
        LinkedHashSet<String>regular= new LinkedHashSet<>();


        String command = scanner.nextLine();
        while (!command.equals("PARTY")) {
            if (Character.isDigit(command.charAt(0))) {
                VIPs.add(command);
            }else {
                regular.add(command);
            }
            command = scanner.nextLine();
        }
        TreeSet<String>allGuests=new TreeSet<>(VIPs);
        allGuests.addAll(regular);

        String input = scanner.nextLine();
        while (!input.equals("END")) {
allGuests.remove(input);
            input = scanner.nextLine();
        }
        System.out.println(allGuests.size());
        System.out.println(String.join(System.lineSeparator(), allGuests));
    }

}
