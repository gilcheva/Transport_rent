package com.endava.models.parkings.contracts;

import com.endava.models.vehicles.contracts.Vehicle;
import java.time.OffsetDateTime;

public interface ParkingTicket {

  Vehicle getVehicle();

  OffsetDateTime getEntranceTime();

  OffsetDateTime getExitTime();

  Parking getParking();

  OffsetDateTime updateExitTime();

  double calculatePrice();
}
