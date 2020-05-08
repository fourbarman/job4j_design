package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArray.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 08.05.2020.
 */
public class SimpleArray<T> implements Iterable<T> {
    private T[] arr;
    private int arrSize;
    private int currentIndex;

    /**
     * Constructor
     *
     * @param arrSize Size of array.
     */
    public SimpleArray(int arrSize) {
        this.currentIndex = 0;
        this.arr = (T[]) new Object[arrSize];
        this.arrSize = arr.length;
    }

    /**
     * Adds element.
     *
     * @param model Model to add.
     */
    public void add(T model) {
        if (currentIndex < arrSize) {
            arr[currentIndex] = model;
            currentIndex++;
        }
    }

    /**
     * Sets element at index.
     *
     * @param index Index to add to.
     * @param model Model to set.
     */
    public void set(int index, T model) {
        if (index < arrSize && index < currentIndex - 1) {
            arr[index] = model;
        } else {
            throw new ArrayStoreException();
        }
    }

    /**
     * Removes element from array and moves rest elements to the left.
     *
     * @param index Index of deleting element.
     */
    public void remove(int index) {
        if (index < arrSize && index < currentIndex - 1) {
            arr[index] = null;
            System.arraycopy(arr, index + 1, arr, index, currentIndex - index + 1);
            currentIndex--;
        } else {
            throw new ArrayStoreException();
        }
    }

    /**
     * Returns element by index.
     *
     * @param index Index of returning element.
     * @return T element.
     */
    public T get(int index) {
        if (index < currentIndex && index < arrSize) {
            return arr[index];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * toString.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(arr, 0, currentIndex));
    }

    /**
     * iterator.
     *
     * @return T Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator<T>(arr);
    }

    /**
     * SimpleIterator.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 08.05.2020.
     */
    public class SimpleIterator<E> implements Iterator<E> {
        int index = 0;
        E[] array;

        /**
         * Constructor
         *
         * @param array E array.
         */
        public SimpleIterator(E[] array) {
            this.array = array;
        }

        /**
         * Checks if array has next element.
         *
         * @return Boolean.
         */
        @Override
        public boolean hasNext() {
            return index < array.length && index < currentIndex;
        }

        /**
         * Returns next element from array.
         *
         * @return E.
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[index++];
        }
    }
}
