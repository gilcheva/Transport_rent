package com.endava.models.vehicles.impl;

import com.endava.models.enums.VehicleType;
import com.endava.models.vehicles.Airplane;

public class AirplaneImpl extends VehicleBase implements Airplane {
  private static final String VEHICLE_NAME = "Airplane";

  private boolean charter;

  public AirplaneImpl(String registrationNumber, int loadCapacity, double pricePerKilometer,boolean charter) {
    super(registrationNumber,loadCapacity, pricePerKilometer, VehicleType.PLANE);
    setCharter(charter);
  }

  @Override
  public boolean isCharter() {
    return this.charter;
  }

  private void setCharter(boolean isCharter) {
    this.charter = isCharter;
  }

  @Override
  public String getVehicleName() {
    return VEHICLE_NAME;
  }

  @Override
  public String toString() {
    return super.toString() +
        "Is charter: " + charter + System.lineSeparator();
  }

}
