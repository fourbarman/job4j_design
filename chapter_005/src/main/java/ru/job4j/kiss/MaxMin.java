package ru.job4j.kiss;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * MaxMin.
 * <p>
 * Class for searching max or min element from list.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 28.09.2021.
 */
public class MaxMin {
    /**
     * Searches element from list by comparator and predicate.
     */
    private <T> T calculate(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        Iterator<T> i = value.iterator();
        T candidate = i.next();
        while (i.hasNext()) {
            T next = i.next();
            if (predicate.test(comparator.compare(next, candidate))) {
                candidate = next;
            }
        }
        return candidate;
    }

    /**
     * Searches max element from list by comparator.
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return calculate(value, comparator, x -> (x > 0));
    }

    /**
     * Searches min element from list by comparator.
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return calculate(value, comparator, x -> (x < 0));
    }
}
