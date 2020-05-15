package ru.job4j.collection;

/**
 * SimpleStack.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 15.05.2020.
 */
public class SimpleStack<T> {
    private SimpleLinkedList<T> linked = new SimpleLinkedList<>();

    /**
     * Returns value of the last element and deletes it from list.
     *
     * @return data Last element.
     */
    public T pop() {
        T data = linked.getLast().data;
        linked.deleteLast();
        return data;
    }

    /**
     * Adds element to the end of the list.
     *
     * @param value Value to add.
     */
    public void push(T value) {
        this.linked.add(value);
    }
}
