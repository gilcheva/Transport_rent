package com.endava.models.parkings;

import com.endava.models.enums.VehicleType;

public interface Parking {

  String getName();

  int getCapacity();

  int getFreeSpaces();

  double getHourlyRate();

  VehicleType getParkingType();

  void addVehicleToParking();

  void removeVehicleFromParking();
}
