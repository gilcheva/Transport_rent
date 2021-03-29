package com.endava.core.factories;

import com.endava.core.contracts.VehiclesFactory;
import com.endava.models.CourseImpl;
import com.endava.models.RentImpl;
import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.parkings.AirplaneParkingImpl;
import com.endava.models.parkings.BusParkingImpl;
import com.endava.models.parkings.CarParkingImpl;
import com.endava.models.parkings.ParkingTicketImpl;
import com.endava.models.parkings.TrainParkingImpl;
import com.endava.models.parkings.contracts.AirplaneParking;
import com.endava.models.parkings.contracts.BusParking;
import com.endava.models.parkings.contracts.CarParking;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.parkings.contracts.TrainParking;
import com.endava.models.vehicles.AirplaneImpl;
import com.endava.models.vehicles.CarImpl;
import com.endava.models.vehicles.ShipImpl;
import com.endava.models.vehicles.TrainImpl;
import com.endava.models.vehicles.contracts.Airplane;
import com.endava.models.vehicles.contracts.Car;
import com.endava.models.vehicles.contracts.Ship;
import com.endava.models.vehicles.contracts.Train;
import com.endava.models.vehicles.contracts.Vehicle;


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
