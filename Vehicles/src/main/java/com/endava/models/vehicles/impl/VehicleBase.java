package com.endava.models.vehicles.impl;

import com.endava.models.enums.VehicleType;
import com.endava.models.helpers.Helper;
import com.endava.models.vehicles.Vehicle;

public abstract class VehicleBase implements Vehicle {
  private static final String LOAD_CAPACITY_ERR_MESSAGE_FORMAT = "A vehicle with less than %.0f kg or more than %.0f kg cannot exist!";
  private static final int LOAD_CAPACITY_MAX_VALUE = 800;
  private static final int LOAD_CAPACITY_MIN_VALUE = 1;
  private static final String PRICE_ERR_MESSAGE_FORMAT = "A vehicle with a price per km lower than $%.2f or higher than $%.2f cannot exist!";
  private static final double PRICE_MAX_VALUE = 20;
  private static final double PRICE_MIN_VALUE = 0.10;

  private String registrationNumber;
  private int loadCapacity;
  private double pricePerKilometer;
  private final VehicleType type;

  public VehicleBase(String registrationNumber, int loadCapacity, double pricePerKilometer,
      VehicleType type) {
    setRegistrationNumber(registrationNumber);
    setLoadCapacity(loadCapacity);
    setPricePerKilometer(pricePerKilometer);
    this.type = type;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  @Override
  public int getLoadCapacity() {
    return loadCapacity;
  }

  @Override
  public double getPricePerKilometer() {
    return pricePerKilometer;
  }

  @Override
  public VehicleType getType() {
    return type;
  }

  protected int getLoadCapacityMaxValue() {
    return LOAD_CAPACITY_MAX_VALUE;
  }

  protected int getLoadCapacityMinValue() {
    return LOAD_CAPACITY_MIN_VALUE;
  }

  protected String getLoadCapacityErrorMessageFormat() {
    return LOAD_CAPACITY_ERR_MESSAGE_FORMAT;
  }

  private void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  private void setLoadCapacity(int loadCapacity) {
    Helper.validateLimits(loadCapacity,
        getLoadCapacityMinValue(),
        getLoadCapacityMaxValue(),
        getLoadCapacityErrorMessageFormat());
    this.loadCapacity = loadCapacity;
  }

  private void setPricePerKilometer(double pricePerKilometer) {
    Helper.validateLimits(pricePerKilometer,
        PRICE_MIN_VALUE,
        PRICE_MAX_VALUE,
        PRICE_ERR_MESSAGE_FORMAT);
    this.pricePerKilometer = pricePerKilometer;
  }

  public abstract String getVehicleName();

  @Override
  public String toString() {
    return getVehicleName() + " ----" + System.lineSeparator() +
        "Registration number: " + registrationNumber + System.lineSeparator() +
        "Load capacity: " + loadCapacity + System.lineSeparator() +
        String.format("Price per kilometer: %.2f", pricePerKilometer) + System.lineSeparator() +
        "Vehicle type: " + type + System.lineSeparator();
  }
}
