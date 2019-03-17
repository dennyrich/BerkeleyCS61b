package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest<T> {
    private ArrayHeapMinPQ<Integer> globalHeap = new ArrayHeapMinPQ<>();
    private int size = 0;
    private int minItem;
    private int[] orderedItems;
    @Test
    public void testing() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(1000, 0);
        test.add(1001, 1);
        test.add(1002, -1);
        assertEquals(1002, (int) test.getSmallest());
        test.removeSmallest();
        assertEquals(1000, (int) test.getSmallest());
        test.printFancy();
    }

    @Test
    public void testGetandGetPriority() {

    }

    @Test
    public void testMakeAndRemove() {
        makeHeap(new int[]{1, 3, 5, 8, 10}, new double[]{3.4, 6, 72, 2.2, 55});
        globalHeap.changePriority(3, 5);
        orderedItems = new int[] {8, 1, 3, 10, 5};
        removeItems();
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> testHeap = returnHeap(); //items: [20, 30], priorities: item/100
        testHeap.changePriority(21, 60);
        testHeap.changePriority(28, 0);
        testHeap.changePriority(25, 5);
        testHeap.changePriority(20, 87);
        testHeap.changePriority(20, 1);
        testHeap.changePriority(30, -1.8);
        testHeap.changePriority(28, 0);
        for (int i = 0; i < 40; i++) {
            if (i >= 20 && i <= 30) {
                assertTrue(i + " not found in heap", testHeap.contains(i));
            } else {
                assertFalse(i + " found in heap", testHeap.contains(i));
            }
        }
        assertTrue(testHeap.contains(28));
        assertEquals(30, (int) testHeap.getSmallest());

        ArrayHeapMinPQ<Integer> small = new ArrayHeapMinPQ<>();
        small.add(5, 0);
        small.add(6, 1);
        small.changePriority(6, -1);
        small.changePriority(5, 22);
        small.add(88, 3.2);
        small.removeSmallest();
        small.removeSmallest();
        //small.changePriority(6, 3);

        ArrayHeapMinPQ<Integer> large = returnLargeHeap();
        for (int i = 0; i < 2000; i++) {
            large.changePriority(i, 0);
        }


    }

    @Test
    public void testSwim() {

    }

    private ArrayHeapMinPQ<Integer> returnLargeHeap() {
        ArrayHeapMinPQ<Integer> large = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 2000; i++) {
            large.add(i, Math.pow(-1, i) * i);
        }
        return large;
    }

    private void changeAllPriority (ArrayHeapMinPQ<Integer> heap) {
        for (int i = 0; i < heap.size(); i ++) {
            int smallest = heap.removeSmallest();
        }
    }


    /**
     *
     * @param items - a sequence alternating between item and priority
     * @return heap with all items added with priorities given
     */
    private void makeHeap(int[] items, double[] priorities) {
        //int size = 0;
        double minP = priorities[0];
        minItem = items[0];
//        ArrayHeapMinPQ<Integer> testHeap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < items.length; i ++) {
            globalHeap.add(items[i], priorities[i]);
            size++;
            minP = Math.min(minP, priorities[i]);
            if (priorities[i] == minP) {
                minItem = items[i];
            }
            verifyHeap();
        }
    }

    /**
     *
     * @return items: integers [20, 30], priorities: item / (double) 100
     */
    private ArrayHeapMinPQ<Integer> returnHeap() {
        ArrayHeapMinPQ<Integer> testHeap = new ArrayHeapMinPQ<>();
        for (int i = 20; i <= 30; i ++) {
            testHeap.add(i, 10 / (double) i);
        }
        return testHeap;
    }

    private void removeItems() {
        int orderIndex = 1;
        for (int i = 0; i < size; i++) {
            System.out.println(i);
            globalHeap.removeSmallest();
            minItem = orderedItems[orderIndex];
            orderIndex++;
            size--;
            verifyHeap();
        }
    }

    private void verifyHeap() {
        assertEquals(size, globalHeap.size());
        assertEquals(minItem, (int) globalHeap.getSmallest());
    }
}
