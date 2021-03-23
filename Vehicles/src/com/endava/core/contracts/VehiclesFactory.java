package com.endava.core.contracts;

import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.parkings.contracts.AirplaneParking;
import com.endava.models.parkings.contracts.BusParking;
import com.endava.models.parkings.contracts.CarParking;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.parkings.contracts.TrainParking;
import com.endava.models.vehicles.contracts.Airplane;
import com.endava.models.vehicles.contracts.Car;
import com.endava.models.vehicles.contracts.Ship;
import com.endava.models.vehicles.contracts.Train;
import com.endava.models.vehicles.contracts.Vehicle;
import com.endava.models.vehicles.enums.VehicleType;

public interface VehiclesFactory {

  Airplane createAirplane(String registrationNumber, int loadCapacity, double pricePerKgPerKilometer,boolean charter);

  Car createCar(String registrationNumber,int loadCapacity, double pricePerKgPerKilometer, VehicleType type);

  Ship createShip(String registrationNumber,int loadCapacity, double pricePerKgPerKilometer);

  Train createTrain(String registrationNumber,int passengerCapacity, double pricePerKgPerKilometer, int carts);

  Course createCourse(String startLocation, String destination, int distance, Vehicle vehicle);

  Rent createRent(Course course, double administrativeCosts);

  AirplaneParking createAirplaneParking (String name, int capacity, double hourlyRate);

  BusParking createBusParking (String name, int capacity, double hourlyRate);

  CarParking createCarParking (String name, int capacity, double hourlyRate);

  TrainParking createTrainParking (String name, int capacity, double hourlyRate);

  ParkingTicket createParkingTicket (Vehicle vehicle, Parking parking);
}
