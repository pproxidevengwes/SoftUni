package student.system;

import java.util.HashMap;
import java.util.Map;

public class StudentsRepository {
    private Map<String, Student> studentsByName;

    public StudentsRepository() {
        this.studentsByName = new HashMap<>();
    }

    public void add(Student student) {
        this.studentsByName.put(student.getName(),student);
    }

    public Student get(String name){
        return this.studentsByName.get(name);
    }

    public boolean contains(String name){
        return studentsByName.containsKey(name);
    }
}
