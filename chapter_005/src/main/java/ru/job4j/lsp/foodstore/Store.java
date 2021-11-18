package ru.job4j.lsp.foodstore;

import java.util.List;

/**
 * Interface Store.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 12.10.2021.
 */
public interface Store {
    /**
     * Returns list of objects from store.
     *
     * @return List.
     */
    List<Food> getList();

    /**
     * Returns true, if food percent expired fits condition of Storage.
     * Otherwise - false.
     *
     * @param food Food object.
     * @return Boolean.
     */
    boolean accept(Food food);

    /**
     * Adds Food object to storage.
     *
     * @param food Food object.
     */
    void add(Food food);

    /**
     * Clears store.
     */
    void clear();
}
