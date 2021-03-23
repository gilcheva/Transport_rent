package com.endava.core;

import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class VehiclesRepositoryImpl implements VehiclesRepository {

  private final List<Vehicle> vehicles = new ArrayList<>();
  private final List<Course> courses = new ArrayList<>();
  private final List<Rent> rents = new ArrayList<>();
  private final Set<Parking> parkings = new HashSet<>();
  private final Set<ParkingTicket> parkingTickets = new HashSet<>();

  @Override
  public List<Vehicle> getVehicles() {
    return new ArrayList<>(vehicles);
  }

  @Override
  public List<Course> getCourses() {
    return new ArrayList<>(courses);
  }

  @Override
  public List<Rent> getRents() {
    return new ArrayList<>(rents);
  }

  public Set<Parking> getParkings() {
    return new HashSet<>(parkings);
  }

  public Set<ParkingTicket> getParkingTickets() {
    return new HashSet<>(parkingTickets);
  }

  @Override
  public void addVehicle(Vehicle vehicle) {
    this.vehicles.add(vehicle);
  }

  @Override
  public void addCourse(Course course) {
    this.courses.add(course);
  }

  @Override
  public void addRent(Rent rent) {
    this.rents.add(rent);
  }

  @Override
  public void addParking(Parking parking) {
    this.parkings.add(parking);
  }

  @Override
  public void addParkingTicket(ParkingTicket ticket) {
    this.parkingTickets.add(ticket);
  }

  @Override
  public void removeParkingTicket(ParkingTicket ticket) {
    this.parkingTickets.remove(ticket);
  }
}
