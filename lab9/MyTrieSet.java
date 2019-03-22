import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private Node holder;
    /** Clears all items out of Trie */
    public MyTrieSet() {
        holder = new Node('z');
    }
    @Override
    public void clear() {
        holder.map.clear();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        Node current = holder;
        for (int index = 0; index < key.length(); index ++) {
            current = current.map.get(key.charAt(index));
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = holder;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node current = holder;
        for (int i = 0; i < prefix.length(); i++) {
            current = current.map.get(prefix.charAt(i));
        }

        List<String> listSoFar = new ArrayList<>();
        listSoFar.add(prefix);
        completeTheList(listSoFar, current, prefix);
        return listSoFar;
    }

    private void completeTheList(List<String> listSoFar, Node n, String word) {
        word += n.letter;
        if (n.map.isEmpty()) {
            listSoFar.add(word);
            return;
        }
        if (n.isKey) {
            listSoFar.add(word);
        }
        for (Node child : n.map.values()) {
            completeTheList(listSoFar, child, word);
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("no longestPrefixOf function");
    }

    private static class Node {
        boolean isKey;
        char letter;
        HashMap<Character, Node> map;

        private Node(char letter) {
            this(letter,false);
        }

        private Node(char letter, boolean end) {
            this.letter = letter;
            map = new HashMap<>();
            isKey = end;
        }
    }
    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
