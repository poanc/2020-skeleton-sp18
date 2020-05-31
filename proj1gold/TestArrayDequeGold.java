import static org.junit.Assert.*;
import org.junit.Test;



public class TestArrayDequeGold {

    @Test
    public void testStudentArrayDeque() {

        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        String msg = "\n";

        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                stu.addLast(i);
                sol.addLast(i);
                msg += "addLast(" + i + ")\n";
            } else if (numberBetweenZeroAndOne >= 0.25 && numberBetweenZeroAndOne < 0.5) {
                stu.addFirst(i);
                sol.addFirst(i);
                msg += "addFirst(" + i + ")\n";
            } else if (numberBetweenZeroAndOne >= 0.5 && numberBetweenZeroAndOne < 0.75) {
                msg += "removeFirst()\n";
                Integer exp = stu.removeFirst();
                Integer act = sol.removeFirst();
                assertEquals(msg, exp, act);
            } else {
                msg += "removeLast()\n";
                Integer exp = stu.removeLast();
                Integer act = sol.removeLast();
                assertEquals(msg, exp, act);
            }
        }

    }

}
