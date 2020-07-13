package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {

    @Test
    public void testIsOpen() {
        Percolation p = new Percolation(5);
        boolean act = p.isOpen(2, 2);
        assertFalse(act);
    }

    @Test
    public void testOpen() {
        Percolation p = new Percolation(5);
        assertFalse(p.isOpen(3, 3));
        p.open(3, 3);
        boolean act = p.isOpen(3, 3);
        assertTrue(act);
    }

    @Test
    public void testIsFull() {
        Percolation p = new Percolation(2);
        p.open(0, 0);
        p.open(0, 1);
        assertTrue(p.isFull(0, 0));
        p.open(1, 0);
        assertTrue(p.percolates());
    }

    @Test
    public void testNumberOfOpenSites() {
        Percolation p = new Percolation(5);
        p.open(0, 0);
        int act = p.numberOfOpenSites();
        int exp = 1;
        p.open(1, 2);
        int act2 = p.numberOfOpenSites();
        int exp2 = 2;
        assertEquals(exp, act);
        assertEquals(exp2, act2);
    }
}
