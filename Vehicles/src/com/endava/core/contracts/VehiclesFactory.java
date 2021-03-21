package com.endava.core.contracts;

import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.vehicles.contracts.Airplane;
import com.endava.models.vehicles.contracts.Car;
import com.endava.models.vehicles.contracts.Ship;
import com.endava.models.vehicles.contracts.Train;
import com.endava.models.vehicles.contracts.Vehicle;
import com.endava.models.vehicles.enums.VehicleType;

public interface VehiclesFactory {

  Airplane createAirplane(int loadCapacity, double pricePerKgPerKilometer,boolean charter);

  Car createCar(int loadCapacity, double pricePerKgPerKilometer, VehicleType type);

  Ship createShip(int loadCapacity, double pricePerKgPerKilometer);

  Train createTrain(int passengerCapacity, double pricePerKgPerKilometer, int carts);

  Course createCourse(String startLocation, String destination, int distance, Vehicle vehicle);

  Rent createRent(Course course, double administrativeCosts);
}
