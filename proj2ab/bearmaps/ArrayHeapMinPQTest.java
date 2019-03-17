package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
        makeHeap(new int[]{1, 3, 5, 8, 10}, new double[]{3.4, 6, 72, 2.2, 55});
        globalHeap.changePriority(3, 5);
        orderedItems = new int[] {8, 1, 3, 10, 5};
        removeItems();
        //randomAddRemove();
    }

//    /**
//     * checks for null pointer exceptions
//     */
//    @Test
//    public void randomAddRemove() {
//        ArrayHeapMinPQ<Integer> randomHeap = new ArrayHeapMinPQ<>();
//        for (int i = 10; i <= 25; i ++) {
//            randomHeap.add(i, 20 / (double) i);
//        }
//        double random;
//        int numExecutions = 0;
//        int addItem = 26;
//        double addPriority = 76.3;
//        String input = "";
//        while (randomHeap.size() > 0) {
//            numExecutions++;
//            random = Math.random();
//            if (random > 0.66) {
//                randomHeap.removeSmallest();
//                input += "removeSmallest \n";
//            } else if (random > 0.33){
//                randomHeap.add(addItem, addPriority);
//                addItem += 2;
//                addPriority /= 0.3;
//                input += "add(" + addItem + ", " + addPriority + ")\n";
//            } else {
//                randomHeap.changePriority(addItem, random * 1000);
//                input += "changePriority(" + addItem + ", " + random * 1000 + ")\n";
//            }
//        }
//        System.out.println(numExecutions);
//    }

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
