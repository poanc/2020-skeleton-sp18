package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    // Assign the start point to be the left bottom corner of the hexagon
    public static void addHexagon(TETile[][] world, int startX, int startY, int n) {
        addLeft(world, startX, startY, n);
        addMiddle(world, startX + n - 1, startY, n);
        addRight(world, startX + 2 * n - 1, startY, n);
    }

    private static void addLeft(TETile[][] world, int startX, int startY, int n) {
        for (int x = 0; x < n - 1; x++) {
            for (int y = 0; y < 2 * (x + 1); y++) {
                world[startX + x][startY + 2 * n - (n + x - y)] = Tileset.TREE;
            }
        }
    }

    private static void addRight(TETile[][] world, int startX, int startY, int n) {
        for (int x = 0; x < n - 1; x++) {
            for (int y = 0; y < 2 * (n - x - 1); y++) {
                world[startX + x][startY + 2 * n - x - y -1] = Tileset.TREE;
            }
        }
    }

    private static void addMiddle(TETile[][] world, int startX, int startY, int n) {
        for (int x = 0; x < n; x += 1) {
            for (int y = 0; y < 2 * n; y += 1) {
                world[startX + x][startY + 2 * n - y] = Tileset.TREE;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WATER;
            }
        }
        world[5][5] = Tileset.GRASS;
        // fills in a block 14 tiles wide by 4 tiles tall
        addHexagon(world, 5, 5, 4);


        // draws the world to the screen
        ter.renderFrame(world);
    }
}
