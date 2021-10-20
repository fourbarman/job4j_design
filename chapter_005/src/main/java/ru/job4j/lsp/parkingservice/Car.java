package ru.job4j.lsp.parkingservice;

/**
 * Car.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 20.10.2021.
 */
public class Car implements Vehicle {
    private int places;

    /**
     * Return parking places.
     *
     * @return Int places.
     */
    @Override
    public int getSize() {
        return this.places;
    }

    /**
     * Constructor.
     */
    public Car() {
        this.places = 1;
    }
}
