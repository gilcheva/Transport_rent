package com.endava.models.parkings;

import com.endava.models.parkings.contracts.AirplaneParking;
import com.endava.models.parkings.enums.ParkingType;

public class AirplaneParkingImpl extends ParkingBase implements AirplaneParking {

  public AirplaneParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, ParkingType.PLAIN);
  }
}
