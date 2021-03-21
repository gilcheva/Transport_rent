package com.endava.commands.listing;

import static com.endava.commands.Constants.JOIN_DELIMITER;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class ListVehiclesCommand implements Command {
  private List<Vehicle> vehicles = new ArrayList<>();

  public ListVehiclesCommand(VehiclesRepository repository) {
    vehicles = repository.getVehicles();
  }

  @Override
  public String execute(List<String> parameters) {
    if (vehicles.size() == 0) {
      return "There are no registered vehicles.";
    }

    List<String> listVehicles = vehiclesToString();

    return JOIN_DELIMITER+
        System.lineSeparator()+
        String.join(JOIN_DELIMITER + System.lineSeparator(), listVehicles).trim();

  }

  private List<String> vehiclesToString() {
    List<String> result = new ArrayList<>();
    for (Vehicle vehicle : vehicles) {
      result.add(vehicle.toString());
    }
    return result;
  }
}
