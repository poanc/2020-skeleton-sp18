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
    public void testIsPalindrome1() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
    }

    @Test
    public void testIsPalindrome2() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("jump"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("taT"));

    }

    @Test
    public void testIsPalindrome3() {
        assertFalse(palindrome.isPalindrome("aaA"));

    }

    @Test
    public void testCharacterComparator() {

        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("flask", obo));


    }

    @Test
    public void testCharacterComparator1() {

        OffByN offBy5 = new OffByN(5);

        assertFalse(palindrome.isPalindrome("ab", offBy5));
        assertFalse(palindrome.isPalindrome("ab", offBy5));



    }
}
