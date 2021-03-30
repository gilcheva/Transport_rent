package com.endava.commands.listing;

import static com.endava.commands.constants.Constants.JOIN_DELIMITER;

import com.endava.commands.Command;
import com.endava.core.VehiclesRepository;
import com.endava.models.courses.Rent;
import java.util.ArrayList;
import java.util.List;

public class ListRentsCommand implements Command {
  private final List<Rent> rents;

  public ListRentsCommand(VehiclesRepository repository) {
    rents = repository.getRents();
  }

  @Override
  public String execute(List<String> parameters) {
    if (rents.size() == 0) {
      return "There are no registered rents.";
    }

    List<String> listRents = rentsToString();

    return JOIN_DELIMITER+
        System.lineSeparator()+
        String.join(JOIN_DELIMITER + System.lineSeparator(), listRents).trim();

  }

  private List<String> rentsToString() {
    List<String> result = new ArrayList<>();
    for (Rent rent : rents) {
      result.add("RentID: " + rents.indexOf(rent)+rent.toString());
    }
    return result;
  }
}
