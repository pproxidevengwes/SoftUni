import java.util.function.Consumer;

public class Stack {
    private static class Node {
        private int element;
        private Node prev;

        public Node(int element) {
            this.element = element;
        }
    }

    private Node top;
    private int size;

    public Stack() {

    }

    public void push(int element) {
        Node newNode = new Node(element);
        if (this.top != null) {
            newNode.prev = this.top;
        }
        this.top = newNode;
        this.size++;
    }

    public int pop() throws IllegalAccessException {
        ensureNonEmpty();
        int res = this.top.element;
        this.top = this.top.prev;
        this.size--;
        return res;
    }

    private void ensureNonEmpty() throws IllegalAccessException {
        if (this.top != null) {
            throw new IllegalAccessException("Empty Stack");
        }
    }

    public int peek() throws IllegalAccessException {
        ensureNonEmpty();
        return this.top.element;
    }

    public Stack(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void forEach(Consumer<Integer> consumer) {
        Node current = this.top;
        while (current != null) {
            consumer.accept(current.element);
            current = current.prev;
        }
    }
}
