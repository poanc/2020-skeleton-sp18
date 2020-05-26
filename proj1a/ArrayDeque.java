public class ArrayDeque<T> {


    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int capacity;

    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }


    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size++;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size++;
        nextLast = plusOne(nextLast);

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int count = size;
        int p = nextFirst;
        while  (count != 0) {
            System.out.print(items[p + 1] + " ");
            count--;
            p = (p + 1) % capacity;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (getUA() < 0.25) {
            resize(items.length / 2);
        }

        nextFirst = plusOne(nextFirst);
        int p = nextFirst;
        T toReturn = items[p];
        items[p] = null;
        size--;
        return toReturn;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (getUA() < 0.25) {
            resize(items.length / 2);
        }

        nextLast = minusOne(nextLast);
        int p = nextLast;
        T toReturn = items[p];
        items[p] = null;
        size--;
        return toReturn;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        int p = (nextFirst + 1 + index) % capacity;
        return items[p];
    }

    private void resize(int newCapacity) {
        T[] copy = (T[]) new Object[newCapacity];
        int count = size;
        int start = 0;
        int p = nextFirst + 1;
        while (count != 0) {
            copy[start] = items[Math.floorMod(p, capacity)];
            start++;
            count--;
            p++;
        }
        items = copy;
        capacity = newCapacity;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** return current items usage ratio*/
    private double getUA() {
        return size / (double) items.length;
    }

    private int minusOne(int n) {
        return Math.floorMod(n - 1, capacity);

    }

    private int plusOne(int n) {
        return Math.floorMod(n + 1, capacity);
    }


}
