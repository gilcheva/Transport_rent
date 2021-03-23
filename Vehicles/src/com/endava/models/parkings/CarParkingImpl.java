package com.endava.models.parkings;

import com.endava.models.parkings.contracts.CarParking;
import com.endava.models.parkings.enums.ParkingType;

public class CarParkingImpl extends ParkingBase implements CarParking {

  public CarParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, ParkingType.CAR);
  }
}
