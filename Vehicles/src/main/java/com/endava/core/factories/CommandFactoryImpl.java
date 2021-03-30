package com.endava.core.factories;

import static com.endava.commands.constants.Constants.INVALID_COMMAND;

import com.endava.commands.Command;
import com.endava.commands.enums.CommandType;
import com.endava.core.CommandFactory;
import com.endava.core.VehiclesFactory;
import com.endava.core.VehiclesRepository;

public class CommandFactoryImpl implements CommandFactory {

  public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
      VehiclesRepository vehiclesRepository) {

    CommandType commandType = null;
    for (CommandType ct : CommandType.values()) {
      if (String.valueOf(ct).equals(commandName.toUpperCase())) {
        commandType = ct;
      }
    }

    if (commandType == null) {
      throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
    }

      return commandType.createCommand(commandName, vehiclesFactory, vehiclesRepository);

  }
}