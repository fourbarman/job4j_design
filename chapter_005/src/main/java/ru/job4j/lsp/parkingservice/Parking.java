package ru.job4j.lsp.parkingservice;

/**
 * Parking.
 * Represents parking service.
 * Should place Vehicle objects should send to appropriate storage.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 20.10.2021.
 */
public class Parking implements ParkingService {
    private ParkPlace carParkPlace;
    private ParkPlace truckParkPlace;

    /**
     * Constructor.
     *
     * @param carPlaces   Car places storage.
     * @param truckPlaces Truck places storage.
     */
    public Parking(int carPlaces, int truckPlaces) {
        this.carParkPlace = new CarParkPlace(carPlaces);
        this.truckParkPlace = new TruckParkPlace(truckPlaces);
    }

    /**
     * Sends Vehicle to appropriate storage.
     *
     * @param vehicle Vehicle object.
     * @return boolean result.
     */
    @Override
    public boolean park(Vehicle vehicle) {
        return truckParkPlace.accept(vehicle) || carParkPlace.accept(vehicle);
    }

    /**
     * Return Car parking places.
     *
     * @return Int places.
     */
    public int getCarParkPlace() {
        return carParkPlace.getPlaceNumber();
    }

    /**
     * Return Truck parking places.
     *
     * @return Int places.
     */
    public int getTruckParkPlace() {
        return truckParkPlace.getPlaceNumber();
    }
}
