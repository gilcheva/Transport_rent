package com.endava.commands.listing;

import static com.endava.commands.Constants.JOIN_DELIMITER;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.parkings.contracts.Parking;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ListParkingsCommand implements Command {

  private final Set<Parking> parkings;

  public ListParkingsCommand(VehiclesRepository repository) {
    parkings = repository.getParkings();
  }

  @Override
  public String execute(List<String> parameters) {
    if (parkings.size() == 0) {
      return "There are no registered parkings.";
    }

    List<String> parkingsToString = parkings.stream()
        .map(Object::toString)
        .collect(Collectors.toList());

    return JOIN_DELIMITER +
        System.lineSeparator() +
        String.join(JOIN_DELIMITER + System.lineSeparator(), parkingsToString).trim();
  }

}
