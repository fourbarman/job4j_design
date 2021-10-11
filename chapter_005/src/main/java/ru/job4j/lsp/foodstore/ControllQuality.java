package ru.job4j.lsp.foodstore;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

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
    Context context;
    Warehouse warehouse;
    Trash trash;
    Shop shop;

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
            if (checkExpireDays(food) < 25) {
                context = new ContextSender(new StrategySendToWarehouse(warehouse));
                context.send(food);
            } else if (25 <= checkExpireDays(food) && checkExpireDays(food) <= 75) {
                context = new ContextSender(new StrategySendToShop(shop));
                context.send(food);
            } else if (75 < checkExpireDays(food) && checkExpireDays(food) < 100) {
                context = new ContextSender(new StrategySendToShopDiscount(shop));
                context.send(food);
            } else {
                context = new ContextSender(new StrategySendToTrash(trash));
                context.send(food);
            }
        }
    }

    /**
     * Calculates percentage of expired left.
     *
     * @param food Food object.
     * @return Percentage.
     */
    private double checkExpireDays(Food food) {
        double expire = DAYS.between(food.createDate, food.expireDate);
        double daysLeft = DAYS.between(LocalDate.now(), food.expireDate);
        return 100 - ((daysLeft / expire) * 100);
    }

    /**
     * Set warehouse.
     *
     * @param warehouse Warehouse.
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Set warehouse.
     *
     * @param trash Trash.
     */
    public void setTrash(Trash trash) {
        this.trash = trash;
    }

    /**
     * Set warehouse.
     *
     * @param shop Shop.
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
