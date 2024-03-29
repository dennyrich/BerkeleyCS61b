import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("persiflage"));
        assertTrue(palindrome.isPalindrome("kayak"));
        assertTrue(palindrome.isPalindrome("redivvider"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("NOON"));
        assertFalse(palindrome.isPalindrome("PERSIFLAGE"));
        assertFalse(palindrome.isPalindrome("Kayak"));
        assertFalse(palindrome.isPalindrome("PErSIFLaGE"));


        CharacterComparator one = new OffByOne();
        assertFalse(palindrome.isPalindrome("kayak", one));
        assertTrue(palindrome.isPalindrome("flake", one));
        assertTrue(palindrome.isPalindrome("flabke", one));
        assertTrue(palindrome.isPalindrome("FLAKE", one));
        assertFalse(palindrome.isPalindrome("RANDOM", one));
        assertFalse(palindrome.isPalindrome("RanDoM", one));
        assertFalse(palindrome.isPalindrome("FLakE", one));

        /*
        CharacterComparator N = new OffByN(5);
        assertTrue(palindrome.isPalindrome("abhgf", N));
        */



    }
}
