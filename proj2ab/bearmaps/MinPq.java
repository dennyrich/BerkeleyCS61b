package bearmaps;
import java.util.List;
import java.util.ArrayList;

public class MinPq<T> implements ExtrinsicMinPQ<T> {
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
        public int compareTo(Object other) {
            PriorityNode<T> otherT = (PriorityNode<T>) other;
            return Double.compare(priority, otherT.priority);
        }
    }
    private PriorityNode<T>[] minHeap;
    private int size;

    private int pointerF;
    private int pointerL;
    private static final int INITIAL_SIZE = 8;

    public MinPq() {
        minHeap = new PriorityNode[INITIAL_SIZE];
        size = 0;
        pointerF = 1;
        pointerL = 1;
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item already exists");
        }
        //size++; (done in add last)
        PriorityNode<T> addition = new PriorityNode<>(item, priority);
        addLast(addition);
        swimUp(addition);
    }
    /* Returns true if the PQ contains the given item. */

    @Override
    public boolean contains(T item) {
        return containsHelper(item, 0);
    }

    private boolean containsHelper(T item, int pos) {
        if (pos >= size || minHeap[0].compareTo(item) > 0) {
            return false;
        } else if (minHeap[pos].equals(item)) {
            return true;
        } else {
            return containsHelper(item, left(pos)) || containsHelper(item, right(pos));
        }
    }


    @Override
    public T getSmallest() {
        return minHeap[0].item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        //size--;
        T smallest = removeFirst().item;
        PriorityNode<T> newTop = removeLast();
        addFirst(newTop); //places last item at from
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
        int index = pointerL - 1;
        while (parent(index) >= pointerF && lessThan(index, parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void swimDown(PriorityNode<T> n) {
        int index = pointerF;
        // while passed in node is greater than left or right
        while (left(index) < pointerL &&
                greater(index, left(index)) ||
                greater(index, right(index))) {
            if (n.compareTo(minHeap[left(index)]) > 0) {
                swap(index, left(index));
                index = left(index);
            } else if (n.compareTo(minHeap[right(index)]) > 0) {
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
        PriorityNode<T> temp = minHeap[pos1];
        minHeap[pos1] = minHeap[pos2];
        minHeap[pos2] = temp;
    }

    private boolean greater(int pos1, int pos2) {
        return minHeap[pos1].compareTo(minHeap[pos2]) > 0;
    }

    private boolean lessThan(int pos1, int pos2) {
        return minHeap[pos1].compareTo(minHeap[pos2]) < 0;
    }

    void printFancy() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = minHeap[i].item;
        }
        PrintHeapDemo.printFancyHeapDrawing(arr);
    }

    private void addFirst(PriorityNode<T> item) {
        size += 1;
        if (size > minHeap.length) {
            resizeUp();
        }
        if (pointerF == 0) {
            pointerF = minHeap.length - 1;
        } else {
            pointerF -= 1;
        }
        minHeap[pointerF] = item;
    }


    private void addLast(PriorityNode<T> item) {
        size += 1;
        if (size > minHeap.length) {
            resizeUp();
        }
        minHeap[pointerL] = item;
        /* this moves the pointer to the begining */
        if (pointerL == minHeap.length - 1) {
            pointerL = 0;
        } else {
            pointerL += 1;
        }
    }

    private PriorityNode<T> removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        PriorityNode<T> item = minHeap[pointerF];
        /* this moves pointer back to 0 if at end */
        if (pointerF == minHeap.length - 1) {
            pointerF = 0;
        } else {
            pointerF += 1;
        }
        if (size < minHeap.length / 4 && size > INITIAL_SIZE) {
            resizeDown();
        }
        return item;
    }

    private PriorityNode<T> removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        /* this moves pointer back to end if at 0 */
        if (pointerL == 0) {
            pointerL = minHeap.length - 1;
        } else {
            pointerL -= 1;
        }
        PriorityNode<T> item = minHeap[pointerL];
        if (size < minHeap.length / 4 && size > INITIAL_SIZE) {
            resizeDown();
        }
        return item;
    }



    private void resizeUp() {
        PriorityNode<T>[] temp = (PriorityNode<T>[]) new Object[minHeap.length + minHeap.length / 2];
        int offset = temp.length - minHeap.length;
        for (int i = 0; i < pointerL; i++) {
            minHeap[i] = minHeap[i];
        }
        for (int i = pointerL; i < pointerL + offset; i++) {
            temp[i] = null;
        }
        for (int i = pointerL + offset; i < temp.length; i++) {
            temp[i] = minHeap[i - offset];
        }
        minHeap = temp;
        pointerF += offset;
    }

    private void resizeDown() {
        PriorityNode<T>[] temp = (PriorityNode<T>[]) new Object[minHeap.length / 3];
        int counter = 0;
        if (pointerF > pointerL) {
            for (int i = pointerF; i < minHeap.length; i++) {
                temp[counter] = minHeap[i];
                counter++;
            }
            for (int i = 0; i < pointerL; i++) {
                temp[counter] = minHeap[i];
                counter++;
            }
            pointerF = 0;
            pointerL = counter;
        } else {
            for (int i = pointerF; i < pointerL; i++) {
                temp[counter] = minHeap[i];
                counter++;
            }
            pointerF = 0;
            pointerL = counter;
        }

        minHeap = temp;
    }

}
