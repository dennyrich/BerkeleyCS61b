package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */

    @Override
    public Iterator<T> iterator() {
        return new BoundedQueueIterator<T>() ;
    }

    private class BoundedQueueIterator<T> implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return fillCount < capacity;
        }

        @Override
        public T next() {
            return (T) dequeue();
        }
    }

    @Override
    public void enqueue(T x) {
        if (!iterator().hasNext()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount++;

    }
    @Override
    public boolean equals(Object other) {
        try {
            ArrayRingBuffer<T> copy = (ArrayRingBuffer<T>) other;
            if (copy.fillCount() != this.fillCount) {
                return false;
            }
            for (int i = first; i < last; i++) {
                if (rb[i] != copy.dequeue()) {
                    return false;
                }
            }
        } catch(ClassCastException e){}
        return true;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */

    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        fillCount--;
        T firstItem = rb[first];
        first = (first + 1) % capacity;
        return firstItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */

    @Override
    public T peek() {
        return rb[first];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
