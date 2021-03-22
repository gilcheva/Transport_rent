package com.endava.models.vehicles.contracts;

import com.endava.models.vehicles.enums.VehicleType;
import java.math.BigDecimal;

public interface Vehicle {

  int getLoadCapacity();

  BigDecimal getPricePerKilometer();

  VehicleType getType();

  String getVehicleName();
}
