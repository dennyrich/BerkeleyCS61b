package es.datastructur.synthesizer;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;
    private int capacity;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        capacity = (int) Math.round((SR / frequency));
        buffer = new ArrayRingBuffer<Double>(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        if (!buffer.isEmpty()) {
            double newAddition = ((buffer.dequeue() + buffer.peek()) / 2.0) * DECAY;
            buffer.enqueue(newAddition);
        }
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        if (buffer.isEmpty()) {
            return 0;
        }
        return buffer.peek();
    }
}

