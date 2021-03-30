package com.endava.models.parkings.impl;

import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.CarParking;

public class CarParkingImpl extends ParkingBase implements CarParking {

  public CarParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, VehicleType.CAR);
  }
}
