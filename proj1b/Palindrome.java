public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        char[] wordChars = word.toCharArray();
        for (char c : wordChars) {
            wordDeque.addLast(c);
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }
    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        } else if (wordDeque.removeFirst() != wordDeque.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(wordDeque);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        int offBy = 1;
        return isPalindromeHelper(wordDeque, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() <= 1) {
            return true;
        } else if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
            return false;
        } else {
            return isPalindromeHelper(wordDeque, cc);
        }
    }

}
