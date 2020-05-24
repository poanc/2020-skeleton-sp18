public class ArrayDeque<T> {


    private int size;
    private T items[];
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
        nextFirst = (nextFirst - 1) % capacity;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size++;
        nextLast = (nextLast + 1) % capacity;

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
        while(count != 0) {
            System.out.print(items[p + 1] + " ");
            count--;
            p = (p + 1) % capacity;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) return null;
        if (size / items.length < 0.25) {
            resize(size / 2);
        }

        int p = (nextFirst + 1) % capacity;
        T toReturn = items[p];
        items[p] = null;
        size--;
        nextFirst = (nextFirst + 1) % capacity;
        return toReturn;
    }

    public T removeLast() {
        if (size == 0) return null;
        double ua = size / (double) items.length;
        if (ua < 0.25) {
            resize(items.length / 2);
        }

        int p = (nextLast - 1) % capacity;
        T toReturn = items[p];
        items[p] = null;
        size--;
        nextLast = (nextLast - 1) % capacity;
        return toReturn;
    }

    public T get(int index) {
        if (index > size) return null;
        int p = (nextFirst + 1 + index) % capacity;
        return items[p];
    }

    public void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        System.arraycopy(items, 0, copy, 0, size);
        items = copy;
        this.capacity = capacity;
        nextFirst = capacity - 1;
        nextLast = size;
    }


}
