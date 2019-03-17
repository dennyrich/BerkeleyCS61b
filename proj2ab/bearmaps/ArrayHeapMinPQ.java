package bearmaps;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private class PriorityNode<T> implements Comparable<T> {
        double priority;
        T item;


        private PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public boolean equals(Object other) {
            if (other.getClass().equals(other.getClass())) {
                return this.item == ((PriorityNode<T>) other).item;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }

        @Override
        public int compareTo(Object other) {
            PriorityNode<T> otherT = (PriorityNode<T>) other;
            return Double.compare(priority, otherT.priority);
        }
    }

    private List<PriorityNode<T>> minHeap;
    private int size;
    private Map<T, Integer> indices;

    public ArrayHeapMinPQ() {
        minHeap = new ArrayList<>();
        size = 0;
        indices = new HashMap<>();
    }
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item already exists");
        }
        size++;
        // initial index is size - 1
        indices.put(item, size - 1);
        PriorityNode<T> addition = new PriorityNode<>(item, priority);
        minHeap.add(addition);
        if (size > 1) {
            // the node, current position (at end of list)
            swimUp(addition, size - 1);
        }
    }
    /* Returns true if the PQ contains the given item. */

    @Override
    public boolean contains(T item) {
        return indices.containsKey(item);
    }


    //private boolean containsHelper(T )
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */

    @Override
    public T getSmallest() {
        return minHeap.get(0).item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() { //
        size--;
        if (size <= 1) {
            T smallest = minHeap.remove(0).item;
            indices.remove(smallest);
            return smallest;
        }
        // swap smallest with greatest, then swimdown
        else {
            swap(0, size - 1);
            PriorityNode<T> newTop = minHeap.get(0);
            PriorityNode<T> smallest = minHeap.remove(size - 1);
            indices.remove(smallest.item);
            swimDown(newTop, 0);
            return smallest.item;
        }
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
        Double oldPriority = getPriority(item);
        if (oldPriority == null) {
            throw new java.util.NoSuchElementException();
        }

        int index = getIndex(item);
        PriorityNode<T> node = minHeap.get(index);
        node.priority = priority;
        indices.put(item, size - 1);
        if (priority < minHeap.get(parent(index)).priority) {
            swimUp(node, index);
        } else {
            swimDown(node, index);
        }
    }

    // returns priority of node at index mapped by the given item
    private double getPriority(T item) {
        Integer index = getIndex(item);
        if (index == null) {
            throw new IllegalArgumentException();
        }
        return minHeap.get(index).priority;
    }

    private Integer getIndex(T item) {
        return indices.get(item);
    }

    private void swimUp(PriorityNode<T> n, int index) {
        while (parent(index) >= 0 && lessThan(index, parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void swimDown(PriorityNode<T> n, int index) {
        while (left(index) < size) {
            int child = left(index);
            if (child < size - 1 && greater(child, child + 1))
                child++; //goes to right child
            if (!greater(index, child))
                break;
            swap(index, child);
            index= child;
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
        indices.put(n1.item, pos2);
        indices.put(n2.item, pos1);
        minHeap.set(pos1, n2);
        minHeap.set(pos2, n1);
    }

    private boolean greater(int pos1, int pos2) {
        return minHeap.get(pos1).compareTo(minHeap.get(pos2)) > 0;
    }

    private boolean lessThan(int pos1, int pos2) {
        return minHeap.get(pos1).compareTo(minHeap.get(pos2)) < 0;
    }

    void printFancy() {
        T[] arr = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = minHeap.get(i).item;
            System.out.println(arr[i]);
        }
        PrintHeapDemo.printFancyHeapDrawing(arr);
    }


}
