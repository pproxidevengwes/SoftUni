public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public void setName(String name) {
        if (name.length() < 1 || name.equals(" ")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0 || 15 < age) {
            throw new IllegalArgumentException("Age should be between 0 and 15");
        }
        this.age = age;
    }

    public double productPerDay() {
        return calculateProductPerDay();
    }

    private double calculateProductPerDay() {
        if (0 <= this.age && this.age <= 5) {
            return 2.00;
        } else if (6 <= this.age && this.age <= 11) {
            return 1.00;
        }
        return 0.75;
    }

    private String getName() {
        return this.name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.", this.getName(), this.getAge(), productPerDay());
    }
}
