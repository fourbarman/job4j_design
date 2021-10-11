package ru.job4j.lsp.foodstore;

/**
 * Context.
 * <p>
 * Interface for manage strategy.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public interface Context {
    void send(Food food);
}
