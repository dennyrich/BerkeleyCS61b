import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import org.junit.Assert;

import org.junit.Test;

import java.util.Iterator;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Assert.assertEquals(0, 0);
    }

    @Test
    public void testMergeSort() {
        for (int j = 0; j < 100; j++) {
            Queue<Integer> test = new Queue<>();
            for (int i = j; i > 0; i--) {
                test.enqueue(i);
            }
            int size = test.size();
            test = MergeSort.mergeSort(test);
            Assert.assertTrue(isSorted(test));
            Assert.assertEquals(size, test.size());
        }
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
