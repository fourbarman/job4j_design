package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EvenIt.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 06.05.2020.
 */
public class EvenIt implements Iterator<Integer> {
    private int[] data;
    private int point;

    /**
     * Constructor
     *
     * @param data Array of int.
     */
    public EvenIt(int[] data) {
        this.data = data;
        point = -1;
        fixNext();
    }

    /**
     * Checks if point < array length.
     *
     * @return Boolean.
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Returns next even element from array.
     *
     * @return Int.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = data[point];
        fixNext();
        return result;
    }

    /**
     * Helper method.
     * Grows point while array element is not even.
     */
    private void fixNext() {
        point++;
        while (point < data.length && data[point] % 2 == 1) {
            point++;
        }
    }
}
