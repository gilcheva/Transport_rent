package com.endava.models.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipImplTest {
    @Test
    public void constructor_should_throw_when_loadCapacityLessThanMinValue() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ShipImpl("С7320В",19, 2);
        });
    }

    @Test
    public void constructor_should_throw_when_loadCapacityMoreThanMaxValue() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ShipImpl("С7320В", 1001, 2);
        });
    }

    @Test
    public void constructor_should_throw_when_pricePerKmLessThanMinimum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ShipImpl("С7320В", 20, 0);
        });
    }

    @Test
    public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ShipImpl("С7320В", 20, 21);
        });
    }
}