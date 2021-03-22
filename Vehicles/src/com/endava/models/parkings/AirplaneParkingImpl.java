package com.endava.models.parkings;

import com.endava.models.parkings.contracts.AirplaneParking;
import com.endava.models.parkings.enums.ParkingType;
import java.math.BigDecimal;

public class AirplaneParkingImpl extends ParkingBase implements AirplaneParking {

  public AirplaneParkingImpl(String name, int capacity, BigDecimal hourlyRate) {
    super(name, capacity, hourlyRate, ParkingType.PLAIN);
  }
}
