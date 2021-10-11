package ru.job4j.lsp.foodstore;

import java.time.LocalDate;

/**
 * Fruit.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class Fruit extends Food {
    public Fruit(String name, LocalDate expireDate, LocalDate createDate, double price, int discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
