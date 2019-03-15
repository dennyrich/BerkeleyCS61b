import java.util.*;


public class MyHashMap<K, V> implements Map61B<K, V> {
    private Set keys;
    private int size;
    private double loadFactor;
    private List<List<HashMapNode<K, V>>> buckets;

    private class HashMapNode<K, V> {
        private K key;
        private V value;
        private HashMapNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
//    private class HashIterable<K> implements Iterable<K> {
//        @Override
//        public Iterator<K> iterator() {
//            return
//        }
//    }

    public MyHashMap() {
        this(16, 0.75);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("size can't be negative");
        }
        size = 0;
        this.loadFactor = loadFactor;
        buckets = new ArrayList<>();
        keys = new HashSet<>();
        for (int i = 0; i < initialSize; i++) {
            List b = new LinkedList<HashMapNode<K, V>>();
            buckets.add(b);
        }
    }

    public void clear() {
        for (List l : buckets) {
            l.clear(); //built in method for LinkedList
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */

    @Override
    public boolean containsKey(K key) {
        for (List l : buckets) {
            if (l.contains(key.hashCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int bucketNum = Math.floorMod(key.hashCode(), buckets.size());
        List targetList = buckets.get(bucketNum);
        for (Object h : targetList) {
            HashMapNode<K, V> node = (HashMapNode<K, V>) h;
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        size++;
        placeInBucket(new HashMapNode<>(key, value), buckets.size());
        if (size > loadFactor * buckets.size()) {
            resize();
        }
        keys.add(key);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    private void resize () {
        int newSize = buckets.size() + buckets.size() / 2;
        List<List<HashMapNode<K, V>>> oldBuckets = buckets;
        buckets.clear();
        for (int i = 0; i < newSize; i++) {
            buckets.add(new LinkedList<>());
        }
        for (List l : oldBuckets) {
            for (Object node : l) {
                placeInBucket((HashMapNode<K, V>) node, newSize);
            }
        }

    }
    private void placeInBucket(HashMapNode<K, V> node, int bucketsSize) {
        int correctBucket = Math.floorMod(node.key.hashCode(), buckets.size());
        buckets.get(correctBucket).add(node);
    }
}
