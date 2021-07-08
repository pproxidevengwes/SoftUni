package animals;

public class Validator {
    public static void validateStringInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    public static void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    public Validator() {
    }
}
