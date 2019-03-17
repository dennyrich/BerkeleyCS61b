package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest<T> {
    @Test
    public void testing() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(1000, 0);
        test.add(1001, 1);
        test.add(1002, -1);
        assertEquals(1002, (int) test.getSmallest());
    }
}
