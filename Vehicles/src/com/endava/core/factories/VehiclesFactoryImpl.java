package com.endava.core.factories;

import com.endava.core.contracts.VehiclesFactory;
import com.endava.models.CourseImpl;
import com.endava.models.RentImpl;
import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.vehicles.AirplaneImpl;
import com.endava.models.vehicles.CarImpl;
import com.endava.models.vehicles.ShipImpl;
import com.endava.models.vehicles.TrainImpl;
import com.endava.models.vehicles.contracts.Airplane;
import com.endava.models.vehicles.contracts.Car;
import com.endava.models.vehicles.contracts.Ship;
import com.endava.models.vehicles.contracts.Train;
import com.endava.models.vehicles.contracts.Vehicle;
import com.endava.models.vehicles.enums.VehicleType;

public class VehiclesFactoryImpl implements VehiclesFactory {

  @Override
  public Airplane createAirplane(int loadCapacity, double pricePerKgPerKilometer, boolean charter) {
    return new AirplaneImpl(loadCapacity, pricePerKgPerKilometer, charter);
  }

  @Override
  public Car createCar(int loadCapacity, double pricePerKgPerKilometer, VehicleType type) {
    return new CarImpl(loadCapacity,pricePerKgPerKilometer,type);
  }

  @Override
  public Ship createShip(int loadCapacity, double pricePerKgPerKilometer) {
    return new ShipImpl(loadCapacity, pricePerKgPerKilometer);
  }

  @Override
  public Train createTrain(int passengerCapacity, double pricePerKgPerKilometer, int carts) {
    return new TrainImpl(passengerCapacity, pricePerKgPerKilometer, carts);
  }

  @Override
  public Course createCourse(String startLocation, String destination, int distance,
      Vehicle vehicle) {
    return new CourseImpl(startLocation, destination, distance, vehicle);
  }

  @Override
  public Rent createRent(Course course, double administrativeCosts) {
    return new RentImpl(course, administrativeCosts);
  }
}
