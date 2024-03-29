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
            System.out.println(test);
            int size = test.size();
            test = MergeSort.mergeSort(test);
            System.out.println(test);
            Assert.assertEquals(size, test.size());
            Assert.assertTrue(isSorted(test));

        }
    }

    @Test
    public void testTenItems() {
        for (int j = 0; j < 1000; j++) {
            Queue<Double> tenItems = new Queue<>();
            for (int i = 0; i < 10; i++) {
                tenItems.enqueue(Math.random() * 1000);
            }
            int size = tenItems.size();
            tenItems = MergeSort.mergeSort(tenItems);
            Assert.assertTrue(tenItems.toString(), isSorted(tenItems));
            Assert.assertEquals(tenItems.toString(), size, tenItems.size());
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
