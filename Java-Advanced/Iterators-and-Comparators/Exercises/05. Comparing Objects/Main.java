import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>();

        String line = scanner.nextLine();
        while (!line.equals("END")) {
            String[] tokens = line.split("\\s+");

            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            people.add(person);
            line = scanner.nextLine();
        }
        int n = Integer.parseInt(scanner.nextLine());
        Person searched = people.get(n - 1);
        int countEquals = 0;
        for (Person p : people) {
            if (searched.compareTo(p) == 0) {
                countEquals++;
            }
        }
        if (countEquals == 1) {
            System.out.println("No matches");
        } else {
            System.out.println(countEquals + " " + (people.size() - countEquals) + " " + people.size());
        }
    }

}
