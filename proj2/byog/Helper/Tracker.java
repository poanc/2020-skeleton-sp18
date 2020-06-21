package byog.Helper;

import byog.Core.JoeWorld;

import java.util.HashSet;
import java.util.Set;

public class Tracker {
    public Point curr;
    public Set<String> prev;

    public Tracker(int x, int y) {
        curr = new Point(x, y);
        prev = new HashSet<>();
    }


    public boolean contains(Point p) {
        return prev.contains(p.toString());
    }

    public void move(int width, int height, int signX, int signY) {
        if (width == 0 || height == 0) {
            return;
        }
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                curr.x += (signX * x);
                curr.y += (signY * y);
                prev.add(curr.toString());
            }
        }

    }

    public void moveTo(Point p) {
        prev.add(curr.toString());
        curr.x = p.x;
        curr.y = p.y;
    }


    public void printCurr() {
        System.out.println(curr.toString());
    }

    public void printPrev() {

        for (String s: prev) {

            System.out.println(s);
        }
    }

}
