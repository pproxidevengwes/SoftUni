import java.util.ArrayList;
import java.util.List;

public class CustomList<E extends Comparable<E>> {
    private List<E> data;

    public CustomList() {
        this.data = new ArrayList<>();
    }

    public void add(E element) {
        this.data.add(element);
    }

    public E remove(int index) {
        return this.data.remove(index);
    }

    public boolean contains(E element) {
        return this.data.contains(element);
    }

    public void swap(int firstIndex, int secondIndex) {
        E first = this.data.get(firstIndex);
        this.data.set(firstIndex, this.data.get(secondIndex));
        this.data.set(secondIndex, first);
    }

    public long countGreaterThan(E element) {
        return this.data.stream().filter(e -> e.compareTo(element) > 0).count();
    }

    public E getMax() {
        E maxElement = this.data.get(0);
        for (E element : data) {
            if (element.compareTo(maxElement) > 0) {
                maxElement = element;
            }
        }
        return maxElement;
    }

    public E getMin() {
        E minElement = this.data.get(0);
        for (E element : data) {
            if (element.compareTo(minElement) < 0) {
                minElement = element;
            }
        }
        return minElement;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E element : data) {
            sb.append(element.toString())
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
