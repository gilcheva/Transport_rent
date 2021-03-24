package com.endava.core;

import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.vehicles.contracts.Vehicle;
import com.endava.models.vehicles.enums.VehicleType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

  @Override
  public Set<Parking> getParkings() {
    return new HashSet<>(parkings);
  }

  @Override
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

  public List<Vehicle> filterByType(VehicleType vehicleType) {
    List<Vehicle> vehiclesList = new ArrayList<>(getVehicles());
    return vehiclesList.stream()
        .filter(vehicle->vehicle.getType()==vehicleType)
        .collect(Collectors.toList());
  }

  public List<Vehicle> sortedByNumber(List<Vehicle> list) {
    List<Vehicle> vehiclesList = new ArrayList<>(list);
    return vehiclesList.stream()
        .sorted((v1, v2) -> v1.getRegistrationNumber().compareToIgnoreCase(v2.getRegistrationNumber()))
        .collect(Collectors.toList());
  }

  @Override
  public List <Vehicle> sortedByLoadCapacity (){
    List<Vehicle> vehiclesList = new ArrayList<>(vehicles);
    return vehiclesList.stream()
        .sorted(Comparator.comparingInt(Vehicle::getLoadCapacity))
        .collect(Collectors.toList());
  }

  @Override
  public List<Vehicle> sortedByPricePerKm (){
    List<Vehicle> vehiclesList = new ArrayList<>(vehicles);
    return vehiclesList.stream()
        .sorted(Comparator.comparingDouble(Vehicle::getPricePerKilometer))
        .collect(Collectors.toList());
  }
}
