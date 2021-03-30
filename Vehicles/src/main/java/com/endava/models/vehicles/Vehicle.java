package com.endava.models.vehicles;

import com.endava.models.enums.VehicleType;

public interface Vehicle {

  String getRegistrationNumber();

  int getLoadCapacity();

  double getPricePerKilometer();

  VehicleType getType();
}
