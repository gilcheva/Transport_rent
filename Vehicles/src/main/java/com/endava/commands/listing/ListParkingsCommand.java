package com.endava.commands.listing;

import static com.endava.commands.constants.Constants.JOIN_DELIMITER;

import com.endava.commands.Command;
import com.endava.core.VehiclesRepository;
import com.endava.models.parkings.Parking;

import java.util.List;
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
