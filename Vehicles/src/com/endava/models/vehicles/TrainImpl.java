package com.endava.models.vehicles;

import com.endava.models.helpers.ValidationHelper;
import com.endava.models.vehicles.contracts.Train;
import com.endava.models.vehicles.enums.VehicleType;

public class TrainImpl extends VehicleBase implements Train {
  private static final String VEHICLE_NAME = "Train";
  private static final int LOAD_CAPACITY_MIN_VALUE = 30;
  private static final int LOAD_CAPACITY_MAX_VALUE = 500;
  private static final String LOAD_CAPACITY_ERR_MESSAGE_FORMAT = "A train cannot transport less than %.0f kg or more than %.0f kg.";
  private static final int CARTS_MIN_VALUE = 1;
  private static final int CARTS_MAX_VALUE = 15;
  private static final String CARTS_ERROR_MESSAGE_FORMAT = "A train cannot have less than %.0f cart or more than %.0f carts.";

  private int carts;

  public TrainImpl(int loadCapacity, double pricePerKilometer, int carts) {
    super(loadCapacity, pricePerKilometer, VehicleType.LAND);
    setCarts(carts);
  }

  @Override
  public int getCarts() {
    return carts;
  }

  @Override
  public VehicleType getType() {
    return VehicleType.LAND;
  }

  private void setCarts(int carts) {
    ValidationHelper
        .validateLimits(carts, CARTS_MIN_VALUE, CARTS_MAX_VALUE, CARTS_ERROR_MESSAGE_FORMAT);
    this.carts = carts;
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

  @Override
  public String toString() {
    return super.toString() +
        "Carts amount: " + getCarts() + System.lineSeparator();
  }

}
