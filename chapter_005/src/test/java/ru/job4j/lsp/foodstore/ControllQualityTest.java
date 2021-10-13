package ru.job4j.lsp.foodstore;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 10.10.2021.
 */
public class ControllQualityTest {
    ControllQuality controllQuality;
    Shop shop;
    Trash trash;
    Warehouse warehouse;
    Food apple;
    Food pork;
    Food potato;
    Food dill;

    /**
     * Set variables before tests.
     * Apple is > 75% expired.
     * Potato is 25 - 75% expired.
     * Pork is < 25% expired.
     * Dill is > 100% expired.
     */
    @Before
    public void setVars() {
        shop = new Shop();
        trash = new Trash();
        warehouse = new Warehouse();
        controllQuality = new ControllQuality(List.of(shop, trash, warehouse));
        apple = new Fruit("Apple",
                LocalDate.now().minusYears(2),
                LocalDate.now().plusMonths(1), 200, 10);
        potato = new Vegetable("Potato",
                LocalDate.now().minusYears(1),
                LocalDate.now().plusYears(1), 100, 10);
        pork = new Meat("Pork",
                LocalDate.now().minusMonths(1),
                LocalDate.now().plusYears(2), 500, 10);
        dill = new Grocery("Dill",
                LocalDate.now().minusYears(1),
                LocalDate.now().minusDays(1), 50, 10);
    }

    /**
     * Test when add Food with 25 - 74 % expired, than it will be added to shop storage.
     */
    @Test
    public void whenAdd25to75PercentExpiredThanAddToShop() {
        controllQuality.sendFood(potato);
        assertEquals(1, shop.getList().size());
        assertEquals(shop.getList().get(0), potato);
        assertEquals(shop.getList().get(0).getPrice(), potato.getPrice(), 0.0);
    }

    /**
     * Test when add Food with > 74 % expired, than it will be added to shop with doscount storage.
     */
    @Test
    public void whenAddMore75PercentExpiredThanAddToShopWithDiscount() {
        controllQuality.sendFood(apple);
        assertEquals(1, shop.getList().size());
        assertEquals(shop.getList().get(0), apple);
        assertEquals(shop.getList().get(0).getPrice(), 180, 0.0);
    }

    /**
     * Test when add Food with < 25% expired, than it will be added to warehouse storage.
     */
    @Test
    public void whenAddLess25PercentExpiredThanAddToWarehouse() {
        controllQuality.sendFood(pork);
        assertEquals(1, warehouse.getList().size());
        assertEquals(warehouse.getList().get(0), pork);
    }

    /**
     * Test when add Food with > 100% expired, than it will be added to trash storage.
     */
    @Test
    public void whenAddMore100PercentExpiredThanAddToTrash() {
        controllQuality.sendFood(dill);
        assertEquals(1, trash.getList().size());
        assertEquals(trash.getList().get(0), dill);
    }
}
