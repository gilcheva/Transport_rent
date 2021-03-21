package com.endava.core.contracts;

import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.List;

public interface VehiclesRepository {

  List<Vehicle> getVehicles();

  List<Course> getCourses();

  List<Rent> getRents();

  void addVehicle(Vehicle vehicle);

  void addCourse(Course course);

  void addRent(Rent rent);
}
