package com.endava.core.contracts;

import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.vehicles.contracts.Vehicle;
import com.endava.models.vehicles.enums.VehicleType;
import java.util.List;
import java.util.Set;

public interface VehiclesRepository {

  List<Vehicle> getVehicles();

  List<Course> getCourses();

  List<Rent> getRents();

  Set<Parking> getParkings();

  Set<ParkingTicket> getParkingTickets();

  void addVehicle(Vehicle vehicle);

  void addCourse(Course course);

  void addRent(Rent rent);

  void addParking(Parking parking);

  void addParkingTicket(ParkingTicket thicket);

  void removeParkingTicket(ParkingTicket ticket);

  Vehicle findVehicleById (int vehicleId);

  Vehicle findVehicleByNumber (String vehicleNumber);

  Course findCourseById (int courseId);

  Parking findParkingByName (String parkingName);

  ParkingTicket findTicketByVehicleNumber(String vehicleNumber);

  boolean vehicleHasParkingTicket (String vehicleNumber);

  List<Vehicle> filterByType(VehicleType vehicleType);

  List<Vehicle> sortedByNumber(List<Vehicle> list);

  List<Vehicle> sortedByLoadCapacity ();

  List<Vehicle> sortedByPricePerKm ();
}
