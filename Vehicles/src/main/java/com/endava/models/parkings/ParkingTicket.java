package com.endava.models.parkings;

import com.endava.models.vehicles.Vehicle;
import java.time.OffsetDateTime;

public interface ParkingTicket {

  Vehicle getVehicle();

  OffsetDateTime getEntranceTime();

  OffsetDateTime getExitTime();

  Parking getParking();

  OffsetDateTime updateExitTime();

  double calculatePrice();
}
