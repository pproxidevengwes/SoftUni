import java.util.ArrayList;
import java.util.List;

public class Box<T extends Comparable<T>> {
    private final List<T> data;

    public Box() {
        this.data = new ArrayList<>();
    }

    public void add(T element) {
        this.data.add(element);
    }

    public void swap(int firstIndex, int secondIndex) {
        T first = this.data.get(firstIndex);
        this.data.set(firstIndex, this.data.get(secondIndex));
        this.data.set(secondIndex, first);
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
