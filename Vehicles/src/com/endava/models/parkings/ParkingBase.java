package com.endava.models.parkings;

import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.enums.ParkingType;
import java.util.Objects;

public class ParkingBase implements Parking {

  private String name;
  private int capacity;
  private int freeSpaces;
  private double hourlyRate;
  private ParkingType parkingType;

  public ParkingBase(String name, int capacity, double hourlyRate, ParkingType type) {
    setName(name);
    setCapacity(capacity);
    setFreeSpaces(capacity);
    setHourlyRate(hourlyRate);
    setParkingType(type);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getCapacity() {
    return capacity;
  }

  @Override
  public int getFreeSpaces() {
    return freeSpaces;
  }

  @Override
  public double getHourlyRate() {
    return hourlyRate;
  }

  @Override
  public ParkingType getParkingType() {
    return parkingType;
  }

  @Override
  public void addVehicleToParking(){
    setFreeSpaces(freeSpaces--);
  }

  @Override
  public void removeVehicleFromParking(){
    setFreeSpaces(freeSpaces++);
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  private void setFreeSpaces(int freeSpaces) {
    this.freeSpaces = freeSpaces;
  }

  private void setHourlyRate(double hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  public void setParkingType(ParkingType parkingType) {
    this.parkingType = parkingType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ParkingBase)) {
      return false;
    }
    ParkingBase that = (ParkingBase) o;
    return getCapacity() == that.getCapacity() && getFreeSpaces() == that.getFreeSpaces()
        && Double.compare(that.getHourlyRate(), getHourlyRate()) == 0 && getName()
        .equals(that.getName()) && getParkingType() == that.getParkingType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public String toString() {
    return getName() + " ----" + System.lineSeparator() +
        "Capacity: " + getCapacity() + System.lineSeparator() +
        "Free parking spaces: " + getFreeSpaces() + System.lineSeparator() +
        String.format("Rate per hour: %.2f", getHourlyRate()) + System.lineSeparator();
  }
}
