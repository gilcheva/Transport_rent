package com.endava.models.parkings.impl;

import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.TrainParking;

public class TrainParkingImpl extends ParkingBase implements TrainParking {

  private int[] parkingSpaces;

  public TrainParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, VehicleType.TRAIN);
    parkingSpaces = new int[capacity];
  }
}
