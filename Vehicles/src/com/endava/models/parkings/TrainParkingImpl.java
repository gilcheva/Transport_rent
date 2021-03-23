package com.endava.models.parkings;

import com.endava.models.parkings.contracts.TrainParking;
import com.endava.models.parkings.enums.ParkingType;

public class TrainParkingImpl extends ParkingBase implements TrainParking {

  public TrainParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, ParkingType.TRAIN);
  }
}
