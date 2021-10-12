package ru.job4j.lsp.foodstore;

import java.util.List;

/**
 * FoodSender.
 * <p>
 * <p>
 * Class sends Food objects to Store.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class ControllQuality {
    private List<Store> storeList;

    public ControllQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    /**
     * If 25% expired than send Food to Warehouse.
     * If 25% to 75% expired than send Food to Shop.
     * If 75% to 100% expired than send Food to Shop with discount.
     * Else - send to Trash.
     *
     * @param food Food object.
     */
    public void sendFood(Food food) {
        if (food != null) {
            for (Store store : storeList) {
                if (store.accept(food)) {
                    store.add(food);
                }
            }
        }
    }

}
