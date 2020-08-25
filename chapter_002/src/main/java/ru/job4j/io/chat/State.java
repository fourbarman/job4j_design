package ru.job4j.io.chat;

/**
 * State.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.08.2020.
 */
public interface State {
    /**
     * Returns name.
     *
     * @return String name.
     */
    String getName();

    /**
     * Returns string.
     *
     * @return String.
     */
    String print();
}
