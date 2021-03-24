package com.endava.commands.creation;

import static com.endava.commands.Constants.FAILED_TO_PARSE_COMMAND_MESSAGE;
import static com.endava.commands.Constants.INVALID_NUMBER_OF_ARGUMENTS;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import java.util.List;

public class CreateRentCommand implements Command {
  private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

  private final VehiclesFactory factory;
  private final VehiclesRepository repository;
  private int courseID;
  private double administrativeCosts;

  public CreateRentCommand(VehiclesFactory factory, VehiclesRepository repository) {
    this.factory = factory;
    this.repository = repository;
  }

  @Override
  public String execute(List<String> parameters) {
    validateInput(parameters);
    parseParameters(parameters);
    Course course = repository.getCourses().get(courseID);
    Rent rent = factory.createRent(course, administrativeCosts);
    repository.addRent(rent);
    return String.format("Rent with ID %d was created.", repository.getRents().size() - 1);
  }

  private void validateInput(List<String> parameters) {
    if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
      throw new IllegalArgumentException(String.format(
          INVALID_NUMBER_OF_ARGUMENTS,
          EXPECTED_NUMBER_OF_ARGUMENTS,
          parameters.size()));
    }
  }

  private void parseParameters(List<String> parameters) {
    try {
      courseID = Integer.parseInt(parameters.get(0));
      administrativeCosts = Double.parseDouble(parameters.get(1));
    } catch (Exception e) {
      throw new IllegalArgumentException(String.format(FAILED_TO_PARSE_COMMAND_MESSAGE, getClass().getSimpleName()));
    }
  }
}
