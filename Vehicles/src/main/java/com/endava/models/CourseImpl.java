package com.endava.models;

import com.endava.models.contracts.Course;
import com.endava.models.helpers.Helper;
import com.endava.models.vehicles.contracts.Vehicle;

public class CourseImpl implements Course {
  private static final int LOCATION_MIN_VALUE = 5;
  private static final int LOCATION_MAX_VALUE = 25;
  private static final String START_LOCATION_ERROR_MESSAGE_FORMAT = "The StartingLocation's length cannot be less than %.0f or more than %.0f symbols long.";
  private static final String DESTINATION_ERROR_MESSAGE_FORMAT = "The Destination's length cannot be less than %.0f or more than %.0f symbols long.";
  private static final int DISTANCE_MIN_VALUE = 5;
  private static final int DISTANCE_MAX_VALUE = 5000;
  private static final String DISTANCE_ERROR_MESSAGE_FORMAT = "The Distance cannot be less than %.0f or more than %.0f kilometers.";

  private String startLocation;
  private String destination;
  private int distance;
  private Vehicle vehicle;

  @Override
  public String getStartLocation() {
    return startLocation;
  }

  public CourseImpl(String startLocation, String destination, int distance, Vehicle vehicle){
    setStartLocation(startLocation);
    setDestination(destination);
    setDistance(distance);
    setVehicle(vehicle);
  }

  public void setStartLocation(String startLocation) {
    Helper.validateNotNull(startLocation);
    Helper.validateLimits(
        startLocation.length(),
        LOCATION_MIN_VALUE,
        LOCATION_MAX_VALUE,
        START_LOCATION_ERROR_MESSAGE_FORMAT
    );
    this.startLocation = startLocation;
  }

  @Override
  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    Helper.validateNotNull(destination);
    Helper.validateLimits(
        destination.length(),
        LOCATION_MIN_VALUE,
        LOCATION_MAX_VALUE,
        DESTINATION_ERROR_MESSAGE_FORMAT
    );
    this.destination = destination;
  }

  @Override
  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    Helper.validateLimits(
        distance,
        DISTANCE_MIN_VALUE,
        DISTANCE_MAX_VALUE,
        DISTANCE_ERROR_MESSAGE_FORMAT
    );
    this.distance = distance;
  }

  @Override
  public Vehicle getVehicle() {
    return vehicle;
  }

  @Override
  public double calculateTransportCosts() {
    return getVehicle().getPricePerKilometer()*distance;
  }

  public void setVehicle(Vehicle vehicle) {
    Helper.validateNotNull(vehicle);
    this.vehicle = vehicle;
  }

  @Override
  public String toString() {
    return System.lineSeparator() +
        "Start location: " + getStartLocation() + System.lineSeparator() +
        "Destination: " + getDestination() + System.lineSeparator() +
        "Distance: " + getDistance() + System.lineSeparator() +
        "Vehicle type: " + getVehicle().getType() + System.lineSeparator() +
        String.format("Transport costs: %.2f", calculateTransportCosts()) + System.lineSeparator();
  }

}
