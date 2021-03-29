package com.endava.models.parkings;

import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.contracts.CarParking;

public class CarParkingImpl extends ParkingBase implements CarParking {

  public CarParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, VehicleType.CAR);
  }
}
