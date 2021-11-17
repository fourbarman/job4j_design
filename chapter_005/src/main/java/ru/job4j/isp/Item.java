package ru.job4j.isp;

/**
 * Item.
 * <p>
 * Simple menu entry.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 02.11.2021.
 */
public class Item implements Action {
    String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public String act() {
        return this.name;
    }

    @Override
    public String getActionName() {
        return this.name;
    }
}
