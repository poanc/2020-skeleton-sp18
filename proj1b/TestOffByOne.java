import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.

    @Test
    public void testOffByOne1() {
        CharacterComparator offByOne = new OffByOne();

        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('z', 'a'));

    }

    @Test
    public void testOffByOne2() {
        CharacterComparator offByOne = new OffByOne();

        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r', 'q'));

    }

    @Test
    public void testOffByOne3() {
        CharacterComparator offByOne = new OffByOne();

        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('a', 'z'));

    }
}
