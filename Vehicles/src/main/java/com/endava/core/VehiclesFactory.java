package com.endava.core;

import com.endava.models.courses.Course;
import com.endava.models.courses.Rent;
import com.endava.models.parkings.AirplaneParking;
import com.endava.models.parkings.BusParking;
import com.endava.models.parkings.CarParking;
import com.endava.models.parkings.Parking;
import com.endava.models.parkings.ParkingTicket;
import com.endava.models.parkings.TrainParking;
import com.endava.models.vehicles.Airplane;
import com.endava.models.vehicles.Car;
import com.endava.models.vehicles.Ship;
import com.endava.models.vehicles.Train;
import com.endava.models.vehicles.Vehicle;

public interface VehiclesFactory {

  Airplane createAirplane(String registrationNumber, int loadCapacity, double pricePerKgPerKilometer,boolean charter);

  Car createCar(String registrationNumber,int loadCapacity, double pricePerKgPerKilometer);

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
