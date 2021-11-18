package ru.job4j.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Trash.
 * <p>
 * Class for storing Food objects.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class Trash extends CheckExpireDays implements Store {
    public Trash() {
        this.list = new ArrayList<>();
    }

    public List<Food> getList() {
        return list;
    }

    private List<Food> list;

    @Override
    public boolean accept(Food food) {
        return 0 <= checkExpireDays(food) && checkExpireDays(food) >= 100;
    }

    /**
     * Adds Food to List.
     *
     * @param food Food object.
     */
    public void add(Food food) {
        this.list.add(food);
    }

    /**
     * Clears store.
     */
    @Override
    public void clear() {
        this.list.clear();
    }
}
