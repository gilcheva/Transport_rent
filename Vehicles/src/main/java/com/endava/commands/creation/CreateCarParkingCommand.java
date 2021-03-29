package com.endava.commands.creation;

import static com.endava.commands.Constants.FAILED_TO_PARSE_COMMAND_MESSAGE;
import static com.endava.commands.Constants.INVALID_NUMBER_OF_ARGUMENTS;
import static com.endava.commands.Constants.PARKING_CREATED_MESSAGE;
import static com.endava.commands.Constants.PARKING_EXISTS_MESSAGE;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.parkings.contracts.Parking;
import java.math.BigDecimal;
import java.util.List;

public class CreateCarParkingCommand implements Command {
  private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

  private final VehiclesFactory factory;
  private final VehiclesRepository repository;
  private String name;
  private int capacity;
  private double hourlyRate;

  public CreateCarParkingCommand(VehiclesFactory factory, VehiclesRepository repository) {
    this.factory = factory;
    this.repository = repository;
  }

  @Override
  public String execute(List<String> parameters) {
    validateInput(parameters);
    parseParameters(parameters);
    Parking carParking = factory.createCarParking(name, capacity, hourlyRate);
    addParking(carParking);
    return String.format(PARKING_CREATED_MESSAGE, carParking.getParkingType(), carParking.getName());
  }

  private void validateInput(List<String> parameters) {
    if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
      throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, EXPECTED_NUMBER_OF_ARGUMENTS, parameters.size()));
    }
  }

  private void parseParameters(List<String> parameters) {
    try {
      name = parameters.get(0);
      capacity = Integer.parseInt(parameters.get(1));
      hourlyRate = Double.parseDouble(parameters.get(2));
    } catch (Exception e) {
      throw new IllegalArgumentException(String.format(FAILED_TO_PARSE_COMMAND_MESSAGE, getClass().getSimpleName()));
    }
  }

  private void addParking(Parking parking) {
    if (repository.getParkings().contains(parking)) {
      throw new IllegalArgumentException(String.format(PARKING_EXISTS_MESSAGE, parking.getName()));
    } else {
      repository.addParking(parking);
    }
  }
}
