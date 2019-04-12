import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import org.junit.Assert;

import org.junit.Test;

import java.util.Iterator;

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
        System.out.println(test.size());
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
        Iterator<Item> iter = items.iterator();
        Item prev;
        Item curr = iter.next();
        int i = 0;
        while (i < items.size()  - 1) {
            prev = curr;
            curr = iter.next();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
            i++;
        }
        return true;
    }
}
