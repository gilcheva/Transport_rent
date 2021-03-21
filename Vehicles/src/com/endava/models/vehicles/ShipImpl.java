package com.endava.models.vehicles;

import com.endava.models.vehicles.contracts.Ship;
import com.endava.models.vehicles.enums.VehicleType;

public class ShipImpl extends VehicleBase implements Ship {
  private static final String VEHICLE_NAME = "Ship";
  private static final int LOAD_CAPACITY_MIN_VALUE = 20;
  private static final int LOAD_CAPACITY_MAX_VALUE = 1000;
  private static final String LOAD_CAPACITY_ERR_MESSAGE_FORMAT = "A ship cannot transport less than %.0f kg or more than %.0f kg.";

  public ShipImpl(int loadCapacity, double pricePerKm) {
    super(loadCapacity, pricePerKm, VehicleType.SEA);
  }

  @Override
  public VehicleType getType() {
    return VehicleType.SEA;
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
