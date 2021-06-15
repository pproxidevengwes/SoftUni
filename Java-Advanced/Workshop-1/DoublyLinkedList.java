import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoublyLinkedList {
    private class Node {
        private int element;
        private Node next;
        private Node prev;

        private Node(int element) {
            this.element = element;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int element) {
        Node newNode = new Node(element);
        if (this.size == 0) {
            this.head = this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.size++;
    }

    public void addLast(int element) {
        Node newNode = new Node(element);
        if (this.size == 0) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.size++;
    }

    public int removeFirst() throws Exception {
        if (this.size == 0) {
            throw new NoSuchElementException("Can't remove element from empty collection");
        } else if (this.size == 1) {
            Node firstElement = this.head;
            this.head = null;
            this.tail = null;
            this.size = 0;
            return firstElement.element;
        } else {
            Node firstElement = this.head;
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return firstElement.element;
        }
    }

    public int removeLast() throws Exception {
        if (this.size == 0) {
            throw new NoSuchElementException("Can't remove element from empty collection");
        } else if (this.size == 1) {
            Node lastElement = this.tail;
            this.head = null;
            this.tail = null;
            this.size = 0;
            return lastElement.element;
        } else {
            Node lastElement = this.tail;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
            return lastElement.element;
        }
    }

    public void forEach(Consumer<Integer> consumer) {
        Node currentNode = this.head;
        while (currentNode != null) {
            consumer.accept(currentNode.element);
            currentNode = currentNode.next;
        }
    }

    public int[] toArray() {
        int[] arr = new int[this.size];
        Node currentNode = this.head;
        int index = 0;
        while (currentNode != null) {
            arr[index++] = currentNode.element;
            currentNode = currentNode.next;
        }
        return arr;
    }

    public int get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + "Size: " + this.size);
        } else {
            if (index < this.size / 2) {
                Node firstNode = this.head;
                for (int i = 0; i < index; i++) {
                    firstNode = firstNode.next;
                }
                return firstNode.element;
            } else {
                Node lastNode = this.tail;
                for (int i = this.size - 1; i > index; i--) {
                    lastNode = lastNode.prev;
                }
                return lastNode.element;
            }
        }
    }

}
