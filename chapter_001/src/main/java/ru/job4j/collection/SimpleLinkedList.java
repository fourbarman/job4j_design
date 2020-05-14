package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * SimpleLinkedList.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.05.2020.
 */
public class SimpleLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;
    private int modCount = 0;

    /**
     * Adds element to the end of the list.
     *
     * @param value Value to add.
     */
    @SuppressWarnings("unchecked")
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            modCount++;
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
            size++;
            modCount++;
        }
    }

    /**
     * Returns element by index.
     *
     * @param index Index of returning element.
     * @return T element.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, this.getSize());
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return node.data;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * Returns size of the list.
     *
     * @return int Size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * iterator.
     *
     * @return T Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<T>(head);
    }

    /**
     * LinkedIterator.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 14.05.2020.
     */
    public class LinkedIterator<E> implements Iterator<E> {
        Node<E> root;
        int expectedModCount;

        /**
         * Constructor
         *
         * @param node E node.
         */
        public LinkedIterator(Node<E> node) {
            this.root = node;
            this.expectedModCount = modCount;
        }

        /**
         * Checks if list has next element.
         * If list has changed throws ConcurrentModificationException.
         *
         * @return Boolean.
         */
        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return root != null;
        }

        /**
         * Returns next element from list.
         *
         * @return E.
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = root.data;
            root = root.next;
            return data;
        }
    }

    /**
     * Node.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 14.05.2020.
     */
    public static class Node<T> {
        T data;
        Node next;

        /**
         * Constructor
         *
         * @param data T Data.
         * @param next T Node.
         */
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
