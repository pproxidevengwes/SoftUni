import java.util.ArrayList;
import java.util.List;

public class Box<T extends Comparable<T>> {
    private final List<T> data;

    public Box() {
        this.data = new ArrayList<>();
    }

    public int countOfBiggerElements(T element) {
        int counter = 0;
        for (T elementFromData : data) {
            if (elementFromData.compareTo(element) >= 1) {
                counter++;
            }
        }
        return counter;
    }

    public void add(T element) {
        this.data.add(element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T element : data) {
            sb.append(element.getClass().getName())
                    .append(": ")
                    .append(element)
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
