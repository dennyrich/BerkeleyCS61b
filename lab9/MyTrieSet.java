import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    Node holder;
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
        return null;
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

}
