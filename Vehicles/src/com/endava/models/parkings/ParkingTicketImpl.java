package com.endava.models.parkings;

import com.endava.models.helpers.ValidationHelper;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.vehicles.contracts.Vehicle;
import java.time.OffsetDateTime;
import java.util.Objects;

public class ParkingTicketImpl implements ParkingTicket {

  private Vehicle vehicle;
  private OffsetDateTime entranceTime;
  private OffsetDateTime exitTime;
  private Parking parking;

  public ParkingTicketImpl(Vehicle vehicle, Parking parking) {
    setVehicle(vehicle);
    setEntranceTime(OffsetDateTime.now());
    setExitTime(entranceTime);
    setParking(parking);
  }

  @Override
  public Vehicle getVehicle() {
    return vehicle;
  }

  @Override
  public OffsetDateTime getEntranceTime() {
    return entranceTime;
  }

  @Override
  public OffsetDateTime getExitTime() {
    return exitTime;
  }

  @Override
  public Parking getParking() {
    return parking;
  }

  private void setEntranceTime(OffsetDateTime entranceTime) {
    this.entranceTime = entranceTime;
  }

  private OffsetDateTime setExitTime(OffsetDateTime exitTime) {
    this.exitTime = exitTime;
    return exitTime;
  }

  private void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  private void setParking(Parking parking) {
    ValidationHelper.validateNotNull(parking);
    this.parking = parking;
  }

  public OffsetDateTime updateExitTime() {
    return setExitTime(OffsetDateTime.now());
  }

  @Override
  public double calculatePrice() {
    double minuteRate = getParking().getHourlyRate()/60;
    return minuteRate*(getExitTime().compareTo(getEntranceTime()));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ParkingTicketImpl)) {
      return false;
    }
    ParkingTicketImpl that = (ParkingTicketImpl) o;
    return getVehicle().equals(that.getVehicle()) && getEntranceTime()
        .equals(that.getEntranceTime()) && getParking().equals(that.getParking());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getVehicle().getRegistrationNumber(), getEntranceTime());
  }

  @Override
  public String toString() {
    return getVehicle().getRegistrationNumber() + " ----" + System.lineSeparator() +
        "Entrance time: " + getEntranceTime().getHour()+":"+getEntranceTime().getMinute() + System.lineSeparator() +
        "Parking: " + getParking().getName() + System.lineSeparator() +
        String.format("Rate per hour: %.2f", getParking().getHourlyRate()) + System.lineSeparator();
  }
}
