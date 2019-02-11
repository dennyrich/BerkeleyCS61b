public  interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    T removeLast();
    T removeFirst();
    T get(int i);
    int size();
    void printDeque();
    default boolean isEmpty() {
        return size() == 0;
    }
}
