package ru.job4j.lsp.parkingservice;

/**
 * ParkingService.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.10.2021.
 */
public interface ParkingService {
    /**
     * Returns true if there is enough space in Parking to park vehicle.
     *
     * @param vehicle Vehicle object.
     * @return boolean.
     */
    boolean park(Vehicle vehicle);
}
