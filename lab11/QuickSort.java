import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     *
     * @param q1  A Queue of items
     * @param q2  A Queue of items
     * @return    A Queue containing the items of 
     *            q1 followed by the items of q2.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     *
     * @param items  A Queue of items
     * @return       A random item from items
     */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     *
     * @param items  A Queue of possibly unsorted items
     * @return       A Queue of sorted items
     */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        if (items.size() <= 1) {
            //System.out.println(items.size());
            return items;
        }
        Queue<Queue<Item>> queueOfQueues = makeSingleItemQueues(items);
        while(queueOfQueues.size() > 1) {
            queueOfQueues.enqueue(mergeSortedQueues(queueOfQueues.dequeue(), queueOfQueues.dequeue()));
        }
        items = queueOfQueues.dequeue();
        return items;
    }

    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     *
     * This method should take linear time and will result in "items" being empty
     *
     * @param   items  A Queue of items.
     * @return         A Queue of queues, each containing an item from items.
     *
     */
    private static <Item extends Comparable> Queue<Queue<Item>>
    makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> queueOfQueues = new Queue<>();
        while (!items.isEmpty()) {
            Queue<Item> singleItem = new Queue<>();
            singleItem.enqueue(items.dequeue());
            queueOfQueues.enqueue(singleItem);
        }
        return queueOfQueues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> merged = new Queue<>();
        while (!(q1.isEmpty() && q2.isEmpty())) {
            merged.enqueue(getMin(q1, q2));
        }
        return merged;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     *
     * This method should take roughly nlogn time where n is the size of "items"
     * running this method will result in "items" being emptied into the returned queue
     *
     * @param   items  A Queue to be sorted.
     * @return         A Queue containing every item in "items".
     *
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() <= 1) {
            //System.out.println(items.size());
            return items;
        }
        Queue<Queue<Item>> queueOfQueues = makeSingleItemQueues(items);
        while(queueOfQueues.size() > 1) {
            queueOfQueues.enqueue(mergeSortedQueues(queueOfQueues.dequeue(), queueOfQueues.dequeue()));
        }
        items = queueOfQueues.dequeue();
        return items;
    }
}
