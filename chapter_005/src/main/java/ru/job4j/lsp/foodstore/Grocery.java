package ru.job4j.lsp.foodstore;

import java.time.LocalDate;

public class Grocery extends Food {
    /**
     * Constructor.
     *
     * @param name       Name.
     * @param createDate Create date.
     * @param expireDate Expire date.
     * @param price      Price.
     * @param discount   Discount.
     */
    public Grocery(String name, LocalDate createDate, LocalDate expireDate, double price, int discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
