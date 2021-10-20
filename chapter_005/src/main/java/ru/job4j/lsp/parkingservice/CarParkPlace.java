package ru.job4j.lsp.parkingservice;
/**
 * CarParkPlace.
 * Represents parking places storage.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 20.10.2021.
 */
public class CarParkPlace implements ParkPlace {
    private int places;

    /**
     * Returns true if Vehicle fits conditions.
     * Returns false if not.
     * Returns false if places < vehicle.size.
     * @param vehicle Vehicle object.
     * @return boolean.
     */
    @Override
    public boolean accept(Vehicle vehicle) {
        int vehicleSize = vehicle.getSize();
        if (vehicleSize == 1 && this.places >= vehicleSize) {
            this.places -= 1;
            return true;
        } else if (vehicleSize >= 1 && this.places >= vehicleSize) {
            this.places -= vehicleSize;
            return true;
        }
        return false;
    }

    /**
     * Constructor.
     * @param places int places.
     */
    public CarParkPlace(int places) {
        this.places = places;
    }

    /**
     * Returns places.
     * @return int places.
     */
    public int getPlaceNumber() {
        return this.places;
    }
}
