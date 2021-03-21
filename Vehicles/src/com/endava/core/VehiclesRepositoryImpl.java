package com.endava.core;

import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class VehiclesRepositoryImpl implements VehiclesRepository {

  private final List<Vehicle> vehicles = new ArrayList<>();
  private final List<Course> courses = new ArrayList<>();
  private final List<Rent> rents = new ArrayList<>();

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
}
