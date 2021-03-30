package com.endava.core;

import com.endava.commands.Command;

public interface CommandFactory {

  Command createCommand(
      String commandTypeAsString,
      VehiclesFactory factory,
      VehiclesRepository vehicleRepository);
}
