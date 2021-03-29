package com.endava.core;

import static com.endava.commands.Constants.PARKING_NOT_EXIST_MESSAGE;
import static com.endava.commands.Constants.PARKING_NO_SPACES_MESSAGE;
import static com.endava.commands.Constants.PARKING_TICKET_NOT_EXIST_MESSAGE;
import static com.endava.commands.Constants.VEHICLE_NOT_EXIST_MESSAGE;

import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.vehicles.contracts.Airplane;
import com.endava.models.vehicles.contracts.Train;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class VehiclesRepositoryImpl {

//  public final static EnumMap<ParkingType, Parking> allParkings = new EnumMap<>(ParkingType.class);

  private static EnumMap<VehicleType, List<Parking>>
  groupParkingsByType(List<Parking> parkings) {
    EnumMap<VehicleType, List<Parking>> parkingByType =
        new EnumMap<VehicleType, List<Parking>>(VehicleType.class);

    for (Parking pk : parkings) {
      VehicleType type = pk.getParkingType();
      if (parkingByType.containsKey(type)) {
        parkingByType.get(type).add(pk);
      } else {
        List<Parking> newParkList = new ArrayList<Parking>();
        newParkList.add(pk);
        parkingByType.put(type, newParkList);
      }
    }
    return parkingByType;
  }

  private final List<Vehicle> vehicles = new ArrayList<>();
  private final List<Course> courses = new ArrayList<>();
  private final List<Rent> rents = new ArrayList<>();
  private final Set<Parking> parkings = new HashSet<>();
  private final Set<ParkingTicket> parkingTickets = new HashSet<>();

//  public static List<Parking> filterParkingByType (ParkingType type) {
//    allParkings
//  }


//  @Override
  public List<Vehicle> getVehicles() {
    return new ArrayList<>(vehicles);
  }

//  @Override
  public List<Course> getCourses() {
    return new ArrayList<>(courses);
  }

//  @Override
  public List<Rent> getRents() {
    return new ArrayList<>(rents);
  }

//  @Override
  public Set<Parking> getParkings() {
    return new HashSet<>(parkings);
  }

//  @Override
  public Set<ParkingTicket> getParkingTickets() {
    return new HashSet<>(parkingTickets);
  }

//  @Override
  public void addVehicle(Vehicle vehicle) {
    this.vehicles.add(vehicle);
  }

//  @Override
  public void addPlane(Airplane vehicle) {
    this.vehicles.add(vehicle);
  }

//  @Override
  public void addTrain(Train vehicle) {
    this.vehicles.add(vehicle);
  }

//  @Override
  public void addCourse(Course course) {
    this.courses.add(course);
  }

//  @Override
  public void addRent(Rent rent) {
    this.rents.add(rent);
  }

//  @Override
  public void addParking(Parking parking) {
    this.parkings.add(parking);
  }

//  @Override
  public void addParkingTicket(ParkingTicket ticket) {
    this.parkingTickets.add(ticket);
  }

//  @Override
  public void removeParkingTicket(ParkingTicket ticket) {
    this.parkingTickets.remove(ticket);
  }

//  @Override
  public Vehicle findVehicleById (int vehicleId) {
    return vehicles.get(vehicleId);
  }

//  @Override
  public Boolean vehicleExists(String vehicleNumber) {
    return vehicles.stream()
            .anyMatch(veh -> vehicleNumber.equals(veh.getRegistrationNumber()));
  }

//  @Override
  public Vehicle findVehicleByNumber (String vehicleNumber) {
    return vehicles.stream()
        .filter(veh -> vehicleNumber.equals(veh.getRegistrationNumber()))
        .findAny()
        .orElseThrow(() ->
            new NoSuchElementException(String.format(VEHICLE_NOT_EXIST_MESSAGE, vehicleNumber)));
  }

//  @Override
  public Course findCourseById (int courseId) {
    return courses.get(courseId);
  }

//  @Override
  public Parking findParkingByName (String parkingName) {
    Parking parking = parkings.stream()
        .filter(park -> parkingName.equals(park.getName()))
        .findAny()
        .orElseThrow(() ->
            new NoSuchElementException(String.format(PARKING_NOT_EXIST_MESSAGE, parkingName)));
    if (parking.getFreeSpaces() - 1 == 0) {
      throw new IllegalArgumentException(PARKING_NO_SPACES_MESSAGE);
    }
    return parking;
  }

//  @Override
  public ParkingTicket findTicketByVehicleNumber(String vehicleNumber) {
    return parkingTickets.stream()
        .filter(tick -> vehicleNumber.equals(tick.getVehicle().getRegistrationNumber()))
        .findAny()
        .orElseThrow(() ->
            new NoSuchElementException(
                String.format(PARKING_TICKET_NOT_EXIST_MESSAGE, vehicleNumber)));
  }

//  @Override
  public boolean vehicleHasParkingTicket (String vehicleNumber){
    return parkingTickets.stream()
        .anyMatch(tick -> tick.getVehicle().getRegistrationNumber().equals(vehicleNumber));
  }

//  @Override
  public List<Vehicle> filterByType(VehicleType vehicleType) {
    List<Vehicle> vehiclesList = new ArrayList<>(getVehicles());
    return vehiclesList.stream()
        .filter(vehicle -> vehicle.getType() == vehicleType)
        .collect(Collectors.toList());
  }

//  @Override
  public List<Vehicle> sortedByNumber(List<Vehicle> list) {
    List<Vehicle> vehiclesList = new ArrayList<>(list);
    return vehiclesList.stream()
        .sorted(
            (v1, v2) -> v1.getRegistrationNumber().compareToIgnoreCase(v2.getRegistrationNumber()))
        .collect(Collectors.toList());
  }

//  @Override
  public List<Vehicle> sortedByLoadCapacity() {
    List<Vehicle> vehiclesList = new ArrayList<>(vehicles);
    return vehiclesList.stream()
        .sorted(Comparator.comparingInt(Vehicle::getLoadCapacity))
        .collect(Collectors.toList());
  }

//  @Override
  public List<Vehicle> sortedByPricePerKm() {
    List<Vehicle> vehiclesList = new ArrayList<>(vehicles);
    return vehiclesList.stream()
        .sorted(Comparator.comparingDouble(Vehicle::getPricePerKilometer))
        .collect(Collectors.toList());
  }
}
