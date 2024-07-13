package customException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        List<Student> studentList = new ArrayList<>();

        String name=reader.readLine();
        while (!name.equals("end")) {
            String email = reader.readLine();

            try {
                Student student = new Student(name, email);
                studentList.add(student);
            } catch (InvalidPersonNameException exception) {
                System.out.println("Exception thrown: " + exception.getMessage());
            }

            name=reader.readLine();
        }
        studentList.forEach(student -> System.out.println(student.toString()));
    }
}
