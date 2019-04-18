package  bearmaps;

import java.util.*;

public class MyTrieSet {
    private static class Node {
        char letter;
        boolean isWord;
        //Node parent;
        Map<Character, Node>  children;

        private Node(char c) {
            this.letter = c;
            //this.parent = null;
            this.children = new HashMap<>();
        }
    }

    /**
     * only has add and prefix operations
     */
    Node root;
    Set<String> words;
    public MyTrieSet() {
        this.root = new Node(' ');
        words = new HashSet<>();
    }

    public void add(String word) {
        if (words.contains(word) || word == null || word.length() == 0) {
            return;
        }
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                //curr.children.get(curr).parent = curr;
                curr.children.put(c, new Node(c));
            }

            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }


    /** Returns a list of all words that start with PREFIX */

    public List<String> keysWithPrefix(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            current = current.children.get(prefix.charAt(i));
        }

        List<String> listSoFar = new ArrayList<>();
        listSoFar.add(prefix);
        completeTheList(listSoFar, current, prefix);
        return listSoFar;
    }

    private void completeTheList(List<String> listSoFar, Node n, String word) {
        //word += n.letter;
        if (n.children.isEmpty()) {
            listSoFar.add(word);
            return;
        }
        if (n.isWord) {
            listSoFar.add(word);
        }
        for (Node child : n.children.values()) {
            completeTheList(listSoFar, child, word + child.letter);
        }
    }

    private Node getToNode(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.children.containsKey(c)) {
                return null;
            }
            curr = curr.children.get(c);
        }
        return curr;
    }
}
