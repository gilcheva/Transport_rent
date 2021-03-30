package com.endava.core.factories;

import com.endava.core.VehiclesFactory;
import com.endava.models.courses.impl.CourseImpl;
import com.endava.models.courses.impl.RentImpl;
import com.endava.models.courses.Course;
import com.endava.models.courses.Rent;
import com.endava.models.parkings.impl.AirplaneParkingImpl;
import com.endava.models.parkings.impl.BusParkingImpl;
import com.endava.models.parkings.impl.CarParkingImpl;
import com.endava.models.parkings.impl.ParkingTicketImpl;
import com.endava.models.parkings.impl.TrainParkingImpl;
import com.endava.models.parkings.AirplaneParking;
import com.endava.models.parkings.BusParking;
import com.endava.models.parkings.CarParking;
import com.endava.models.parkings.Parking;
import com.endava.models.parkings.ParkingTicket;
import com.endava.models.parkings.TrainParking;
import com.endava.models.vehicles.impl.AirplaneImpl;
import com.endava.models.vehicles.impl.CarImpl;
import com.endava.models.vehicles.impl.ShipImpl;
import com.endava.models.vehicles.impl.TrainImpl;
import com.endava.models.vehicles.Airplane;
import com.endava.models.vehicles.Car;
import com.endava.models.vehicles.Ship;
import com.endava.models.vehicles.Train;
import com.endava.models.vehicles.Vehicle;


public class VehiclesFactoryImpl implements VehiclesFactory {

  @Override
  public Airplane createAirplane(String registrationNumber, int loadCapacity, double pricePerKgPerKilometer, boolean charter) {
    return new AirplaneImpl(registrationNumber, loadCapacity, pricePerKgPerKilometer, charter);
  }

  @Override
  public Car createCar(String registrationNumber, int loadCapacity, double pricePerKgPerKilometer) {
    return new CarImpl(registrationNumber, loadCapacity,pricePerKgPerKilometer);
  }

  @Override
  public Ship createShip(String registrationNumber, int loadCapacity, double pricePerKgPerKilometer) {
    return new ShipImpl(registrationNumber, loadCapacity, pricePerKgPerKilometer);
  }

  @Override
  public Train createTrain(String registrationNumber, int passengerCapacity, double pricePerKgPerKilometer, int carts) {
    return new TrainImpl(registrationNumber, passengerCapacity, pricePerKgPerKilometer, carts);
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

  @Override
  public AirplaneParking createAirplaneParking (String name, int capacity, double hourlyRate) {
    return new AirplaneParkingImpl(name, capacity, hourlyRate);
  }

  @Override
  public BusParking createBusParking (String name, int capacity, double hourlyRate) {
    return new BusParkingImpl(name, capacity, hourlyRate);
  }

  @Override
  public CarParking createCarParking (String name, int capacity, double hourlyRate) {
    return new CarParkingImpl(name, capacity, hourlyRate);
  }

  @Override
  public TrainParking createTrainParking (String name, int capacity, double hourlyRate) {
    return new TrainParkingImpl(name, capacity, hourlyRate);
  }

  @Override
  public ParkingTicket createParkingTicket (Vehicle vehicle, Parking parking) {
    return new ParkingTicketImpl(vehicle, parking);
  }
}
