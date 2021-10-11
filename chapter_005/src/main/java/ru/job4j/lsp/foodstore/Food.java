package ru.job4j.lsp.foodstore;

import java.time.LocalDate;

/**
 * Food.
 * <p>
 * Class represents food product.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public abstract class Food {

    String name;
    LocalDate expireDate;
    LocalDate createDate;
    double price;
    int discount;

    /**
     * Constructor.
     *
     * @param name       Name.
     * @param createDate Create date.
     * @param expireDate Expire date.
     * @param price      Price.
     * @param discount   Discount.
     */
    public Food(String name, LocalDate createDate, LocalDate expireDate, double price, int discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }
}
