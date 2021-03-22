package com.endava.models.parkings;

import com.endava.models.parkings.contracts.AirplaneParking;
import com.endava.models.parkings.contracts.CarParking;
import com.endava.models.parkings.enums.ParkingType;
import java.math.BigDecimal;

public class CarParkingImpl extends ParkingBase implements CarParking {

  public CarParkingImpl(String name, int capacity, BigDecimal hourlyRate) {
    super(name, capacity, hourlyRate, ParkingType.CAR);
  }
}
