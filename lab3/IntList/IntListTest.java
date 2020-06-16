import static org.junit.Assert.*;

import org.junit.Test;

public class IntListTest {

    /**
     * Example test that verifies correctness of the IntList.of static
     * method. The main point of this is to convince you that
     * assertEquals knows how to handle IntLists just fine.
     */

    @Test
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.of(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    @Test
    public void testdSquareList() {
        IntList L = IntList.of(1, 2, 3);
        IntList.dSquareList(L);
        assertEquals(IntList.of(1, 4, 9), L);
    }

    /**
     * Do not use the new keyword in your tests. You can create
     * lists using the handy IntList.of method.
     * <p>
     * Make sure to include test cases involving lists of various sizes
     * on both sides of the operation. That includes the empty of, which
     * can be instantiated, for example, with
     * IntList empty = IntList.of().
     * <p>
     * Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     * Anything can happen to A.
     */

    @Test
    public void testSquareListRecursive() {
        IntList L = IntList.of(1, 2, 3);
        IntList res = IntList.squareListRecursive(L);
        assertEquals(IntList.of(1, 2, 3), L);
        assertEquals(IntList.of(1, 4, 9), res);
    }

    @Test
    public void testDcatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.dcatenate(A, B));
        assertEquals(IntList.of(1, 2, 3, 4, 5, 6), A);
    }

    @Test
    public void testCatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.catenate(A, B));
        assertEquals(IntList.of(1, 2, 3), A);
    }

    @Test
    public void testReverse() {
        IntList A = IntList.of(1, 2, 3, 4);
        IntList exp = IntList.of(4, 3, 2, 1);
        IntList act = IntList.reverse(A);
        assertEquals(exp, act);
        assertNotEquals(exp, A);
    }

    @Test
    public void testReverseNull() {
        IntList A = null;
        IntList exp = null;
        IntList act = IntList.reverse(A);
        assertSame(exp, act);
    }

    @Test
    public void testSquareIterative() {
        IntList A = IntList.of(1, 2, 3);
        IntList toCheck = IntList.squareIterative(A);
        assertNotEquals(A, toCheck);
    }

    @Test
    public void testSquareIterative2() {
        IntList A = IntList.of(8, 5, 10);
        IntList toCheck = IntList.squareIterative(A);
        IntList exp = IntList.of(64, 25, 100);
        assertEquals(exp, toCheck);
    }

    @Test
    public void testSquareMutative() {
        IntList A = IntList.of(1, 2, 3);
        IntList toCheck = IntList.squareMutativeIterative(A);
        assertEquals(A, toCheck);
    }

    @Test
    public void testSquareMutative2() {
        IntList A = IntList.of(1, 2, 3);
        IntList toCheck = IntList.squareMutativeIterative(A);
        IntList exp = IntList.of(64, 25, 100);
        assertNotEquals(exp, toCheck);
    }

    @Test
    public void testSquareMutativeRecursive() {
        IntList A = IntList.of(1, 2, 3);
        IntList toCheck = IntList.squareMutativeRecursive(A);
        assertEquals(A, toCheck);
    }

    @Test
    public void testSquarMutativeeRecursive2() {
        IntList A = IntList.of(8, 5, 10);
        IntList toCheck = IntList.squareMutativeRecursive(A);
        IntList exp = IntList.of(64, 25, 100);
        assertEquals(exp, toCheck);
    }

    @Test
    public void testSquareRecursive() {
        IntList A = IntList.of(1, 2, 3);
        IntList toCheck = IntList.squareRecursive(A);
        assertNotEquals(A, toCheck);
    }
    @Test
    public void testSquareRecursive2() {
        IntList A = IntList.of(8, 5, 10);
        IntList toCheck = IntList.squareRecursive(A);
        IntList exp = IntList.of(64, 25, 100);
        assertEquals(exp, toCheck);
    }

    @Test
    public void testSkippify() {
        IntList A = IntList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        A.skippify();
        IntList exp = IntList.of(1, 3, 6, 10);
        assertEquals(exp, A);
    }

    @Test
    public void testSkippify2() {
        IntList B = IntList.of(9, 8, 7, 6, 5, 4, 3, 2, 1);
        B.skippify();

        IntList exp = IntList.of(9, 7, 4);
        assertEquals(exp, B);
    }

    @Test
    public void testRemoveDuplicate() {
        IntList A = IntList.of(1, 2, 2, 2, 3);
        IntList.removeDuplicates(A);
        IntList exp = IntList.of(1, 2, 3);
        assertEquals(exp, A);
    }

    @Test
    public void testRemoveDuplicate2() {
        IntList A = IntList.of();
        IntList.removeDuplicates(A);
        IntList exp = IntList.of();
        assertEquals(exp, A);
    }

    @Test
    public void testRemoveDuplicate3() {
        IntList A = IntList.of(1, 1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7);
        IntList.removeDuplicates(A);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6, 7);
        assertEquals(exp, A);
    }
}
