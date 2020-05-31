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

        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("&%", obo));

    }

    @Test
    public void testCharacterComparator1() {
        OffByN offBy5 = new OffByN(5);
        assertFalse(palindrome.isPalindrome("ab", offBy5));
        assertFalse(palindrome.isPalindrome("a", offBy5));

    }

    @Test
    public void testCharacterComparatorOffOneTrue() {
        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("ab", obo));

    }

    @Test
    public void testCharacterComparatorOffOneCornerCase() {
        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("Z", obo));
        assertTrue(palindrome.isPalindrome("C", obo));
        assertTrue(palindrome.isPalindrome("bc", obo));
        assertTrue(palindrome.isPalindrome("ab", obo));
        assertTrue(palindrome.isPalindrome("yxz", obo));

    }

    @Test
    public void testCharacterComparatorOffOneCornerCase1() {
        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome(" ", obo));
        assertTrue(palindrome.isPalindrome("  ", obo));
        assertTrue(palindrome.isPalindrome("   ", obo));

    }

    @Test
    public void testCharacterComparatorOffOneCornerCase2() {
        OffByOne obo = new OffByOne();

        assertFalse(palindrome.isPalindrome(" a", obo));
        assertFalse(palindrome.isPalindrome("b  ", obo));
        assertTrue(palindrome.isPalindrome("b a", obo));

    }

    @Test
    public void testCharacterComparatorOffOneFalse() {
        OffByOne obo = new OffByOne();

        assertFalse(palindrome.isPalindrome("za", obo));

    }

}
