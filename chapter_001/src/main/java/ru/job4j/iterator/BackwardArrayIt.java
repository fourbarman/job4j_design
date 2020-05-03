package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * BackwardArrayIt.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.05.2020.
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    /**
     * Constructor
     *
     * @param data Array of int.
     */
    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length - 1;
    }

    /**
     * Checks if point >= 0.
     *
     * @return Boolean.
     */
    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    /**
     * Returns next element from array.
     *
     * @return Int.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}