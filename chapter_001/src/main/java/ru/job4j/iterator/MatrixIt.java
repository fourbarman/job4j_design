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
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    /**
     * Constructor
     *
     * @param data Array[][] of int.
     */
    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Checks if array has next element.
     *
     * @return Boolean.
     */
    @Override
    public boolean hasNext() {
        if (row + 1 == data.length) {
            return column < data[row].length;
        }
        return row < data.length;
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
        while (column >= data[row].length && row < data.length) {
            row++;
            column = 0;
        }
        return data[row][column++];
    }
}
