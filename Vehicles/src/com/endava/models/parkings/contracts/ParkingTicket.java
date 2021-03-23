package com.endava.models.parkings.contracts;

import java.time.OffsetDateTime;

public interface ParkingTicket {

  OffsetDateTime getEntranceTime();

  OffsetDateTime getExitTime();

  String getVehicleNumber();

  Parking getParking();

  OffsetDateTime updateExitTime();

  double calculatePrice();
}
