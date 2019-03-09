import org.junit.Test;

import java.util.*;
public class BSTMap<K, V> implements Map61B<K, V> {

    private class Node<K, V> {
        K key;
        V value;
        Node right;
        Node left;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            right = null;
            left = null;
        }
        private Node() {
            this(null, null);
        }
    }

    private int size;
    private Node top;
    public BSTMap() {
        size = 0;
        top = null;
    }

    @Override
    public void clear() {
        top = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsHelper(top, key);
    }
    private boolean containsHelper(Node n, K key) {
        if (n == null) {
            return false;
        }
        else if (n.key.equals(key)) {
            return true;
        }
        else {
            return containsHelper(n.left, key) || containsHelper(n.right, key);
        }
    }

    @Override
    public V get(K key) {
        return getHelper(top, (Comparable<K>) key);
    }
    private V getHelper(Node n, Comparable<K> cc) {
        if (n == null) {
            return null;
        }
        int comparison = cc.compareTo((K) n.key);
        if (comparison == 0) {
            return (V) n.value;
        } else if (comparison > 0) {
            return (V) getHelper(n.right, cc);
        } else {
            return (V) getHelper(n.left, cc);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        top = putHelper((Comparable<K>) key, value, top);

    }

    private Node<K, V> putHelper(Comparable<K> key, V value, Node n) {
        if (n == null) return new Node(key, value);
        int cmp = key.compareTo((K) n.key);
        if      (cmp < 0) n.left  = putHelper(key, value, n.left);
        else if (cmp > 0) n.right = putHelper(key, value, n.right);
        else              n.value   = value;
        //n.size = 1 + size(n.left) + size(x.right);
        return n;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("No remove operation");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("No keySet operation");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("No remove operation");
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("No iterator operation");
    }
//    public static void main(String[] args) {
//
//    }
}
