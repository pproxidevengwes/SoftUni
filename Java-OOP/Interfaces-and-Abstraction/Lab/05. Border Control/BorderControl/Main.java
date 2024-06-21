package borderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Identifiable> identifiables = new ArrayList<>();

        String line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] tokens = line.split("\\s+");
            Identifiable identifiable;
            if (tokens.length == 2) {
                identifiable = new Robot(tokens[0], tokens[1]);
            } else {
                identifiable = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            }
            identifiables.add(identifiable);
            line = scanner.nextLine();
        }

        String fakeId = scanner.nextLine();

        for (Identifiable identifiable : identifiables) {
            if (identifiable.getId().endsWith(fakeId)) {
                System.out.println(identifiable.getId());
            }
        }
    }
}
