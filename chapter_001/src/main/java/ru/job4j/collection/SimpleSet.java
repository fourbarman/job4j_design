package ru.job4j.collection;

import java.util.Iterator;

/**
 * SimpleSet.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 18.05.2020.
 */
public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> set = new SimpleArray<>();

    /**
     * Adds element.
     * Prevents adding duplicates.
     *
     * @param t Value to add.
     */
    public void add(T t) {
        for (T el : set) {
            if (t.equals(el)) {
                return;
            }
        }
        this.set.add(t);
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
