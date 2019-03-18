package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest<T> {

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
    public void testForNullPointerExceptions() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        List<Integer> added = new ArrayList<>();
        for (int i = 0; i < 5; i = i++) {
            if (i % 3 == 0) {
                test.add(i, i);
                test.add(i + 23, i);
                added.add(i);
                added.add(i + 23);
            } else if (i % 3 == 1) {
                if (added.size() > 0) {
                    added.remove(test.removeSmallest());
                    added.remove(test.removeSmallest());                }
            } else {
                if (added.contains(i)) {
                    test.changePriority(i / 3, 100 / i);
                }
            }
        }
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
        small.add(5, 0); //22
        small.add(6, 1); //-1
        small.changePriority(6, -1);
        small.add(23, 78);
        small.changePriority(5, 22);
        small.add(88, 3.2);
        small.removeSmallest();
        small.removeSmallest();
        small.changePriority(23, 3);
        assertFalse(small.contains(6));

        ArrayHeapMinPQ<Integer> large = returnLargeHeap();
        for (int i = 0; i < 2000; i++) {
            large.changePriority(i, 0);
        }

        //***********************************//
        ArrayHeapMinPQ<Integer> anotherOne = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20; i += 3) {
            if (i % 2 == 0) {
                anotherOne.add(i, i);
                anotherOne.add(i + 1, i + 1);
            }
        }
    }

    private ArrayHeapMinPQ<Integer> returnLargeHeap() {
        ArrayHeapMinPQ<Integer> large = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 2000; i++) {
            large.add(i, Math.pow(-1, i) * i);
        }
        return large;
    }

    private void changeAllPriority (ArrayHeapMinPQ<Integer> heap) {
        for (int i = 0; i < heap.size(); i++) {
            int smallest = heap.removeSmallest();
        }
    }



    private ArrayHeapMinPQ<Integer> returnHeap() {
        ArrayHeapMinPQ<Integer> testHeap = new ArrayHeapMinPQ<>();
        for (int i = 20; i <= 30; i++) {
            testHeap.add(i, 10 / (double) i);
        }
        return testHeap;
    }
    @Test
    public void testStrings() {
        ArrayHeapMinPQ<String> bc = new ArrayHeapMinPQ<>();
        bc.add("a", -1);
        bc.add("b", 0);
        bc.add("c", 1);
        bc.removeSmallest();
        bc.changePriority("c", 5);
    }
}
