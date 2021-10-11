package ru.job4j.lsp.foodstore;
import java.time.LocalDate;
/**
 * Meat.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class Meat extends Food {
    public Meat(String name, LocalDate expireDate, LocalDate createDate, double price, int discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
