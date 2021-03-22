package com.endava.models.parkings;

import com.endava.models.helpers.ValidationHelper;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingThicket;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;

public class ParkingThicketImpl implements ParkingThicket {

  private String vehicleNumber;
  private OffsetDateTime entranceTime;
  private OffsetDateTime exitTime;
  private Parking parking;

  public ParkingThicketImpl(String vehicleNumber, OffsetDateTime entranceTime, Parking parking) {
    setVehicleNumber(vehicleNumber);
    setEntranceTime(entranceTime);
    setExitTime(entranceTime);
    setVehicleNumber(vehicleNumber);
    setParking(parking);
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
  public String getVehicleNumber() {
    return vehicleNumber;
  }

  @Override
  public Parking getParking() {
    return parking;
  }

  private void setEntranceTime(OffsetDateTime entranceTime) {
    this.entranceTime = entranceTime;
  }

  private void setExitTime(OffsetDateTime exitTime) {
    this.exitTime = exitTime;
  }

  private void setVehicleNumber(String vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
  }

  private void setParking(Parking parking) {
    ValidationHelper.validateNotNull(parking);
    this.parking = parking;
  }

  @Override
  public BigDecimal calculatePrice() {
    return getParking().getHourlyRate()
        .multiply(new BigDecimal(getEntranceTime().compareTo(getExitTime())));
  }

  @Override
  public String toString() {
    return getVehicleNumber() + " ----" + System.lineSeparator() +
        "Entrance time: " + getEntranceTime() + System.lineSeparator() +
        "Exit time: " + getExitTime() + System.lineSeparator() +
        "Parking: " + getParking().getName() + System.lineSeparator() +
        String.format("Rate per hour: %.2f", getParking().getHourlyRate()) + System.lineSeparator()
        +
        String.format("Sum to pay: %.2f", calculatePrice()) + System.lineSeparator();
  }
}
