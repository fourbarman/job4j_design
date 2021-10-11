package ru.job4j.lsp.foodstore;

/**
 * StrategySendToWarehouse.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class StrategySendToWarehouse implements Strategy {
    Warehouse warehouse;

    public StrategySendToWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Send Food to Warehouse.
     *
     * @param food Food object.
     */
    @Override
    public void send(Food food) {
        this.warehouse.add(food);
    }
}
