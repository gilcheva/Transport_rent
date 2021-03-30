package com.endava.commands.creation;

import static com.endava.commands.constants.Constants.INVALID_NUMBER_OF_ARGUMENTS;
import static com.endava.commands.constants.Constants.PARKING_TICKET_PRICE;
import static com.endava.models.helpers.Helper.dateFormat;

import com.endava.commands.Command;
import com.endava.core.VehiclesFactory;
import com.endava.core.VehiclesRepository;
import com.endava.models.parkings.Parking;
import com.endava.models.parkings.ParkingTicket;
import java.time.OffsetDateTime;
import java.util.List;

public class ExitParkingCommand implements Command {

  private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

  private final VehiclesFactory factory;
  private final VehiclesRepository repository;
  private String vehicleNumber;

  public ExitParkingCommand(VehiclesFactory factory, VehiclesRepository repository) {
    this.factory = factory;
    this.repository = repository;
  }

  @Override
  public String execute(List<String> parameters) {
    validateInput(parameters);
    parseParameters(parameters);
    ParkingTicket ticket = repository.findTicketByVehicleNumber(vehicleNumber);
    Parking parking = ticket.getParking();
    parking.removeVehicleFromParking();
    OffsetDateTime exitTime = ticket.updateExitTime();
    String sumToPayString = printSumToPay(ticket);
    repository.removeParkingTicket(ticket);
    return sumToPayString;
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
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Failed to parse ExitParkingTicket command parameters.");
    }
  }

  private String printSumToPay(ParkingTicket ticket){
   return ticket + String.format(
        "Exit time:     " + dateFormat(ticket.getExitTime()) + System.lineSeparator()+
            PARKING_TICKET_PRICE, ticket.calculatePrice()) + System.lineSeparator();
  }
}
