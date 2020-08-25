package ru.job4j.io.chat;

/**
 * Stop.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.08.2020.
 */
public class Stop implements State {
    String name = "STOP";

    /**
     * Returns name.
     *
     * @return String name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns null.
     *
     * @return String null.
     */
    @Override
    public String print() {
        return null;
    }
}
