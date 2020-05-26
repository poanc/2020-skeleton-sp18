public class LinkedListDeque<T> {

    private static class Node<T> {
        private T item;
        private Node next;
        private Node prev;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }


    public void addFirst(T item) {
        size++;
        Node<T> first = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = first;
        sentinel.next = first;
    }

    public void addLast(T item) {
        size++;
        Node<T> last = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = last;
        sentinel.prev = last;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel;
        int count = size;
        while (count != 0) {
            System.out.print(p.next.item);
            System.out.print(' ');
            p = p.next;
            count--;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }

        Node toReturn = sentinel.next;
        toReturn.next.prev = toReturn.prev;
        sentinel.next = toReturn.next;
        size--;

        return (T) toReturn.item;

    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }

        Node toReturn = sentinel.prev;
        toReturn.prev.next = toReturn.next;
        sentinel.prev = toReturn.prev;
        size--;

        return (T) toReturn.item;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        Node p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index--;
        }
        return (T) p.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node node, int index) {
        if (index == 0) {
            return (T) node.item;
        }
        return getRecursive(node.next, index - 1);

    }

}
