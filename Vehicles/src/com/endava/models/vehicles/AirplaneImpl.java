package com.endava.models.vehicles;

import com.endava.models.vehicles.contracts.Airplane;
import com.endava.models.vehicles.enums.VehicleType;

public class AirplaneImpl extends VehicleBase implements Airplane {
  private static final String VEHICLE_NAME = "Airplane";

  private boolean charter;

  public AirplaneImpl(int loadCapacity, double pricePerKilometer,boolean charter) {
    super(loadCapacity, pricePerKilometer, VehicleType.AIR);
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
