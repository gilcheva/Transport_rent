package com.endava.core;

import com.endava.models.courses.Course;
import com.endava.models.courses.Rent;
import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.Parking;
import com.endava.models.parkings.ParkingTicket;
import com.endava.models.vehicles.Airplane;
import com.endava.models.vehicles.Train;
import com.endava.models.vehicles.Vehicle;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface VehiclesRepository {

  List<Vehicle> getVehicles();

  List<Course> getCourses();

  List<Rent> getRents();

  Set<Parking> getParkings();

  Set<ParkingTicket> getParkingTickets();

  void addVehicle(Vehicle vehicle);
  void addPlane(Airplane vehicle);
  void addTrain(Train vehicle);

  void addCourse(Course course);

  void addRent(Rent rent);

  void addParking(Parking parking);

  void addParkingTicket(ParkingTicket thicket);

  void removeParkingTicket(ParkingTicket ticket);

  Vehicle findVehicleById (int vehicleId) throws SQLException;

  Boolean vehicleExists(String vehicleNumber);

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
