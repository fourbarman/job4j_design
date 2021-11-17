package ru.job4j.isp;

/**
 * Action.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 02.11.2021.
 */
public interface Action {
    /**
     * Executes Action.
     */
    String act();

    /**
     * Returns Action name;
     *
     * @return String name.
     */
    String getActionName();
}
