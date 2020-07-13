package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class testPercolationStats {

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
    public void testPercolationStats() {
        int N = 5;
        PercolationFactory pf = new PercolationFactory();
        PercolationStats p = new PercolationStats(N, 5, pf);
        System.out.println(p.mean());
    }

}
