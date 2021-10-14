package ru.job4j.lsp.parkingservice;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {
    /**
     * When add a Car and there is enough places in ParkingService than return true.
     */
    @Test
    public void whenCarAddedAndEnoughParkingPlacesThanReturnTrue() {
        ParkingService parking = new Parking(20, 10);
        assertTrue(parking.park(new Car(1)));
    }

    /**
     * When add a Car and there is enough places in ParkingService than Car Park Places size will be reduced by 1.
     * Truck Park Places will not be changed.
     */
    @Test
    public void whenCarAddedAndEnoughParkingPlacesThanCarPlacesIsReducedByOne() {
        ParkingService parking = new Parking(20, 10);
        assertThat(CarParkPlaces.getPlacesNumber(), is(19));
        assertThat(TruckParkPlaces.getPlacesNumber(), is(10));
    }

    /**
     * When add a Car and there is NOT enough places in ParkingService than return false.
     */
    @Test
    public void whenCarAddedAndNotEnoughParkingPlacesThanReturnFalse() {
        ParkingService parking = new Parking(0, 10);
        assertFalse(parking.park(new Car(1)));
    }

    /**
     * When add a Truck and there is enough places in ParkingService than return true.
     */
    @Test
    public void whenTruckAddedAndEnoughParkingPlacesThanReturnTrue() {
        ParkingService parking = new Parking(20, 10);
        assertTrue(parking.park(new Truck(2)));
    }

    /**
     * When add a Truck and there is enough places in ParkingService than Truck Park Places size will be reduced by 1.
     * Car Park Places will not be changed.
     */
    @Test
    public void whenTruckAddedAndEnoughParkingPlacesThanTruckPlacesIsMinusOnePlace() {
        ParkingService parking = new Parking(20, 10);
        assertTrue(parking.park(new Truck(2)));
        assertThat(TruckParkPlaces.getPlacesNumber(), is(8));
        assertThat(CarParkPlaces.getPlacesNumber(), is(20));
    }

    /**
     * When add a Truck and there is NOT enough places for Truck in ParkingService,
     * but enough in Car park places than return true.
     * Car Park Places will be reduced by Truck size.
     */
    @Test
    public void whenTruckAddedAndNotEnoughParkingPlacesButEnoughInCarParkThanReturnTrue() {
        ParkingService parking = new Parking(20, 0);
        assertTrue(parking.park(new Truck(2)));
        assertThat(CarParkPlaces.getPlacesNumber(), is(18));
    }

    /**
     * When add a Truck and there is NOT enough places in ParkingService than return false.
     */
    @Test
    public void whenCarAddedAndEnoughParkingPlacesThanReturnTrue() {
        ParkingService parking = new Parking(0, 0);
        assertFalse(parking.park(new Truck(2)));
    }
}
