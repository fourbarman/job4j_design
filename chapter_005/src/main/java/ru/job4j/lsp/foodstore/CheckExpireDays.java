package ru.job4j.lsp.foodstore;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * CheckExpireDays.
 * <p>
 * Class checks percentage of product expired.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 12.10.2021.
 */
public abstract class CheckExpireDays {
    /**
     * Checks percentage of product expired and return percentage left.
     *
     * @param food Food object.
     * @return double.
     */
    public double checkExpireDays(Food food) {
        double expire = DAYS.between(food.getCreateDate(), food.getExpireDate()); //количество дней = весь срок до окончания.
        double daysLeft = DAYS.between(LocalDate.now(), food.getExpireDate()); //количество дней с сегодня до окончания срока годности
        return 100 - ((daysLeft / expire) * 100);
    }
}
