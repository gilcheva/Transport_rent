package com.endava.models.vehicles;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ShipImplTest {

  @Test
  public void constructor_should_throw_when_loadCapacityLessThanMinValue() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new ShipImpl(19, 2);
    });
  }

  @Test
  public void constructor_should_throw_when_loadCapacityMoreThanMaxValue() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new ShipImpl(1001, 2);
    });
  }

  @Test
  public void constructor_should_throw_when_pricePerKmLessThanMinimum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new ShipImpl(20, 0);
    });
  }

  @Test
  public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new ShipImpl(20, 21);
    });
  }

}