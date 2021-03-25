package com.endava.commands.enums;


import static com.endava.commands.Constants.INVALID_COMMAND;

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
import com.endava.commands.creation.ExitParkingCommand;
import com.endava.commands.listing.FilterVehiclesByTypeCommand;
import com.endava.commands.listing.ListCoursesCommand;
import com.endava.commands.listing.ListParkingTicketsCommand;
import com.endava.commands.listing.ListParkingsCommand;
import com.endava.commands.listing.ListRentsCommand;
import com.endava.commands.listing.ListVehiclesCommand;
import com.endava.commands.listing.SortVehiclesByLoadCapacityCommand;
import com.endava.commands.listing.SortVehiclesByPriceCommand;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;

public enum CommandType {
  CREATEAIRPLANE {
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateAirplaneCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATECAR{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateCarCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATESHIP{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateShipCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATETRAIN {
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateTrainCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATECOURSE {
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateCourseCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATERENT{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateRentCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATEAIRPLANEPARKING{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateAirplaneParkingCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATEBUSPARKING{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateBusParkingCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATECARPARKING{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateCarParkingCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATETRAINPARKING{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateTrainParkingCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  CREATEPARKINGTICKET{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new CreateParkingTicketCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  EXITPARKING{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new ExitParkingCommand(vehiclesFactory, vehiclesRepository);
    }
  },
  LISTVEHICLES{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new ListVehiclesCommand(vehiclesRepository);
    }
  },
  LISTCOURSES{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new ListCoursesCommand(vehiclesRepository);
    }
  },
  LISTRENTS{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new ListRentsCommand(vehiclesRepository);
    }
  },
  LISTPARKINGS{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new ListParkingsCommand(vehiclesRepository);
    }
  },
  LISTPARKINGTICKETS{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new ListParkingTicketsCommand(vehiclesRepository);
    }
  },
  SORTVEHICLESBYLOADCAPACITY{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new SortVehiclesByLoadCapacityCommand(vehiclesRepository);
    }
  },
  SORTVEHICLESBYPRICE{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new SortVehiclesByPriceCommand(vehiclesRepository);
    }
  },
  FILTERVEHICLESBYTYPE{
    public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
        VehiclesRepository vehiclesRepository) {
      return new FilterVehiclesByTypeCommand(vehiclesRepository);
    }
  };

  public Command createCommand(String commandName, VehiclesFactory vehiclesFactory,
      VehiclesRepository vehiclesRepository){
    throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
  }
}
