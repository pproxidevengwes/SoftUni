package animals;

public class Animal {
    protected String name;
    protected int age;
    protected String gender;

    public Animal(String name, int age, String gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validateStringInput(name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        Validator.validateAge(age);
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        Validator.validateStringInput(gender);
        this.gender = gender;
    }

    public String produceSound() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName())
                .append(System.lineSeparator());
        sb.append(this.getName()).append(" ")
                .append(this.getAge()).append(" ")
                .append(this.getGender())
                .append(System.lineSeparator());
        sb.append(produceSound());
        return sb.toString();
    }
}
