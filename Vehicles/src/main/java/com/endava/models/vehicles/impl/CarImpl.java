package com.endava.models.vehicles.impl;

import com.endava.models.enums.VehicleType;
import com.endava.models.vehicles.Car;

public class CarImpl extends VehicleBase implements Car {
  private static final String VEHICLE_NAME = "Car";
  private static final String LOAD_CAPACITY_ERR_MESSAGE_FORMAT = "A car cannot be loaded with less than %.0f kg or more than %.0f kg.";
  private static final int LOAD_CAPACITY_MAX_VALUE = 100;
  private static final int LOAD_CAPACITY_MIN_VALUE = 1;

  public CarImpl(String registrationNumber, int loadCapacity, double pricePerKilometer) {
    super(registrationNumber,loadCapacity, pricePerKilometer, VehicleType.CAR);
  }
  @Override
  protected int getLoadCapacityMaxValue() {
    return LOAD_CAPACITY_MAX_VALUE;
  }

  @Override
  protected int getLoadCapacityMinValue() {
    return LOAD_CAPACITY_MIN_VALUE;
  }

  @Override
  protected String getLoadCapacityErrorMessageFormat() {
    return LOAD_CAPACITY_ERR_MESSAGE_FORMAT;
  }
  @Override
  public String getVehicleName() {
    return VEHICLE_NAME;
  }
}
