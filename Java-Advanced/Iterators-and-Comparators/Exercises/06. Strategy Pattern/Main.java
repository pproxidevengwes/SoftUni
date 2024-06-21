import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Person> set1 = new TreeSet<>(new PersonComparatorByNameLength());
        Set<Person> set2 = new TreeSet<>(new PersonComparatorByAge());

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
          
            Person person = new Person(name, age);
            set1.add(person);
            set2.add(person);
        }
      
        set1.forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
        set2.forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
    }
}
