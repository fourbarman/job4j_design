package ru.job4j.lsp.foodstore;

/**
 * StrategySendToShopDiscount.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class StrategySendToShopDiscount implements Strategy {
    Shop shop;

    public StrategySendToShopDiscount(Shop shop) {
        this.shop = shop;
    }

    /**
     * Send Food to Shop.
     * Changes Food price with discount.
     *
     * @param food Food object.
     */
    @Override
    public void send(Food food) {
        double disc = (food.price / 100) * food.discount;
        food.price -= disc;
        this.shop.add(food);
    }
}
