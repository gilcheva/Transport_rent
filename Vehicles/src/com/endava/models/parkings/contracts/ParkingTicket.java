package com.endava.models.parkings.contracts;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface ParkingTicket {

  OffsetDateTime getEntranceTime();

  OffsetDateTime getExitTime();

  String getVehicleNumber();

  Parking getParking();

  OffsetDateTime updateExitTime();

  BigDecimal calculatePrice();
}
