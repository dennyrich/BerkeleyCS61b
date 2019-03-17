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
    Map<T, Double> priorities;

    public ArrayHeapMinPQ() {
        minHeap = new ArrayList<>();
        size = 0;
        priorities = new HashMap<>();
    }
    @Override
    public void add(T item, double priority) {
        if (minHeap.contains(new PriorityNode(item))) {
            throw new IllegalArgumentException("Item already exists");
        }
        size++;
        priorities.put(item, priority);
        PriorityNode<T> addition = new PriorityNode<>(item, priority);
        minHeap.add(addition);
        swimUp(addition, size - 1);
    }
    /* Returns true if the PQ contains the given item. */

    @Override
    public boolean contains(T item) {
        return getPriority(item) != null;
    }


    //private boolean containsHelper(T )
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */

    @Override
    public T getSmallest() {
        return minHeap.get(0).item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        size--;
        T smallest = minHeap.remove(0).item;
        priorities.remove(smallest);
        if (size > 1) {
            PriorityNode<T> newTop = minHeap.remove(minHeap.size() - 1);
            minHeap.add(0, newTop); //places last item at from
            swimDown(newTop, 0);
        }
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
        Double oldPriority = getPriority(item);
        if (oldPriority == null) {
            throw new java.util.NoSuchElementException();
        }

        int index = get(oldPriority, 0);
        PriorityNode<T> node = minHeap.get(index);
        node.priority = priority;
        priorities.put(item, priority);
        if (priority > minHeap.get(parent(index)).priority) {
            swimUp(node, index);
        } else {
            swimDown(node, index);
        }
    }

    private int get(double priority, int position) {
        if (position > size) {
            return -1;
        } else if (priority < minHeap.get(position).priority) {
            return -1;
        }
        else if (priority == minHeap.get(position).priority) {
            return position;
        }
        else {
            int index = get(priority, left(position));
            if (index < 0) {
                index = get(priority, right(position));
            }
            return index;
        }
    }

    private void swimUp(PriorityNode<T> n, int index) {
        while (parent(index) >= 0 && lessThan(index, parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void swimDown(PriorityNode<T> n, int index) {
        // while passed in node is greater than left or right
//        while (left(index) < size - 1 &&
//                    greater(index, left(index)) ||
//                    greater(index, right(index))) {
//            if (n.compareTo(minHeap.get(left(index))) > 0) {
//                swap(index, left(index));
//                index = left(index);
//            } else if (n.compareTo(minHeap.get(right(index))) > 0) {
//                swap(index, right(index));
//                index = right(index);
//            } else {
//                System.out.println("something went wrong (swimDown)");
//            }
//        }
        while (left(index) < size) {
            int child = left(index);
            if (child < size - 1 && greater(child, child+1)) child++; //goes to right child
            if (!greater(index, child)) break;
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
        minHeap.set(pos1, n2);
        minHeap.set(pos2, n1);
    }

    private boolean greater(int pos1, int pos2) {
        return minHeap.get(pos1).compareTo(minHeap.get(pos2)) > 0;
    }

    private boolean lessThan(int pos1, int pos2) {
        return minHeap.get(pos1).compareTo(minHeap.get(pos2)) < 0;
    }

    private Double getPriority(T item) {
        return priorities.get(item);
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
