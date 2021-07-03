package student.system;

public class Student {
    private final String name;
    private final int age;
    private final double grade;
    private String comment;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.setComment();
    }

    public void setComment() {
        if (getGrade() >= 5.00) {
            comment = "Excellent student.";
        } else if (getGrade() >= 3.50) {
            comment = "Very nice person";
        } else {
            comment = "Very nice person.";
        }
    }

    public String getInfo() {
        return String.format("%s is %s years old. %s", name, age, comment);
    }

    public String getName() {
        return this.name;
    }

    public double getGrade() {
        return this.grade;
    }

}
