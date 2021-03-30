package com.endava.models.vehicles.impl;

import com.endava.models.enums.VehicleType;
import com.endava.models.vehicles.Ship;

public class ShipImpl extends VehicleBase implements Ship {
  private static final String VEHICLE_NAME = "Ship";
  private static final int LOAD_CAPACITY_MIN_VALUE = 20;
  private static final int LOAD_CAPACITY_MAX_VALUE = 1000;
  private static final String LOAD_CAPACITY_ERR_MESSAGE_FORMAT = "A ship cannot transport less than %.0f kg or more than %.0f kg.";

  public ShipImpl(String registrationNumber, int loadCapacity, double pricePerKm) {
    super(registrationNumber, loadCapacity, pricePerKm, VehicleType.SHIP);
  }

  @Override
  public VehicleType getType() {
    return VehicleType.SHIP;
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
