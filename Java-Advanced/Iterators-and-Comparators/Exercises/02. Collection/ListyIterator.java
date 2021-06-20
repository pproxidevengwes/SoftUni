import java.util.Iterator;
import java.util.List;

public class ListyIterator implements Iterable<String> {

    private class ElementsIterator implements Iterator<String> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return this.index < data.size();
        }

        @Override
        public String next() {
            return data.get(this.index++);
        }
    }

    private List<String> data;
    private int index = 0;

    public ListyIterator(List<String> data) {
        this.data = data;
    }

    public boolean move() {
        if (hasNext()) {
            index++;
            return true;
        }
        return false;
    }

    public boolean hasNext() {
        return index < data.size() - 1;
    }

    public void print() {
        validator();
        System.out.println(this.data.get(this.index));
    }

    public void validator() {
        if (this.data.isEmpty()) {
            throw new IllegalStateException("Invalid Operation!");
        }
    }

    @Override
    public Iterator<String> iterator() {
        validator();
        return new ElementsIterator();
    }
}
