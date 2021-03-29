package com.endava.commands.listing;

import static com.endava.commands.Constants.INVALID_NUMBER_OF_ARGUMENTS;
import static com.endava.commands.Constants.JOIN_DELIMITER;
import static com.endava.commands.Constants.NO_VEHICLES_MESSAGE;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.List;

public class ListVehiclesCommand implements Command {
  private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

  private final VehiclesRepository repository;

  public ListVehiclesCommand(VehiclesRepository repository) {
    this.repository = repository;
  }

  @Override
  public String execute(List<String> parameters) {
    validateInput(parameters);
    if (repository.getVehicles().isEmpty()) {
      return NO_VEHICLES_MESSAGE;
    }
    return printSortedExecute();
  }

  private void validateInput(List<String> parameters) {
    if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
      throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, EXPECTED_NUMBER_OF_ARGUMENTS, parameters.size()));
    }
  }

  private String printSortedExecute() {
    List<Vehicle> sorted = repository.sortedByNumber(repository.getVehicles());
    StringBuilder sb = new StringBuilder();
    sb.append(System.lineSeparator());
    sb.append(JOIN_DELIMITER).append(System.lineSeparator());
    sb.append("---------  Vehicles  --------").append(System.lineSeparator());
    sb.append(JOIN_DELIMITER).append(System.lineSeparator());
    for (Vehicle vehicle : sorted) {
      sb.append(vehicle.toString()).append(System.lineSeparator());
    }
    sb.append(JOIN_DELIMITER).append(System.lineSeparator());
    sb.append("-----  End of vehicles  -----").append(System.lineSeparator());
    sb.append(JOIN_DELIMITER).append(System.lineSeparator());
    return sb.toString();
  }

}
