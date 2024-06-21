public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void increaseSalary(double bonus) {
        if (this.getAge() < 30) {
            bonus /= 2;
        }
        this.setSalary(this.salary *= (1.0 + bonus / 100));
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %s leva", this.getFirstName(), getLastName(), getSalary());
    }
}
