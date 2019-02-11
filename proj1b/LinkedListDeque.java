
public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        T item;
        Node next;
        Node prev;

        Node(T i) {
            item = i;
            next = null;
            prev = null;
        }
    }

    private int size;
    private Node sentinal;

    public LinkedListDeque() {
        size = 0;
        sentinal = new Node(null);
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
    }
    public LinkedListDeque(LinkedListDeque other) {
        this();
        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i));
        }
    }
    @Override
    public void addFirst(T item) {
        size += 1;
        Node newFirst = new Node(item); //create new node
        Node oldFirst = sentinal.next;
        oldFirst.prev = newFirst;
        newFirst.next = oldFirst;
        newFirst.prev = sentinal;
        sentinal.next = newFirst;
    }
    @Override
    public void addLast(T item) {
        size += 1;
        Node newLast = new Node(item);
        Node oldLast = sentinal.prev;
        oldLast.next = newLast;
        newLast.prev = oldLast;
        newLast.next = sentinal;
        sentinal.prev = newLast;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(getRecursive(i) + " ");
        }
        System.out.println();
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node oldLast = sentinal.prev;
        Node newLast = oldLast.prev;
        newLast.next = sentinal;
        sentinal.prev = newLast;
        return oldLast.item;
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node oldFirst = sentinal.next;
        Node newFirst = oldFirst.next;
        newFirst.prev = sentinal;
        sentinal.next = newFirst;
        return oldFirst.item;
    }
    @Override
    public T get(int index) {
        Node curr = sentinal.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }
    public T getRecursive(int index) {
        return getRecursionHelper(index, sentinal.next);
    }
    private T getRecursionHelper(int index, Node n) {
        if (index == 0) {
            return n.item;
        } else {
            return getRecursionHelper(index - 1, n.next);
        }
    }
}
