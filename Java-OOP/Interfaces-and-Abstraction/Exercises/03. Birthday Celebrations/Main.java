import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Birthable> validClasses = new ArrayList<>();
        String line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] tokens = line.split("\\s+");
            String clazz = tokens[0];
            if (clazz.equals("Citizen")) {
                validClasses.add(new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
            } else if (clazz.equals("ex2.Pet")) {
                validClasses.add(new Pet(tokens[1], tokens[2]));
            }
            line = scanner.nextLine();
        }
        String targetYear = scanner.nextLine();
        validClasses.stream().filter(p -> p.getBirthDate().contains(targetYear))
                .forEach(p -> System.out.println(p.getBirthDate()));
    }
}
