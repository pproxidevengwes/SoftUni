package validPerson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Person> people = new ArrayList<>();

        String firstName=reader.readLine();
        while (!firstName.equals("end")) {
            String lastName = reader.readLine();
            int age = Integer.parseInt(reader.readLine());

            try {
                Person person = new Person(firstName, lastName, age);
                people.add(person);
            } catch (IllegalArgumentException exception) {
                System.out.println("Exception thrown: " + exception.getMessage());
            }

            firstName=reader.readLine();
        }

        people.forEach(person -> System.out.println(person.toString()));
    }
}
