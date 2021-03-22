package com.endava.models.parkings.enums;

public enum ParkingType {
  CAR,
  BUS,
  PLAIN,
  TRAIN;

  @Override
  public String toString() {
    switch (this) {
      case CAR:
        return "Car";
      case BUS:
        return "Bus";
      case PLAIN:
        return "Airplane";
      case TRAIN:
        return "Train";
      default:
        throw new IllegalArgumentException();
    }
  }

}
