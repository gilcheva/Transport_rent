package com.endava.models.parkings;

import com.endava.models.parkings.contracts.AirplaneParking;
import com.endava.models.parkings.contracts.BusParking;
import com.endava.models.parkings.enums.ParkingType;
import java.math.BigDecimal;

public class BusParkingImpl extends ParkingBase implements BusParking {

  public BusParkingImpl(String name, int capacity, double hourlyRate) {
    super(name, capacity, hourlyRate, ParkingType.BUS);
  }
}
