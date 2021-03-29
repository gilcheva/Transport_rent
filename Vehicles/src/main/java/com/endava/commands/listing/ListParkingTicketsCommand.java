package com.endava.commands.listing;

import static com.endava.commands.Constants.JOIN_DELIMITER;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.parkings.contracts.ParkingTicket;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ListParkingTicketsCommand implements Command {

  private final Set<ParkingTicket> parkingTickets;

  public ListParkingTicketsCommand(VehiclesRepository repository) {
    parkingTickets = repository.getParkingTickets();
  }

  @Override
  public String execute(List<String> parameters) {
    if (parkingTickets.size() == 0) {
      return "There are no registered parking tickets.";
    }

    List<String> parkingTicketsToString = parkingTickets.stream()
        .map(Object::toString)
        .collect(Collectors.toList());

    return JOIN_DELIMITER +
        System.lineSeparator() +
        String.join(JOIN_DELIMITER + System.lineSeparator(), parkingTicketsToString).trim();
  }

}
