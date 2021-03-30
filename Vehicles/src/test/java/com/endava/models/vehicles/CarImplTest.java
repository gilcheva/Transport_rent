package com.endava.models.vehicles;

import com.endava.models.vehicles.impl.CarImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarImplTest {
    @Test
    public void constructor_should_throw_when_loadCapacityLessThanMinValue() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CarImpl("С7320В", 0, 2);
        });
    }

    @Test
    public void constructor_should_throw_when_loadCapacityMoreThanMaxValue() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CarImpl("С7320В", 101, 2);
        });
    }

    @Test
    public void constructor_should_throw_when_pricePerKgPerKmLessThanMinimum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CarImpl("С7320В", 5, 0.09);
        });
    }

    @Test
    public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
        // Act
        assertThrows(IllegalArgumentException.class, () -> {
            new CarImpl("С7320В", 5, 21);
        });
    }

}