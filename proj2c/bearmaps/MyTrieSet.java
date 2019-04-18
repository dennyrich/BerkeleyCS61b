package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet  {
    private class Node {
        private Object key;
        private boolean isKey;
        private HashMap<Object, Node> map;

        Node(Object key, boolean isKey) {
            this.key = key;
            this.isKey = isKey;
            map = new HashMap<>();
        }

        boolean isKey() {
            return isKey;
        }

        List<String> getKeys() {
            List<String> keys = new ArrayList<>();
            for (Node n : map.values()) {
                String key = "";
                if (n.isKey()) {
                    keys.add(key);
                } else {
                    for (String s : n.getKeys()) {
                        keys.add(s);
                    }
                }
            }
            return keys;
        }
    }

    private Node root;

    public MyTrieSet() {
        root = new Node("", false);
    }


    public void clear() {
        root = new Node("", false);
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey();
    }

    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return result;
            }
            curr = curr.map.get(c);
        }
        collect(prefix, result, curr);
        return result;
    }

    private void collect(String prefix, List<String> lst, Node n) {
        if (n.isKey()) {
            lst.add(prefix);
        }
        for (Object c : n.map.keySet()) {
            collect(prefix + c, lst, n.map.get(c));
        }
        return;
    }

    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }


    public void add(String key) {
        if (key == null || key.length() < 1 || contains(key)) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }
}