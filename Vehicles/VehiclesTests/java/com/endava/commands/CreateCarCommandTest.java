package com.endava.commands;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.endava.commands.contracts.Command;
import com.endava.commands.creation.CreateCarCommand;
import com.endava.core.VehiclesRepositoryImpl;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.core.factories.VehiclesFactoryImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateCarCommandTest {

  private VehiclesRepository repository;
  private VehiclesFactory factory;
  private Command testCommand;

  @BeforeEach
  public void before() {
    this.factory = new VehiclesFactoryImpl();
    this.repository = new VehiclesRepositoryImpl();
    this.testCommand = new CreateCarCommand(factory, repository);
  }

  @Test
  public void execute_should_throwException_when_passedFewerArgumentsThanExpected() {
    //Arrange, act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(asList(new String[2]));
    });
  }

  @Test
  public void execute_should_throwException_when_passedMoreArgumentsThanExpected() {
    //Arrange, act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(asList(new String[4]));
    });
  }

  @Test
  public void execute_should_throwException_when_passedInvalidLoadCapacity() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("ff");
    arguments.add("2");
    arguments.add("0");

    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_throwException_when_passedInvalidPricePerKm() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("5");
    arguments.add("f");
    arguments.add("0");

    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_throwException_when_passedInvalidType() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("5");
    arguments.add("2");
    arguments.add("p");

    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_addNewCar_when_passedValidInput() {
    // Arrange, Act
    testCommand.execute(asList("5", "2", "0"));

    // Assert
    assertEquals(1, repository.getVehicles().size());
    assertEquals("CarImpl", repository.getVehicles().get(0).getClass().getSimpleName());
  }

}