package com.endava.models.parkings.impl;

import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.AirplaneParking;

public class AirplaneParkingImpl extends ParkingBase implements AirplaneParking {

  public AirplaneParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, VehicleType.PLANE);
  }

}
