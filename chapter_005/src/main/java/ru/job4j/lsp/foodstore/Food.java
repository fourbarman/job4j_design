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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String toString() {
        return "{" + this.getName()
                + ", " + this.getCreateDate()
                + ", " + this.getExpireDate()
                + ", " + this.getExpireDate()
                + ", " + this.getPrice()
                + ", " + this.getDiscount()
                + "}";
    }

    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private double price;
    private int discount;

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
