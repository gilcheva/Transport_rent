package com.endava.models.vehicles.contracts;

import com.endava.models.vehicles.enums.VehicleType;

public interface Vehicle {

  String getRegistrationNumber();

  int getLoadCapacity();

  double getPricePerKilometer();

  VehicleType getType();
}
