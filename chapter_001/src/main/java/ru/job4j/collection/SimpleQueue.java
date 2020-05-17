package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * SimpleQueue.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.05.2020.
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Returns value of the first element and deletes it from queue.
     * If in.size == 0 than trow NoSuchElementException.
     * Loop while in.size != 0, push last element from in to out.
     *
     * @return data First element.
     */
    public T poll() {
        if (in.getSize() == 0) {
            throw new NoSuchElementException();
        }
        while (in.getSize() != 0) {
            out.push(in.pop());
        }
        return out.pop();
    }

    /**
     * Adds element to the end of the list.
     *
     * @param value Value to add.
     */
    public void push(T value) {
        in.push(value);
    }
}
