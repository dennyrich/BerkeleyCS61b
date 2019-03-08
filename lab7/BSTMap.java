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
        if (n.left == null && n.right == null) {
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
        int comparison = cc.compareTo((K) n.key);
        if (comparison == 0) {
            return (V) n.value;
        } else if (comparison > 0) {
            return (V) getHelper(n.right, cc);
        } else if (comparison < 0) {
            return (V) getHelper(n.left, cc);
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Comparable<K> comp = (Comparable<K>) key;
        Node n = top;
        while (n != null) {
            int comparison = comp.compareTo((K) n.key);
            if (comparison > 0) {
                n = n.right;
            } else if (comparison < 0) {
               n = n.right;
            }
        }

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

}
