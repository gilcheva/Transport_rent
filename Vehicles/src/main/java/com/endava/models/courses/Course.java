package com.endava.models.courses;

import com.endava.models.vehicles.Vehicle;

public interface Course {

  String getDestination();

  int getDistance();

  String getStartLocation();

  Vehicle getVehicle();

  double calculateTransportCosts();

}
