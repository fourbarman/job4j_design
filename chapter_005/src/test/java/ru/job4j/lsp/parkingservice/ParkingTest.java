package ru.job4j.lsp.parkingservice;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 15.10.2021.
 */
public class ParkingTest {
    Parking parking;

    @Before
    public void setVars() {
        parking = new Parking(2, 2);
    }

    /**
     * When add a Car and there is enough places in ParkingService than return true.
     */
    @Test
    public void whenCarAddedAndEnoughParkingPlacesThanReturnTrue() {
        assertTrue(parking.park(new Car()));
    }

    /**
     * When add a Car and there is enough places in ParkingService than Car Park Places size will be reduced by 1.
     * Truck Park Places will not be changed.
     */
    @Test
    public void whenCarAddedAndEnoughParkingPlacesThanCarPlacesIsReducedByOne() {
        assertTrue(parking.park(new Car()));
        assertThat(parking.getCarParkPlace(), is(1));
        assertThat(parking.getTruckParkPlace(), is(2));
    }

    /**
     * When add a Car and there is NOT enough places in ParkingService than return false.
     */
    @Test
    public void whenCarAddedAndNotEnoughParkingPlacesThanReturnFalse() {
        parking.park(new Car());
        parking.park(new Car());
        assertFalse(parking.park(new Car()));
    }

    /**
     * When add a Truck and there is enough places in ParkingService than return true.
     */
    @Test
    public void whenTruckAddedAndEnoughParkingPlacesThanReturnTrue() {
        assertTrue(parking.park(new Truck(2)));
    }

    /**
     * When add a Truck and there is enough places in ParkingService than Truck Park Places size will be reduced by 1.
     * Car Park Places will not be changed.
     */
    @Test
    public void whenTruckAddedAndEnoughParkingPlacesThanTruckPlacesIsMinusOnePlace() {
        assertTrue(parking.park(new Truck(2)));
        assertThat(parking.getCarParkPlace(), is(2));
        assertThat(parking.getTruckParkPlace(), is(1));
    }

    /**
     * When add a Truck and there is NOT enough places for Truck in ParkingService,
     * but enough in Car park places than return true.
     * Car Park Places will be reduced by Truck size.
     */
    @Test
    public void whenTruckAddedAndNotEnoughParkingPlacesButEnoughInCarParkThanReturnTrue() {
        assertTrue(parking.park(new Truck(2)));
        assertTrue(parking.park(new Truck(2)));
        assertTrue(parking.park(new Truck(2)));
        assertThat(parking.getCarParkPlace(), is(0));
    }

    /**
     * When add a Truck and there is NOT enough places in ParkingService than return false.
     */
    @Test
    public void whenTruckAddedAndNOTEnoughParkingPlacesThanReturnFalse() {
        parking.park(new Car());
        parking.park(new Car());
        parking.park(new Truck(2));
        parking.park(new Truck(2));
        assertFalse(parking.park(new Truck(2)));
    }
}
