package com.endava.models.vehicles;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TrainImplTest {

  @Test
  public void constructor_should_throw_when_loadCapacityLessThanMinValue() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainImpl(29, 2, 3);
    });
  }

  @Test
  public void constructor_should_throw_when_loadCapacityMoreThanMaxValue() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainImpl(501, 2, 3);
    });
  }

  @Test
  public void constructor_should_throw_when_pricePerKmLessThanMinimum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainImpl(5, 0.05, 3);
    });
  }

  @Test
  public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainImpl(5, 21, 3);
    });
  }

  @Test
  public void constructor_should_throw_when_cartsLessThanMinimum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainImpl(5, 2, 0);
    });
  }

  @Test
  public void constructor_should_throw_when_cartsMoreThanMaximum() {
    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainImpl(5, 2, 16);
    });
  }

}