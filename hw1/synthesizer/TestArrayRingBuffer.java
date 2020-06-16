package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {

    @Test
    public void testIsEmpty() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
    }


    @Test
    public void testEnqueue() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(5);
        arb.enqueue(6);
        int act = (int) arb.dequeue();
        int exp = 5;
        assertEquals(exp, act);

    }

    @Test
    public void testRandom1() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        arb.enqueue(11);
        arb.enqueue(12);
        arb.enqueue(13);
        arb.enqueue(14);
        assertTrue(arb.isFull());
    }

    @Test
    public void testRandom3() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        int count = 0;
        while(count < 10) {
            arb.enqueue((int) Math.floor(Math.random() * 10));
            count++;
        }
        count = 0;
        while(count < 10) {
            arb.dequeue();
            count++;
        }
        assertTrue(arb.isEmpty());
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(5);
        arb.enqueue(6);
        int act = (int) arb.peek();
        int exp = 5;
        assertEquals(exp, act);
    }

    @Test
    public void testIteration() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);

        ArrayRingBuffer.ArrayRingIterator aa = arb.new ArrayRingIterator();
        while(aa.hasNext()) {
            System.out.println(aa.next());
        }
    }

    @Test
    public void testIteration2() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);

        for (Object n : arb) {
            System.out.println(n);
        }
    }



    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
