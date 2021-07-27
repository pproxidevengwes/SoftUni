package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Constructor<?>[] constructors = BlackBoxInt.class.getDeclaredConstructors();

        BlackBoxInt blackBoxInt = null;
        int index = 0;

        for (Constructor<?> constructor : constructors) {
            
            if (constructor.getParameterCount() == 0) {
                try {
                    constructor.setAccessible(true);
                    blackBoxInt = (BlackBoxInt) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }

        String line = scanner.nextLine();

        Method[] methods = BlackBoxInt.class.getDeclaredMethods();
        Field innerValue = null;
        try {
            innerValue = BlackBoxInt.class.getDeclaredField("innerValue");
            innerValue.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        while (!line.equals("END")) {
            String[] tokens = line.split("_");
            String name = tokens[0];
            int p = Integer.parseInt(tokens[1]);

            Method method = Arrays.stream(methods)
                    .filter(m -> m.getName().equals(name))
                    .findFirst().orElse(null);

            if (method != null) {
                try {
                    method.setAccessible(true);
                    method.invoke(blackBoxInt, p);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }

            if (innerValue != null) {
                try {
                    System.out.println(innerValue.get(blackBoxInt));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            line = scanner.nextLine();
        }
    }
}
