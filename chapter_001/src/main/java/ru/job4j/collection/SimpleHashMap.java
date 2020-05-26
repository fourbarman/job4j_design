package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleHashMap.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.05.2020.
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private Node<K, V>[] table;
    private int capacity = 16;
    private int size;
    private final double loadFactor = 0.75;
    private int modCount;

    /**
     * Constructor.
     */
    public SimpleHashMap() {
        this.table = new Node[capacity];
        this.size = 0;
        modCount = 0;
    }

    /**
     * Insert (key, value) to the table.
     *
     * @param key   Key.
     * @param value Value.
     * @return boolean result.
     */
    public boolean insert(K key, V value) {
        if (size >= capacity * loadFactor) {
            resize();
        }
        Node<K, V> node = new Node<>(key, value);
        int index = findIndex(node.key);
        if (table[index] != null) {
            return false;
        }
        this.table[index] = node;
        this.size++;
        this.modCount++;
        return true;
    }

    /**
     * Returns value by key.
     *
     * @param key Key.
     * @return V value.
     */
    public V get(K key) {
        int index = findIndex(key);
        if (this.table[index] == null) {
            return null;
        }
        return this.table[index].value;
    }

    /**
     * Removes (key, value) by key from table.
     *
     * @param key Key.
     * @return boolean result.
     */
    public boolean delete(K key) {
        int index = findIndex(key);
        if (this.table[index] == null) {
            return false;
        }
        this.table[index] = null;
        this.size--;
        this.modCount++;
        return true;
    }

    /**
     * Returns hash for key.
     *
     * @param key Key.
     * @return int hash.
     */
    private int hash(K key) {
        int h;
        if (key == null) {
            return 0;
        } else {
            h = key.hashCode();
            return h ^ (h >>> 16);
        }
    }

    /**
     * Find index for key.
     *
     * @param key Key.
     * @return int index.
     */
    private int findIndex(K key) {
        return hash(key) & (capacity - 1);
    }

    /**
     * Get size
     *
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Resizes table capacity with 2.
     */
    private void resize() {
        this.capacity = capacity * 2;
        Node<K, V>[] newTable = new Node[capacity];
        int index;
        for (Node<K, V> kvNode : table) {
            if (kvNode != null) {
                index = findIndex(kvNode.getKey());
                newTable[index] = kvNode;
            }
        }
        this.table = newTable;
    }

    /**
     * Iterator.
     *
     * @return Node(K, V) Iterator.
     */
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            final Node<K, V>[] array = table;
            final int size = getSize();
            int index = 0;
            final int currentModCount = modCount;

            /**
             * Checks if array has next element.
             *
             * @return Boolean.
             */
            @Override
            public boolean hasNext() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            /**
             * Returns next element from array.
             *
             * @return E.
             */
            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (array[index] == null && index < capacity) {
                    index++;
                }
                return array[index++];
            }
        };
    }

    /**
     * Node.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 14.05.2020.
     */
    public static class Node<G, D> {
        private final G key;
        private final D value;

        public int getHashCode() {
            return hashCode;
        }

        private final int hashCode;

        /**
         * Return key.
         *
         * @return G key.
         */
        public G getKey() {
            return key;
        }

        /**
         * Return value.
         *
         * @return D value.
         */
        public D getValue() {
            return value;
        }

        /**
         * Constructor
         *
         * @param key   G Data.
         * @param value D Node.
         */
        public Node(G key, D value) {
            this.key = key;
            this.value = value;
            if (key == null) {
                this.hashCode = 0;
            } else {
                this.hashCode = key.hashCode();
            }
        }
    }
}
