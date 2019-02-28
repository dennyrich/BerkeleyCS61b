package es.datastructur.synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(2);
        arb.enqueue(6);
        arb.enqueue(7);
        //arb.enqueue(6);
        assertEquals((Integer) 6, arb.dequeue());
        assertEquals((Integer) 7, arb.dequeue());

    }
}
