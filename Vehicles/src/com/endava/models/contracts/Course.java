package com.endava.models.contracts;

import com.endava.models.vehicles.contracts.Vehicle;

public interface Course {

  String getDestination();

  int getDistance();

  String getStartLocation();

  Vehicle getVehicle();

  double calculateTransportCosts();

}
