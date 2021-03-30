package com.endava.models;

import com.endava.models.courses.Course;
import com.endava.models.courses.Rent;
import com.endava.models.courses.impl.CourseImpl;
import com.endava.models.courses.impl.RentImpl;
import com.endava.models.vehicles.impl.AirplaneImpl;
import com.endava.models.vehicles.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentImplTest {
    private Vehicle testVehicle;
    private Course testCourse;

    @BeforeEach
    public void before() {
        testVehicle = new AirplaneImpl("С7320В", 30, 20, true);
        testCourse = new CourseImpl("start","destination",30, testVehicle);
    }

    @Test
    public void constructor_should_throw_when_additionalCostsAreNull() {
        // Act and assert
        assertThrows(NullPointerException.class, () -> {
            Rent rent = new RentImpl(testCourse,null);
        });
    }

    @Test
    public void constructor_should_throw_when_courseIsNull() {
        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            Rent rent = new RentImpl(null,30.0);
        });
    }
}