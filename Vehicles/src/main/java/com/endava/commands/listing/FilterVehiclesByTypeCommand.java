package com.endava.commands.listing;

import static com.endava.commands.Constants.FAILED_TO_PARSE_COMMAND_MESSAGE;
import static com.endava.commands.Constants.INVALID_NUMBER_OF_ARGUMENTS;
import static com.endava.commands.Constants.JOIN_DELIMITER;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.enums.VehicleType;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.List;

public class FilterVehiclesByTypeCommand implements Command {
  private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

  private final VehiclesRepository repository;
  private VehicleType type;

  public FilterVehiclesByTypeCommand(VehiclesRepository repository) {
    this.repository = repository;
  }

  @Override
  public String execute(List<String> parameters) {
    validateInput(parameters);
    parseParameters(parameters);
    return printFilteredExecute();
  }

  private void validateInput(List<String> parameters) {
    if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
      throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, EXPECTED_NUMBER_OF_ARGUMENTS, parameters.size()));
    }
  }

  private void parseParameters(List<String> parameters) {
    try {
      type = VehicleType.valueOf(parameters.get(0).toUpperCase());
    } catch (Exception e) {
      throw new IllegalArgumentException(String.format(FAILED_TO_PARSE_COMMAND_MESSAGE, getClass().getSimpleName())); }

  }

  private String printFilteredExecute() {
//    EnumSet<VehicleType> filtered = ;
//    List <Vehicle> filtered = ParkingTicketImpl.groupParkingsByType(repository.getParkings());
    List<Vehicle> filtered = repository.sortedByNumber((repository.filterByType(type)));
    StringBuilder sb = new StringBuilder();
    sb.append(System.lineSeparator());
    sb.append(JOIN_DELIMITER).append(JOIN_DELIMITER).append(System.lineSeparator());
    sb.append(String.format("-----------  Vehicles Filtered By Type %s  ----------", type)).append(System.lineSeparator());
    for (Vehicle vehicle : filtered) {
      sb.append(vehicle.toString()).append(System.lineSeparator());
    }
    sb.append(String.format("------------- End of filter by type %s ---------------", type)).append(System.lineSeparator());
    sb.append(JOIN_DELIMITER).append(JOIN_DELIMITER).append(System.lineSeparator());
    return sb.toString();
  }

}
