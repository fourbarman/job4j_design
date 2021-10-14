package ru.job4j.lsp.parkingservice;

/**
 * ParkPlace.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.10.2021.
 */
public interface ParkPlace {
    /**
     * Check vehicle size, enough space in appropriate place and return true if success.
     *
     * @param vehicle Vehicle object.
     * @return boolean.
     */
    boolean accept(Vehicle vehicle);

    /**
     * Returns free places number.
     *
     * @return int number of places.
     */
    int getPlaceNumber();
}
