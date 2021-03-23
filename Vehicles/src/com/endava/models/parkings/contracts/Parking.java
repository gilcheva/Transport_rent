package com.endava.models.parkings.contracts;

import com.endava.models.parkings.enums.ParkingType;

public interface Parking {

  String getName();

  int getCapacity();

  int getFreeSpaces();

  double getHourlyRate();

  ParkingType getParkingType();

  void addVehicleToParking();

  void removeVehicleFromParking();
}
