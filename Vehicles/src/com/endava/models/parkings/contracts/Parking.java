package com.endava.models.parkings.contracts;

import com.endava.models.parkings.enums.ParkingType;
import java.math.BigDecimal;

public interface Parking {

  String getName();

  int getCapacity();

  int getFreeSpaces();

  BigDecimal getHourlyRate();

  ParkingType getParkingType();

  void addVehicleToParking();

  void removeVehicleFromParking();
}
