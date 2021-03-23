package com.endava.commands.creation;

import static com.endava.commands.Constants.INVALID_NUMBER_OF_ARGUMENTS;
import static com.endava.commands.Constants.PARKING_NO_SPACES_MESSAGE;
import static com.endava.commands.Constants.PARKING_NOT_EXIST_MESSAGE;
import static com.endava.commands.Constants.PARKING_TICKET_CREATED_MESSAGE;
import static com.endava.commands.Constants.PARKING_TICKET_EXISTS_MESSAGE;
import static com.endava.commands.Constants.VEHICLE_NOT_EXIST_MESSAGE;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.List;
import java.util.NoSuchElementException;

public class CreateParkingTicketCommand implements Command {

  private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

  private final VehiclesFactory factory;
  private final VehiclesRepository repository;
  private String vehicleNumber;
  private String parkingName;

  public CreateParkingTicketCommand(VehiclesFactory factory, VehiclesRepository repository) {
    this.factory = factory;
    this.repository = repository;
  }

  @Override
  public String execute(List<String> parameters) {
    validateInput(parameters);
    parseParameters(parameters);

    Parking parking = repository.getParkings().stream()
        .filter(park -> parkingName.equals(park.getName()))
        .findAny()
        .orElseThrow(() ->
            new NoSuchElementException(String.format(PARKING_NOT_EXIST_MESSAGE, vehicleNumber)));
    if (parking.getFreeSpaces() - 1 == 0) {
      throw new IllegalArgumentException(PARKING_NO_SPACES_MESSAGE);
    }

    Vehicle vehicle = repository.getVehicles().stream()
        .filter(veh -> vehicleNumber.equals(veh.getRegistrationNumber()))
        .findAny()
        .orElseThrow(() ->
            new NoSuchElementException(String.format(VEHICLE_NOT_EXIST_MESSAGE, vehicleNumber)));
    parking.addVehicleToParking();

    if (repository.getParkingTickets().stream()
        .anyMatch(tick -> tick.getVehicle().getRegistrationNumber().equals(vehicleNumber))) {
      throw new IllegalArgumentException(
          String.format(PARKING_TICKET_EXISTS_MESSAGE,vehicleNumber));
    }

    ParkingTicket parkingTicket = factory.createParkingTicket(vehicle, parking);
    repository.addParkingTicket(parkingTicket);
    return String.format(PARKING_TICKET_CREATED_MESSAGE, vehicleNumber);
  }

  private void validateInput(List<String> parameters) {
    if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
      throw new IllegalArgumentException(String
          .format(INVALID_NUMBER_OF_ARGUMENTS, EXPECTED_NUMBER_OF_ARGUMENTS, parameters.size()));
    }
  }

  private void parseParameters(List<String> parameters) {
    try {
      vehicleNumber = parameters.get(0);
      parkingName = parameters.get(1);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Failed to parse CreateParkingTicket command parameters.");
    }
  }

}
