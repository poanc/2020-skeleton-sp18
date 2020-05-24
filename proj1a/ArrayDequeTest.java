import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void testRemove1() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(5);
        A.removeFirst();
        A.addFirst(1);
        A.removeLast();
        int act = A.size();
        int exp = 0;
        assertEquals(exp, act);

    }

    @Test
    public void testRemove2() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(5);
        A.removeFirst();
        A.addFirst(1);
        A.removeLast();
        A.removeFirst();
        int act = A.size();
        int exp = 0;
        assertEquals(exp, act);

    }

    @Test
    public void testRemove3() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(5);
        A.removeFirst();
        A.addFirst(1);
        A.removeLast();
        A.removeFirst();
        A.printDeque();
        A.addLast(10);
        A.addLast(5);
        int first = A.removeFirst();
        int exp = 10;
        assertEquals(exp, first);

    }

    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(5);
        boolean act1 = A.isEmpty();

        A.removeFirst();
        A.addFirst(1);
        A.removeLast();
        boolean act2 = A.isEmpty();

        assertFalse(act1);
        assertTrue(act2);

    }


}
