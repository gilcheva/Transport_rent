package com.endava.models.parkings.impl;

import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.BusParking;

public class BusParkingImpl extends ParkingBase implements BusParking {

  public BusParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, VehicleType.BUS);
  }
}
