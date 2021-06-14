import java.lang.reflect.Array;
import java.util.stream.IntStream;

public class ArrayCreator<T> {

    public static <T> T[] create(int length, T value) {
        return create(value.getClass(), length, value);
    }

    public static <T> T[] create(Class<?> clazz, int length, T value) {
        T[] arr = (T[]) Array.newInstance(clazz, length);
        IntStream.range(0, length)
                .forEach(e -> arr[e] = value);
        return arr;
    }

}
