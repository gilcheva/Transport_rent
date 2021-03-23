package com.endava.commands;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.endava.commands.contracts.Command;
import com.endava.commands.creation.CreateRentCommand;
import com.endava.core.VehiclesRepositoryImpl;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.core.factories.VehiclesFactoryImpl;
import com.endava.models.CourseImpl;
import com.endava.models.vehicles.AirplaneImpl;
import com.endava.models.vehicles.contracts.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateRentCommandTest {

  private VehiclesRepository repository;
  private VehiclesFactory factory;
  private Command testCommand;

  @BeforeEach
  public void before() {
    this.factory = new VehiclesFactoryImpl();
    this.repository = new VehiclesRepositoryImpl();
    this.testCommand = new CreateRentCommand(factory, repository);
  }

  @Test
  public void execute_should_throwException_when_passedFewerArgumentsThanExpected() {
    // Arrange, act, assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(asList(new String[1]));
    });
  }

  @Test
  public void execute_should_throwException_when_passedMoreArgumentsThanExpected() {
    // Arrange, act, assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(asList(new String[3]));
    });
  }

  @Test
  public void execute_should_throwException_when_passedInvalidIndex() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("f");
    arguments.add("20");
    repository.addCourse(new CourseImpl( "start","destination",30, new AirplaneImpl("С7320В", 30, 20, true)));

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_throwException_when_passedNonexistingIndex() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("1");
    arguments.add("20");
    repository.addCourse(new CourseImpl( "start","destination",30, new AirplaneImpl("С7320В", 30, 20, true)));

    // Act
    assertThrows(IndexOutOfBoundsException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_throwException_when_passedInvalidAdministrativeCosts() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("0");
    arguments.add("2gg");

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_throwException_when_passedNullCourse() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("0");
    arguments.add("20");
    repository.addCourse(null);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_addNewRent_when_passedValidInput() {
    // Arrange
    Vehicle vehicle = new AirplaneImpl("С7320В", 30, 20, true);
    repository.addVehicle(vehicle);
    repository.addCourse(new CourseImpl( "start","destination",30, vehicle));

    // Act
    testCommand.execute(asList("0", "20"));

    // Assert
    assertEquals(1, repository.getRents().size());
    assertEquals("RentImpl", repository.getRents().get(0).getClass().getSimpleName());
  }

}
