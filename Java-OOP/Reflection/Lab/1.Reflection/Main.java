package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Reflection.class;

        System.out.println(clazz);
        System.out.println(clazz.getSuperclass());

        Class<?>[] interfaces = clazz.getInterfaces();

        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }

        Constructor<Reflection> ctor = clazz.getDeclaredConstructor();

        Reflection reflection = ctor.newInstance();

        System.out.println(reflection.toString());
    }
}
