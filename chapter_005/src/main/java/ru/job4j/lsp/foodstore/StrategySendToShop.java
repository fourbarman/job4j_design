package ru.job4j.lsp.foodstore;

/**
 * StrategySendToShop.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class StrategySendToShop implements Strategy {
    Shop shop;

    public StrategySendToShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * Send Food to Shop.
     *
     * @param food Food object.
     */
    @Override
    public void send(Food food) {
        this.shop.add(food);
    }
}
