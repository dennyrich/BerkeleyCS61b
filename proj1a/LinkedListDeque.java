public class LinkedListDeque <T> {
    private class Node {
        T item;
        Node next;
        Node prev;

        public Node(T i) {
            item = i;
            next = null;
            prev = null;
        }
    }

    int size;
    Node sentinal;

    public LinkedListDeque() {
        size = 0;
        sentinal = new Node(null);
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
    }
    public LinkedListDeque(LinkedListDeque other) {
        sentinal = new Node(null);
        sentinal.prev = sentinal;
        sentinal.next = sentinal;
        for (int i = 0; i < size; i ++) {
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        size += 1;
        Node newFirst = new Node(item); //create new node
        newFirst.prev = sentinal;
        newFirst.next = sentinal.next;
        sentinal.next = newFirst;

    }
    public void addLast(T item) {
        size += 1;
        Node newLast = new Node(item); //create new node
        newLast.prev = sentinal.prev;
        newLast.next = sentinal;
        sentinal.prev = newLast;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i ++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    public T removeLast() {
        size -= 1;
        Node l = sentinal.prev;
        sentinal.prev.prev.next = sentinal;
        sentinal.prev = sentinal.prev.prev;
        return l.item;
    }
    public T removeFirst() {
        size -= 1;
        Node f = sentinal.next;
        sentinal.next.next.prev = sentinal;
        sentinal.next = sentinal.next.next;
        return f.item;
    }
    public T get(int index) {
        Node curr = sentinal.next;
        for (int i = 0; i < index; i ++) {
            curr = curr.next;

        }
        return curr.item;
    }
    public T getRecursive(int index) {
        return getRHelper(sentinal.next, index);
    }
    private T getRHelper(Node curr, int index) {
        if (curr == null || index == 0) {
            return curr.item;
        } else {
            return getRHelper(curr.next, index - 1);
        }
    }
}
