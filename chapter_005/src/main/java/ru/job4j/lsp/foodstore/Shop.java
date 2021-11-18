package ru.job4j.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Shop.
 * <p>
 * Class for storing Food objects.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class Shop extends CheckExpireDays implements Store {
    public Shop() {
        this.list = new ArrayList<>();
    }

    public List<Food> getList() {
        return list;
    }

    List<Food> list;

    @Override
    public boolean accept(Food food) {
        return 25 <= checkExpireDays(food) && checkExpireDays(food) < 100;
    }

    /**
     * Adds Food to List.
     *
     * @param food Food object.
     */
    public void add(Food food) {
        if (75 <= checkExpireDays(food)) {
            food.setPrice(food.getPrice() - (food.getPrice() / 100) * food.getDiscount());
        }
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
