package com.endava.models.parkings.contracts;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface ParkingThicket {

  OffsetDateTime getEntranceTime();

  OffsetDateTime getExitTime();

  String getVehicleNumber();

  Parking getParking();

  BigDecimal calculatePrice();
}
