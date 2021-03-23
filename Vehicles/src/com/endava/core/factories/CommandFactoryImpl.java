package com.endava.core.factories;

import com.endava.commands.contracts.Command;
import com.endava.commands.creation.CreateAirplaneCommand;
import com.endava.commands.creation.CreateAirplaneParkingCommand;
import com.endava.commands.creation.CreateBusParkingCommand;
import com.endava.commands.creation.CreateCarCommand;
import com.endava.commands.creation.CreateCarParkingCommand;
import com.endava.commands.creation.CreateCourseCommand;
import com.endava.commands.creation.CreateParkingTicketCommand;
import com.endava.commands.creation.CreateRentCommand;
import com.endava.commands.creation.CreateShipCommand;
import com.endava.commands.creation.CreateTrainCommand;
import com.endava.commands.creation.CreateTrainParkingCommand;
import com.endava.commands.changes.ExitParkingCommand;
import com.endava.commands.enums.CommandType;
import com.endava.commands.listing.ListCoursesCommand;
import com.endava.commands.listing.ListParkingTicketsCommand;
import com.endava.commands.listing.ListParkingsCommand;
import com.endava.commands.listing.ListRentsCommand;
import com.endava.commands.listing.ListVehiclesCommand;
import com.endava.core.contracts.CommandFactory;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;

public class CommandFactoryImpl implements CommandFactory {

  private static final String INVALID_COMMAND = "Invalid command name: %s!";

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

    switch (commandType) {
      case CREATEAIRPLANE:
        return new CreateAirplaneCommand(vehiclesFactory, vehiclesRepository);

      case CREATECAR:
        return new CreateCarCommand(vehiclesFactory, vehiclesRepository);

      case CREATESHIP:
        return new CreateShipCommand(vehiclesFactory, vehiclesRepository);

      case CREATETRAIN:
        return new CreateTrainCommand(vehiclesFactory, vehiclesRepository);

      case CREATECOURSE:
        return new CreateCourseCommand(vehiclesFactory, vehiclesRepository);

      case CREATERENT:
        return new CreateRentCommand(vehiclesFactory, vehiclesRepository);

      case CREATEAIRPLANEPARKING:
        return new CreateAirplaneParkingCommand(vehiclesFactory, vehiclesRepository);

      case CREATEBUSPARKING:
        return new CreateBusParkingCommand(vehiclesFactory, vehiclesRepository);

      case CREATECARPARKING:
        return new CreateCarParkingCommand(vehiclesFactory, vehiclesRepository);

      case CREATETRAINPARKING:
        return new CreateTrainParkingCommand(vehiclesFactory, vehiclesRepository);

      case CREATEPARKINGTICKET:
        return new CreateParkingTicketCommand(vehiclesFactory, vehiclesRepository);

      case EXITPARKING:
        return new ExitParkingCommand(vehiclesFactory, vehiclesRepository);

      case LISTVEHICLES:
        return new ListVehiclesCommand(vehiclesRepository);

      case LISTCOURSES:
        return new ListCoursesCommand(vehiclesRepository);

      case LISTRENTS:
        return new ListRentsCommand(vehiclesRepository);

      case LISTPARKINGS:
        return new ListParkingsCommand(vehiclesRepository);

      case LISTPARKINGTICKETS:
        return new ListParkingTicketsCommand(vehiclesRepository);

    }
    throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
  }
}