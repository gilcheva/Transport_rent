package com.endava.models.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainImplTest {

    @Test
    public void constructor_should_throw_when_loadCapacityLessThanMinValue() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TrainImpl("С7320В", 29, 2, 3);
        });
    }

    @Test
    public void constructor_should_throw_when_loadCapacityMoreThanMaxValue() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TrainImpl("С7320В", 501, 2, 3);
        });
    }

    @Test
    public void constructor_should_throw_when_pricePerKmLessThanMinimum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TrainImpl("С7320В", 5, 0.05, 3);
        });
    }

    @Test
    public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TrainImpl("С7320В", 5, 21, 3);
        });
    }

    @Test
    public void constructor_should_throw_when_cartsLessThanMinimum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TrainImpl("С7320В",5, 2, 0);
        });
    }

    @Test
    public void constructor_should_throw_when_cartsMoreThanMaximum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TrainImpl("С7320В", 5, 2, 16);
        });
    }

}