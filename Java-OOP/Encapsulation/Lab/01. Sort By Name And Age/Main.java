import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(reader.readLine());

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split("\\s+");
            people.add(new Person(input[0], input[1], Integer.parseInt(input[2])));
        }

        people.sort((a, b) -> {
            int result = a.getFirstName().compareTo(b.getFirstName());
            if (result != 0) {
                return result;
            } else {
                return Integer.compare(a.getAge(), b.getAge());
            }
        });
        for (Person person : people) {
            System.out.println(person.toString());
        }
    }
}
