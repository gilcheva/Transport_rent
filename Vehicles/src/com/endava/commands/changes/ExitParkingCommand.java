package com.endava.commands.changes;

import static com.endava.commands.Constants.INVALID_NUMBER_OF_ARGUMENTS;
import static com.endava.commands.Constants.PARKING_TICKET_NOT_EXIST_MESSAGE;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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

    ParkingTicket ticket = repository.getParkingTickets().stream()
        .filter(tick -> vehicleNumber.equals(tick.getVehicle().getRegistrationNumber()))
        .findAny()
        .orElseThrow(() ->
            new NoSuchElementException(
                String.format(PARKING_TICKET_NOT_EXIST_MESSAGE, vehicleNumber)));

    Parking parking = ticket.getParking();
    parking.removeVehicleFromParking();
    OffsetDateTime exitTime = ticket.updateExitTime();
    double price = ticket.calculatePrice();

    String sumToPayString = String.format(vehicleNumber+ System.lineSeparator()+
        "Sum to pay: %.2f", price) + System.lineSeparator() +
        "Entrance time: " + ticket.getEntranceTime().getHour()+":"+ticket.getEntranceTime().getMinute() + System.lineSeparator() +
        "Exit time: " + exitTime.getHour() + ":" + exitTime.getMinute() + System.lineSeparator();
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

}
