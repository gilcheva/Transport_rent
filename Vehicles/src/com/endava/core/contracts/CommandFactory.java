package com.endava.core.contracts;

import com.endava.commands.contracts.Command;
import com.endava.models.vehicles.contracts.Vehicle;

public interface CommandFactory {

  Command createCommand(
      String commandTypeAsString,
      VehiclesFactory factory,
      VehiclesRepository vehicleRepository);
}
