package com.endava.models.vehicles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AirplaneImplTest {

  @Test
  public void constructor_should_throw_when_loadCapacityLessThanMinValue() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new AirplaneImpl("С7320В", 0, 2, true);
    });
  }

  @Test
  public void constructor_should_throw_when_loadCapacityMoreThanMaxValue() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new AirplaneImpl("С7320В", 801, 2, true);
    });
  }

  @Test
  public void constructor_should_throw_when_pricePerKgPerKmLessThanMinimum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new AirplaneImpl("С7320В", 5, 0.09, true);
    });
  }

  @Test
  public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new AirplaneImpl("С7320В", 5, 21, true);
    });
  }

}