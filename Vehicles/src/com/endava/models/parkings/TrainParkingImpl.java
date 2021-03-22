package com.endava.models.parkings;

import com.endava.models.parkings.contracts.AirplaneParking;
import com.endava.models.parkings.contracts.TrainParking;
import com.endava.models.parkings.enums.ParkingType;
import java.math.BigDecimal;

public class TrainParkingImpl extends ParkingBase implements TrainParking {

  public TrainParkingImpl(String name, int capacity, BigDecimal hourlyRate) {
    super(name, capacity, hourlyRate, ParkingType.TRAIN);
  }
}
