package com.endava.models.vehicles;

import static org.junit.jupiter.api.Assertions.*;

import com.endava.models.vehicles.enums.VehicleType;
import org.junit.jupiter.api.Test;

class CarImplTest {

  @Test
  public void constructor_should_throw_when_loadCapacityLessThanMinValue() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new CarImpl("С7320В", 0, 2, VehicleType.LAND);
    });
  }

  @Test
  public void constructor_should_throw_when_loadCapacityMoreThanMaxValue() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new CarImpl("С7320В", 101, 2, VehicleType.LAND);
    });
  }

  @Test
  public void constructor_should_throw_when_pricePerKgPerKmLessThanMinimum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new CarImpl("С7320В", 5, 0.09, VehicleType.LAND);
    });
  }

  @Test
  public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      new CarImpl("С7320В", 5, 21, VehicleType.LAND);
    });
  }
}