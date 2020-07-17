package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.key.compareTo(key) == 0) {
            return p.value;
        } else if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else if (p.key.compareTo(key) < 0) {
            return getHelper(key, p.right);
        }
        return null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        } else if (p.key.compareTo(key) == 0) {
            p.value = value;
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else if (p.key.compareTo(key) < 0) {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> toReturn = new HashSet<>();
        inOrderTraverse(root, toReturn);
        return toReturn;

    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (get(key) == null) {
            return null;
        }
        return removeHelper(key, root);
    }

    private V removeHelper(K key, Node p) {
        Node keyNode = findKeyNode(key);
        Node parentNode = findParent(key);

        // no child
        if (keyNode.left == null && keyNode.right == null) {
            if (keyNode.equals(root)) {
                clear();
            } else if (parentNode.right != null && parentNode.right.key.compareTo(key) == 0) {
                parentNode.right = null;
            } else if (parentNode.left != null && parentNode.left.key.compareTo(key) == 0) {
                parentNode.left = null;
            }

        // two child
        } else if (keyNode.left != null && keyNode.right != null) {

            if (keyNode.equals(root)) {
                root = keyNode.left;
                root.right = keyNode.right;
            } else if (parentNode.right != null && parentNode.right.key.compareTo(key) == 0) {
                Node leftNode = findMostLeftNode(keyNode);
                parentNode.right = leftNode;
                leftNode.right = keyNode.right;
            } else {
                Node rightNode = findMostRightNode(keyNode);
                parentNode.left = rightNode;
                rightNode.right = keyNode.left;
            }

        // one child
        } else {

            if (keyNode.equals(root)) {
                if (keyNode.right != null) {
                    root = keyNode.right;
                } else {
                    root = keyNode.left;
                }
            } else if (parentNode.right != null && parentNode.right.key.compareTo(key) == 0) {

                if (keyNode.right != null) {
                    parentNode.right = keyNode.right;
                } else {
                    parentNode.right = keyNode.left;
                }

            } else if (parentNode.left != null && parentNode.left.key.compareTo(key) == 0) {

                if (keyNode.right != null) {
                    parentNode.left = keyNode.right;
                } else {
                    parentNode.left = keyNode.left;
                }
            }
        }

        size -= 1;
        return keyNode.value;
    }

    private Node findParent(K key) {
        return findParentHelper(key, root);
    }

    private Node findParentHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.left != null && p.left.key.compareTo(key) == 0) {
            return p;
        } else if (p.right != null && p.right.key.compareTo(key) == 0) {
            return p;
        } else if (p.key.compareTo(key) > 0) {
            p = findParentHelper(key, p.left);
        } else if (p.key.compareTo(key) < 0) {
            p = findParentHelper(key, p.right);
        }
        return p;
    }

    private Node findKeyNode(K key) {
        return findKeyNodeHelper(key, root);
    }

    private Node findKeyNodeHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.key.compareTo(key) == 0) {
            return p;
        } else if (p.key.compareTo(key) > 0) {
            p = findKeyNodeHelper(key, p.left);
        } else if (p.key.compareTo(key) < 0) {
            p = findKeyNodeHelper(key, p.right);
        }
        return p;
    }

    private Node findMostLeftNode(Node p) {
        if (p.left == null) {
            return p;
        }
        return findMostLeftNode(p.left);
    }

    private Node findMostRightNode(Node p) {
        if (p.right == null) {
            return p;
        }
        return findMostLeftNode(p.right);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    private void inOrderTraverse(Node p, Set<K> s) {
        if (p == null) {
            return;
        }
        inOrderTraverse(p.left, s);
        s.add(p.key);
        inOrderTraverse(p.right, s);
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);

    }
}
