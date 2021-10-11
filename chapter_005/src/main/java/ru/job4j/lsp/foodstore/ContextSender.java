package ru.job4j.lsp.foodstore;

/**
 * ContextSender.
 * <p>
 * Execute strategy method.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class ContextSender implements Context {
    Strategy strategy;

    ContextSender(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Execute strategy method send().
     *
     * @param food Food object.
     */
    public void send(Food food) {
        this.strategy.send(food);
    }
}
