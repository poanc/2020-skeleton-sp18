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
        nextFirst = 4;
        nextLast = 5;
    }


    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        nextFirst = (nextFirst - 1) % capacity;
    }

    public void addLast(T item) {
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
        int p = (nextFirst + 1) % capacity;
        T toReturn = items[p];
        items[p] = null;
        size--;
        nextFirst = (nextFirst + 1) % capacity;
        return toReturn;
    }

    public T removeLast() {
        int p = (nextLast - 1) % capacity;
        T toReturn = items[p];
        items[p] = null;
        size--;
        nextLast = (nextLast + 1) % capacity;
        return toReturn;
    }

    public T get(int index) {
        if (index > size) return null;
        int p = (nextFirst + 1 + index) % capacity;
        return items[p];
    }

    public static void main (String[] parms) {
        ArrayDeque<Integer> x = new ArrayDeque<>();
        x.addLast(5);
        x.addFirst(4);
        x.addLast(6);
        System.out.println(x.get(2));
        x.printDeque();

        x.removeLast();
        x.removeFirst();

        x.printDeque();
    }
}
