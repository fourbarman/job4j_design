package ru.job4j.lsp.foodstore;

/**
 * Interface Strategy.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public interface Strategy {

    /**
     * Sends Food to Store.
     *
     * @param food Food object.
     */
    void send(Food food);
}
