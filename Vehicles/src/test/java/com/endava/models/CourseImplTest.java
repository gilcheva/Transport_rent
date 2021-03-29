package com.endava.models;

import com.endava.models.contracts.Course;
import com.endava.models.vehicles.CarImpl;
import com.endava.models.vehicles.contracts.Vehicle;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CourseImplTest {
    private Vehicle testVehicle;

    @Before
    public void before() {
        testVehicle = new CarImpl("С7320В", 14, 2);
    }

    @Test
    public void constructor_should_throw_when_startLocationLengthLessThanMinimum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new CourseImpl("", "destination", 30, testVehicle);
        });
    }

    @Test
    public void constructor_should_throw_when_destinationLengthLessThanMinimum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new CourseImpl("start", "", 30, testVehicle);
        });
    }

    @Test
    public void constructor_should_throw_when_startLocationIsNull() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new CourseImpl(null, "destination", 30, testVehicle);
        });
    }

    @Test
    public void constructor_should_throw_when_destinationIsNull() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new CourseImpl("start", null, 30, testVehicle);
        });
    }

    @Test
    public void constructor_should_throw_when_startLocationLengthMoreThanMaximum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new CourseImpl(Arrays.toString(new Character[26]), "destination", 30, testVehicle);
        });
    }

    @Test
    public void constructor_should_throw_when_destinationLengthMoreThanMaximum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new CourseImpl("start", Arrays.toString(new Character[26]), 30, testVehicle);
        });
    }

    @Test
    public void constructor_should_throw_when_distanceLessThanMinimum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new CourseImpl("start", "destination", 4, testVehicle);
        });
    }

    @Test
    public void constructor_should_throw_when_distanceMoreThanMaximum() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new CourseImpl("", "destination", 5001, testVehicle);
        });
    }
}