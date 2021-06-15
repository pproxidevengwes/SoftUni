import java.util.function.Consumer;

public class SmartArray {
    private static final int INITIAL_CAPACITY = 4;
    private int[] elements;
    private int size;

    public SmartArray() {
        this.elements = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void add(int element) {
        if (size == elements.length) {
            elements = grow();
        }
        this.elements[size++] = element;
    }

    private int[] grow() {
        int[] newElements = new int[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        return newElements;
    }

    public int get(int index) {
        ensure(index);
        return this.elements[index];
    }

    public int size() {
        return this.size;
    }

    public int remove(int index) {
        int element = get(index);
        for (int i = index; i < this.size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--this.size] = 0;
        if (this.size == 0) {
            this.elements = new int[INITIAL_CAPACITY];
        }
        if (this.size <= elements.length / 4 && elements.length > INITIAL_CAPACITY) {
            this.elements = shrink();
        }
        return element;
    }

    private int[] shrink() {
        int[] newElements = new int[elements.length / 2];
        System.arraycopy(elements, 0, newElements, 0, this.size);
        return newElements;
    }

    private void ensure(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    public boolean contains(int element) {
        for (int e : elements) {
            if (e == element) {
                return true;
            }
        }
        return false;
    }

    public void add(int index, int element) {
        ensure(index);
        int lastElement = elements[size - 1];
        for (int i = this.size - 1; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        add(lastElement);
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < this.size; i++) {
            consumer.accept(elements[i]);
        }
    }

}
