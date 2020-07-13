package byog.Core;

import byog.Helper.Point;

import byog.Helper.Tracker;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


public class JoeWorld {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static final int BOUND = 4;
    private static final int ITERATE_TIME = 3000;
    private static final long SEED = 28732;
    private static final Random RANDOM = new Random(SEED);

    // Should keep track of the USER i.e. the floor
    // walls can randomly appear aside by the floor.


    public static void createJoeWorld(TETile[][] world) {

        /** Create a point that play the role of "floor"
         * Would track its path */
        Point s = randomPoint(WIDTH, HEIGHT);
//        Point s = new Point(40, 15);
        Tracker t = new Tracker(s.x, s.y);
//        addRoom(world, t, 5, 5, 1 , 1);
//        addRoom(world, t, 6, 6, -1 , -1);
        for (int i = 0; i < ITERATE_TIME; i += 1) {
//            addRoom(world, t, randomInt(BOUND), randomInt(BOUND));

            randomOperation(world, t);
        }
    }

    private static void randomOperation(TETile[][] world, Tracker t) {
        int operationNum = randomInt(1);
        switch (operationNum) {
            case 0:
                addHallway(world, t, randomInt(BOUND));
                break;
            case 1:
                addRoom(world, t, randomInt(BOUND), randomInt(BOUND));
                break;
        }
    }

    private static void addHallway(TETile[][] world, Tracker t, int length) {
        int height;
        int width;
        if (randomBoolean()) {
            width = 1;
            height = length;
        } else {
            width = length;
            height = 1;
        }
        addRoom(world, t, width, height);
    }

    private static void addHallway(TETile[][] world, Tracker t, int length, int signX, int signY) {
        int height;
        int width;
        if (randomBoolean()) {
            width = 1;
            height = length;
        } else {
            width = length;
            height = 1;
        }
        addRoom(world, t, width, height, signX, signY);
    }

    private static void addRoom(TETile[][] world, Tracker t, int width, int height) {

        int signX = randomSign();
        int signY = randomSign();

        Point copy = new Point(t.getX(), t.getY());

        if (isOutOfBound(copy, signX * width, signY * height)) {
            return;
        }

        if (t.isOverLap(copy, signX * width, signY * height)) {
            System.out.println("IT do This !!!!!!");
            return;
        } else {
            t.insertRoom(signX * width, signY * height);
        }

        /**  To add walls, start iterate the x-axis and y-axis with -1 and end with length + 1 */
        for (int x = -1; x < width + 1; x += 1) {
            for (int y = -1; y < height + 1; y += 1) {

                Point p = new Point(copy.x + (signX * x), copy.y + (signY * y));

                /** create walls of the room*/
                if (x < 0 || x >= width || y < 0 || y >= height) {
                    
                    /** Escape if the position is floor */
                    if (t.contains(p)) {
                        continue;
                    }
                    world[p.x][p.y] = Tileset.WALL;
                } else {
                    world[p.x][p.y] = Tileset.FLOOR;
                    t.moveTo(p);
                }
            }
        }
    }

    private static void addRoom(TETile[][] world, Tracker t, int width, int height, int signX, int signY) {

        Point copy = new Point(t.getX(), t.getY());


        if (isOutOfBound(copy, signX * width, signY * height)) {
            return;
        }

        if (t.isOverLap(copy, signX * width, signY * height)) {
            System.out.println("IT do This !!!!!!");
            return;
        } else {
            t.insertRoom(signX * width, signY * height);
        }


        /**  To add walls, start iterate the x-axis and y-axis with -1 and end with length + 1 */
        for (int x = -1; x < width + 1; x += 1) {
            for (int y = -1; y < height + 1; y += 1) {

                Point p = new Point(copy.x + (signX * x), copy.y + (signY * y));

                /** create walls of the room*/
                if (x < 0 || x >= width || y < 0 || y >= height) {

                    /** Escape if the position is floor */
                    if (t.contains(p)) {
                        continue;
                    }
                    world[p.x][p.y] = Tileset.WALL;
                } else {
                    world[p.x][p.y] = Tileset.FLOOR;
                    t.moveTo(p);
                }
            }
        }
    }

    /** Detect if the desired room is out of the canvas */
    private static boolean isOutOfBound(Point p, int width, int height) {
        Point d = new Point(p.x + width, p.y + height);
        return d.x < 0 || d.x > WIDTH - 1 || d.y < 0 || d.y > HEIGHT - 1;
    }

    /** Picks a RANDOM tile */
    private static int randomInt(int n) {
        return RANDOM.nextInt(n);
    }

    /** Generate random sign, 1 and -1 for computing the direction of the room */
    private static int randomSign() {
        return RANDOM.nextInt(2) % 2 == 1 ? 1 : -1;
    }

    /** Generate Point class which contains random coordinate */
    private static Point randomPoint(int width, int height) {
        return new Point(randomInt(width), randomInt(height));
    }

    public static boolean randomBoolean() {
        return RANDOM.nextInt(2) % 2 == 1 ? true : false;
    }


    private static void printXY(int x, int y) {
        System.out.println(x + "," + y);
    }


    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // create the walls and floors
        createJoeWorld(world);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
