import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import org.junit.Assert;

import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {

    }

    @Test
    public void testMergeSort() {
        Queue<Integer> test = new Queue<>();
        for (int i = 10; i > 0; i--) {
            test.enqueue(i);
        }

        Queue<Integer> small = new Queue<>();
        small.enqueue(0);
        Queue<Integer> empty = new Queue<>();
        test = MergeSort.mergeSort(test);
        Assert.assertTrue(isSorted(test));

        System.out.println(test.size());

        Assert.assertTrue(isSorted(MergeSort.mergeSort(small)));
        Assert.assertTrue(isSorted(MergeSort.mergeSort(empty)));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
