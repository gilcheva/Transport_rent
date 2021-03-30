package com.endava.models.parkings.impl;

import static com.endava.models.helpers.Helper.dateFormat;

import com.endava.models.enums.VehicleType;
import com.endava.models.helpers.Helper;
import com.endava.models.parkings.Parking;
import com.endava.models.parkings.ParkingTicket;
import com.endava.models.vehicles.Vehicle;
import java.time.OffsetDateTime;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ParkingTicketImpl implements ParkingTicket {
//  private final static EnumSet<ParkingType> airplaneParkings = EnumSet.of(ParkingType.PLAIN);
  public static EnumMap<VehicleType, Set<Parking>> groupParkingsByType(Set<Parking> parkings) {
    EnumMap<VehicleType, Set<Parking>> parkingByType =
        new EnumMap<VehicleType, Set<Parking>>(VehicleType.class);

    for (Parking pk : parkings) {
      VehicleType type = pk.getParkingType();
      if (parkingByType.containsKey(type)) {
        parkingByType.get(type).add(pk);
      } else {
        Set<Parking> newParkList = new HashSet<>();
        newParkList.add(pk);
        parkingByType.put(type,newParkList);
      }
    }
    return parkingByType;
  }

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
    Helper.validateNotNull(parking);
    this.parking = parking;
  }

  public OffsetDateTime updateExitTime() {
    return setExitTime(OffsetDateTime.now());
  }

  @Override
  public double calculatePrice() {
    double minuteRate = getParking().getHourlyRate() / 60;
    return minuteRate * (getExitTime().compareTo(getEntranceTime()));
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
        "Parking: " + getParking().getName() + System.lineSeparator() +
        String.format("Rate per hour: %.2f", getParking().getHourlyRate()) + System.lineSeparator()+
        "Entrance time: " + dateFormat(getEntranceTime()) + System.lineSeparator();
  }
}
