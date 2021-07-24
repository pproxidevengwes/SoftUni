package reflection;

import java.lang.reflect.Field;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Field[] fields = RichSoilLand.class.getDeclaredFields();

        while (!input.equals("HARVEST")) {
            if (input.equals("all")) {

                for (Field field : fields) {
                    System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());
                }
            } else {
                String finalInput = input;
                Arrays.stream(fields)
                        .filter(field -> Modifier.toString(field.getModifiers()).equals(finalInput))
                        .forEach(field -> System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));
            }
            input = scanner.nextLine();
        }
    }
}
