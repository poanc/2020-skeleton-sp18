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

    @Test
    public void testRandom() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.addLast(7);
        A.addLast(8);
        A.addFirst(3);
        A.addFirst(2);
        A.addFirst(1);
        A.addFirst(0);
        int act = A.removeLast();
        int exp = 8;

        assertEquals(exp, act);
//        assertEquals(exp2, act2);
//        assertEquals(exp3, act3);
    }


    @Test
    public void testResize() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(1);
        A.addLast(2);
        A.addLast(3);
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.addLast(7);
        A.addLast(8);
        A.addLast(9);
        int act = A.size();
        int exp = 9;

        assertEquals(exp, act);
    }

    @Test
    public void testResize1() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(1);
        A.addLast(2);
        A.addLast(3);
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.addLast(7);
        A.addLast(8);
        A.addLast(9);
        int act1 = A.get(8);
        int exp1 = 9;
        int act2 = A.get(5);
        int exp2 = 6;

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);

    }

    @Test
    public void testResize2() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(1);
        A.addLast(2);
        A.addLast(3);
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.addLast(7);
        A.addLast(8);
        A.addLast(9);
        int act1 = A.get(8);
        int exp1 = 9;
        int act2 = A.get(5);
        int exp2 = 6;

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);

    }

    @Test
    public void testResize3() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(1);
        A.addLast(2);
        A.addLast(3);
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.addLast(7);
        A.addLast(8);
        A.addLast(9);
//        for(int i = 0; i < 6; i++) {
//            A.removeLast();
//        }
        A.removeLast();
        A.removeLast();

        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();

        int act1 = A.size();
        int exp1 = 2;


        assertEquals(exp1, act1);

    }

    @Test
    public void testResize4() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(1);
        A.addLast(2);
        A.addLast(3);
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.addLast(7);
        A.addLast(8);
        A.addLast(9);
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        int act1 = A.get(1);
        int exp1 = 2;
        assertEquals(exp1, act1);

    }

    @Test
    public void testRemove5() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
        boolean test1 = ArrayDeque.isEmpty();
        ArrayDeque.addLast(1);
        ArrayDeque.addLast(2);
        ArrayDeque.addLast(3);
        ArrayDeque.addLast(4);
        ArrayDeque.addLast(5);
        ArrayDeque.addLast(6);
        ArrayDeque.addLast(7);
        ArrayDeque.addLast(8);
        int act = ArrayDeque.removeLast();
        int exp = 8;

        assertEquals(exp, act);
        assertTrue(test1);

    }

    @Test
    public void testRandom2() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
        ArrayDeque.addFirst(0);
        ArrayDeque.addFirst(1);
        int act1 = ArrayDeque.removeFirst();
        int act2 = ArrayDeque.removeFirst();
        int exp1 = 1;
        int exp2 = 0;

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);

    }

}
