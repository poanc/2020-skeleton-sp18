package byog.Helper;

import byog.Core.JoeWorld;

import java.util.HashSet;
import java.util.Set;

public class Tracker {
    private Point curr;

    /** The element of prev: x,y */
    private Set<String> prev;

    /** The element of room: p1x, p1y, p2x, p2y */
    private Set<String> room;


    public Tracker(int x, int y) {
        curr = new Point(x, y);
        prev = new HashSet<>();
        room = new HashSet<>();
    }

    public int getX() {
        return curr.x;
    }

    public int getY() {
        return curr.y;
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


    /** Detect if the desired room is overlapped the exist one
     * For example, the exist room owns the corner(0, 0), (3, 3).
     * The Tracker is now at the point (-1, -1).
     * We would like to add a room to (2, 2), which owns the +3 units length of the room.
     * We can just check if the point of the diagonal corner of the desired room is in the range of the exist room.
     * The desired room corner: (-1, -1) -> (2, 2)
     * The point of the existed room: [(0, 0), (3, 3)]
     * The range of the existed room: [3-0, 3-0] -> [3, 3]
     *
     * The other example
     * The desired room corner: (0, 0) -> (-4, 4)
     * The point of the existed room: [(0, 0), (-3, -3)]
     * The range of the existed room: [-3, -3]
     * */
    public boolean isOverLap(Point p, int width, int height) {
        Point newCorner = new Point(p.x + width, p.y + height);
        String roomString = Point.toRoom(curr, newCorner);

        if (room.contains(roomString)) return true;
        for (String s : room) {
            System.out.println(s);
            String[] coordinate = s.split(",");

            int ox = Integer.parseInt(coordinate[0]);
            int oy = Integer.parseInt(coordinate[1]);
            int dx = Integer.parseInt(coordinate[2]);
            int dy = Integer.parseInt(coordinate[3]);

            System.out.println(dx);
            // First quarant
            if (newCorner.x < dx && newCorner.x > ox && newCorner.y < dy && newCorner.y > oy) return true;

            // Second quarant
            else if (newCorner.x > dx && newCorner.x < ox && newCorner.y < dy && newCorner.y > oy) return true;

            // Third quarant
            else if (newCorner.x > dx && newCorner.x < ox && newCorner.y > dy && newCorner.y < oy) return true;

            // Fourth quarant
            else if (newCorner.x < dx && newCorner.x > ox && newCorner.y > dy && newCorner.y < oy) return true;

            else if (newCorner.x > dx && newCorner.x > ox && newCorner.y > dy && newCorner.y > oy) return true;

            else if (newCorner.x < dx && newCorner.x < ox && newCorner.y > dy && newCorner.y > oy) return true;

            else if (newCorner.x < dx && newCorner.x < ox && newCorner.y < dy && newCorner.y < oy) return true;

            else if (newCorner.x > dx && newCorner.x > ox && newCorner.y < dy && newCorner.y < oy) return true;



            return false;



        }
        return false;

    }

    public void insertRoom(int width, int height) {
        Point newCorner = new Point(curr.x + width, curr.y + height);
        room.add(Point.toRoom(newCorner, curr));
    }

}
