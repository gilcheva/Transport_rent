package com.endava.core.contracts;

import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.List;
import java.util.Map;
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
}
