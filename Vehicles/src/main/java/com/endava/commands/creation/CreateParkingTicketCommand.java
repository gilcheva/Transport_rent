package com.endava.commands.creation;

import static com.endava.commands.constants.Constants.FAILED_TO_PARSE_COMMAND_MESSAGE;
import static com.endava.commands.constants.Constants.INVALID_NUMBER_OF_ARGUMENTS;
import static com.endava.commands.constants.Constants.PARKING_TICKET_CREATED_MESSAGE;
import static com.endava.commands.constants.Constants.PARKING_TICKET_EXISTS_MESSAGE;
import static com.endava.models.helpers.Helper.dateFormat;

import com.endava.commands.Command;
import com.endava.core.VehiclesFactory;
import com.endava.core.VehiclesRepository;
import com.endava.models.parkings.Parking;
import com.endava.models.parkings.ParkingTicket;
import com.endava.models.vehicles.Vehicle;
import java.util.List;

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
    Parking parking = repository.findParkingByName(parkingName);
    Vehicle vehicle = repository.findVehicleByNumber(vehicleNumber);
    parking.addVehicleToParking();

    if (repository.vehicleHasParkingTicket(vehicleNumber)) {
      throw new IllegalArgumentException(
          String.format(PARKING_TICKET_EXISTS_MESSAGE, vehicleNumber));
    }

    ParkingTicket parkingTicket = factory.createParkingTicket(vehicle, parking);
    repository.addParkingTicket(parkingTicket);
    return String
        .format(PARKING_TICKET_CREATED_MESSAGE,
            vehicleNumber,
            dateFormat(parkingTicket.getEntranceTime()));
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
          String.format(FAILED_TO_PARSE_COMMAND_MESSAGE, getClass().getSimpleName()));
    }
  }

}
