package com.endava.commands.creation;

import static com.endava.commands.constants.Constants.FAILED_TO_PARSE_COMMAND_MESSAGE;
import static com.endava.commands.constants.Constants.INVALID_NUMBER_OF_ARGUMENTS;

import com.endava.commands.Command;
import com.endava.core.VehiclesFactory;
import com.endava.core.VehiclesRepository;
import com.endava.models.courses.Course;
import com.endava.models.vehicles.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class CreateCourseCommand implements Command {
  private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

  private final VehiclesFactory factory;
  private final VehiclesRepository repository;
  private String startLocation;
  private String destination;
  private int distance;
  private int vehicleID;

  public CreateCourseCommand(VehiclesFactory factory, VehiclesRepository repository) {
    this.factory = factory;
    this.repository = repository;
  }

  @Override
  public String execute(List<String> parameters) throws SQLException {
    validateInput(parameters);
    parseParameters(parameters);
    Vehicle vehicle = repository.findVehicleById(vehicleID);
    Course course = factory.createCourse(startLocation, destination, distance, vehicle);
    repository.addCourse(course);
    return String.format("Course with ID %d was created.", repository.getCourses().size() - 1);
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
      startLocation = parameters.get(0);
      destination = parameters.get(1);
      distance = Integer.parseInt(parameters.get(2));
      vehicleID = Integer.parseInt(parameters.get(3));
    } catch (Exception e) {
      throw new IllegalArgumentException(String.format(FAILED_TO_PARSE_COMMAND_MESSAGE, getClass().getSimpleName())); }
  }
}
