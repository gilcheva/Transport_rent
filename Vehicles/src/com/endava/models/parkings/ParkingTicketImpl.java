package com.endava.models.parkings;

import com.endava.models.helpers.ValidationHelper;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.Objects;

public class ParkingTicketImpl implements ParkingTicket {

  private String vehicleNumber;
  private OffsetDateTime entranceTime;
  private OffsetDateTime exitTime;
  private Parking parking;

  public ParkingTicketImpl(String vehicleNumber, Parking parking) {
    setVehicleNumber(vehicleNumber);
    setEntranceTime(OffsetDateTime.now());
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

  private OffsetDateTime setExitTime(OffsetDateTime exitTime) {
    this.exitTime = exitTime;
    return exitTime;
  }

  private void setVehicleNumber(String vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
  }

  private void setParking(Parking parking) {
    ValidationHelper.validateNotNull(parking);
    this.parking = parking;
  }

  public OffsetDateTime updateExitTime() {
    return setExitTime(OffsetDateTime.now());
  }

  @Override
  public BigDecimal calculatePrice() {
    BigDecimal minuteRate = getParking().getHourlyRate()
        .divide(BigDecimal.valueOf(60),2, RoundingMode.HALF_UP);
    return minuteRate.multiply(new BigDecimal(getExitTime().compareTo(getEntranceTime())));
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
    return getVehicleNumber().equals(that.getVehicleNumber()) && getEntranceTime()
        .equals(that.getEntranceTime()) && getParking().equals(that.getParking());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getVehicleNumber(), getEntranceTime());
  }

  @Override
  public String toString() {
    return getVehicleNumber() + " ----" + System.lineSeparator() +
        "Entrance time: " + getEntranceTime().getHour()+":"+getEntranceTime().getMinute() + System.lineSeparator() +
        "Parking: " + getParking().getName() + System.lineSeparator() +
        String.format("Rate per hour: %.2f", getParking().getHourlyRate()) + System.lineSeparator();
  }
}
