package com.endava.models.parkings;

import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.contracts.BusParking;

public class BusParkingImpl extends ParkingBase implements BusParking {

  public BusParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, VehicleType.BUS);
  }
}
