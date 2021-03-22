package com.endava.models.contracts;

import com.endava.models.vehicles.contracts.Vehicle;
import java.math.BigDecimal;

public interface Course {

  String getDestination();

  int getDistance();

  String getStartLocation();

  Vehicle getVehicle();

  BigDecimal calculateTransportCosts();

}
