package bearmaps;
import java.util.List;
import java.util.ArrayList;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class PriorityNode<T> implements Comparable<T> {
        PriorityNode<T> left;
        PriorityNode<T> right;
        double priority;
        T item;

        private PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        private PriorityNode(T item) {
            this(item, 0);
        }

        @Override
        public boolean equals(Object other) {
            if (other.getClass().equals(other.getClass())) {
                return this.item == ((PriorityNode<T>) other).item;
            }
            return false;
        }
        @Override
        public int compareTo(Object other) {
            PriorityNode<T> otherT = (PriorityNode<T>) other;
            return Double.compare(priority, otherT.priority);
        }
    }
    private List<PriorityNode<T>> minHeap;
    private int size;
    PriorityNode min;

    public ArrayHeapMinPQ() {
        minHeap = new ArrayList<>();
        size = 0;
    }
    @Override
    public void add(T item, double priority) {
        if (minHeap.contains(new PriorityNode(item))) {
            throw new IllegalArgumentException("Item already exists");
        }
        size++;
        PriorityNode<T> addition = new PriorityNode<>(item, priority);
        minHeap.add(addition);
        swimUp(addition);
    }
    /* Returns true if the PQ contains the given item. */

    @Override
    public boolean contains(T item) {
        return minHeap.contains(new PriorityNode<>(item));
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */

    @Override
    public T getSmallest() {
        return minHeap.get(0).item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        T smallest = minHeap.remove(0).item;
        PriorityNode<T> newTop = minHeap.remove(minHeap.size() - 1);
        minHeap.add(0, newTop); //places last item at from
        return smallest;
    }
    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        for (PriorityNode<T> node : minHeap) {
            if (node.item.equals(item)) {
                node.priority = priority;
                return;
            }
        }
        throw new java.util.NoSuchElementException();
    }

    private void swimUp(PriorityNode<T> n) {
        int index = size - 1;
        while (parent(index) >= 0 && lessThan(index, parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void swimDown(PriorityNode<T> n) {
        int index = 0;
        // while passed in node is greater than left or right
        while (left(index) < size - 1 &&
                    greater(index, left(index)) ||
                    greater(index, right(index))) {
            if (n.compareTo(minHeap.get(left(index))) > 0) {
                swap(index, left(index));
                index = left(index);
            } else if (n.compareTo(minHeap.get(right(index))) > 0) {
                swap(index, right(index));
                index = right(index);
            } else {
                System.out.println("something went wrong (swimDown)");
            }
        }
    }

    private int parent(int position) {
        return (position - 1) / 2;
    }

    private int left(int position) {
        return position * 2 + 1;
    }

    private int right(int position) {
        return position * 2 + 2;
    }

    private void swap(int pos1, int pos2) {
        PriorityNode<T> n1 = minHeap.get(pos1);
        PriorityNode<T> n2 = minHeap.get(pos2);
        minHeap.remove(pos1);
        minHeap.add(pos1, n2);
        minHeap.remove(pos2);
        minHeap.add(pos2, n1);
    }

    private boolean greater(int pos1, int pos2) {
        return minHeap.get(pos1).compareTo(minHeap.get(pos2)) > 0;
    }

    private boolean lessThan(int pos1, int pos2) {
        return minHeap.get(pos1).compareTo(minHeap.get(pos2)) < 0;
    }

    void printFancy() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = minHeap.get(i).item;
        }
        PrintHeapDemo.printFancyHeapDrawing(arr);
    }

}
