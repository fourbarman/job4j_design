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
     * Returns false if index and hashCode are the same, but key are not equal.
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
        if (table[index] != null && hash(node.key) == hash(table[index].key)) {
            if (!(node.key == table[index].key || node.key.equals(table[index].key))) {
                return false;
            }
            table[index].key = node.key;
            table[index].value = node.value;
            this.modCount++;
            return true;
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
        if (hash(key) == hash(this.table[index].key)
                && (key == table[index].key || key.equals(table[index].key))) {
            return this.table[index].value;
        }
        return null;
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
        if (hash(key) == hash(this.table[index].key)
                && (key == table[index].key || key.equals(table[index].key))) {
            this.table[index] = null;
            this.size--;
            this.modCount++;
            return true;
        }
        return false;
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
                while (index < capacity && array[index] == null) {
                    index++;
                }
                return index < capacity;
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
        private G key;
        private D value;

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
        }
    }
}
