package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 4;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hashCode = hash(key);
        return buckets[hashCode].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (loadFactor() > MAX_LF) {
            resize(buckets.length * 2);
        }
        int hashCode = hash(key);
        if (!buckets[hashCode].containsKey(key)) {
            size += 1;
        }
        buckets[hashCode].put(key, value);

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
        for (ArrayMap am : buckets) {
            Set<K> ks = am.keySet();
            toReturn.addAll(ks);
        }
        return toReturn;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int hashCode = hash(key);
        V toReturn = buckets[hashCode].remove(key);
        if (toReturn != null) {
            size -= 1;
        }
        return toReturn;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        int hashCode = hash(key);
        V toReturn = buckets[hashCode].remove(key);
        if (toReturn != null) {
            size -= 1;
        }
        return toReturn;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    private int hash(K key, int length) {
        if (key == null) {
            return 0;
        }
        int numBuckets = length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    private void resize(int length) {
        ArrayMap<K, V>[] newBuckets = new ArrayMap[length];
        for (int i = 0; i < newBuckets.length; i += 1) {
            newBuckets[i] = new ArrayMap<>();
        }

        Set<K> s = keySet();
        for (K k : s) {
            int hashCode = hash(k, length);
            newBuckets[hashCode].put(k, get(k));
        }
        buckets = newBuckets;
    }
}
