package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 07.05.2020.
 */
public class Converter {
    /**
     * Returns Iterator<Integer>.
     *
     * @param it Iterator of iterators.
     * @return Iterator<Integer>.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> iterator = (new ArrayList<Integer>()).iterator();

            /**
             * Checks if iterator has next element.
             *
             * @return Boolean.
             */
            @Override
            public boolean hasNext() {
                while (it.hasNext() && !iterator.hasNext()) {
                    iterator = it.next();
                }
                return iterator.hasNext();
            }

            /**
             * Returns next even element from iterator.
             *
             * @return Int.
             */
            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator.next();
            }
        };
    }
}
