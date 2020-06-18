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
    private static final int WIDTH = 90;
    private static final int HEIGHT = 90;
    private static final int STARTX = 10;
    private static final int STARTY = 10;

    private static final long SEED = 28731;
    private static final Random RANDOM = new Random(SEED);

    // Assign the start point to be the left bottom corner of the hexagon
    private static void addHexagon(TETile[][] world, int startX, int startY, int n) {
        TETile tile = randomTile();
        drawLeft(world, startX, startY, n, tile);
        drawMiddle(world, startX + n - 1, startY, n, tile);
        drawRight(world, startX + 2 * n - 1, startY, n, tile);
    }

    private static void drawLeft(TETile[][] world, int startX, int startY, int n, TETile tile) {
        for (int x = 0; x < n - 1; x++) {
            for (int y = 0; y < 2 * (x + 1); y++) {
                world[startX + x][startY + 2 * n - (n + x - y)] = tile;
            }
        }
    }

    private static void drawRight(TETile[][] world, int startX, int startY, int n, TETile tile) {
        for (int x = 0; x < n - 1; x++) {
            for (int y = 0; y < 2 * (n - x - 1); y++) {
                world[startX + x][startY + 2 * n - x - y - 1] = tile;
            }
        }
    }

    private static void drawMiddle(TETile[][] world, int startX, int startY, int n, TETile tile) {
        for (int x = 0; x < n; x += 1) {
            for (int y = 0; y < 2 * n; y += 1) {
                world[startX + x][startY + 2 * n - y] = tile;
            }
        }
    }

    private static void drawVerticalDuplicate(TETile[][] world, int startX, int startY, int n, int repete) {
        for (int i = 0; i < repete; i += 1) {
            addHexagon(world, startX, startY + i * 2 * n, n);
        }
    }

    public static void addTesselation(TETile[][] world, int base) {
        int startX = STARTX;
        int startY = STARTY;
        int repete = 0;
        int middle = 3;
        for (int i = 0; i < 5; i += 1) {
            int nextStartX = nextStartX(startX, 2 * base - 1, i);
            int nextStartY = nextStartY(startY, base, i, middle);
            if (i < middle) {
                repete = 3 + i;
            } else {
                repete = middle + 5 - i - 1;
            }
            drawVerticalDuplicate(world, nextStartX, nextStartY, base, repete);
        }
    }


    private static int nextStartX(int startX, int base, int count) {
        return startX + (base * count);
    }

    private static int nextStartY(int startY, int base, int count, int middle) {
        if (count < middle) {
            return startY + base * (middle - count + 1);
        }
        return startY + base * count;

    }

    /** Picks a RANDOM tile */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.TREE;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.PLAYER;
            case 5: return Tileset.SAND;
            case 6: return Tileset.GRASS;
            default: return Tileset.FLOOR;
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

        // add tesselation with size;
        addTesselation(world,5);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
