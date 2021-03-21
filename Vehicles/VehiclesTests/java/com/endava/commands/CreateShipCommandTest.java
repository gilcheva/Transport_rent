package com.endava.commands;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.endava.commands.contracts.Command;
import com.endava.commands.creation.CreateShipCommand;
import com.endava.core.VehiclesRepositoryImpl;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.core.factories.VehiclesFactoryImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateShipCommandTest {

  private VehiclesRepository repository;
  private VehiclesFactory factory;
  private Command testCommand;

  @BeforeEach
  public void before() {
    this.factory = new VehiclesFactoryImpl();
    this.repository = new VehiclesRepositoryImpl();
    this.testCommand = new CreateShipCommand(factory, repository);
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
  public void execute_should_throwException_when_passedInvalidLoadCapacity() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("ff");
    arguments.add("2");

    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_throwException_when_passedInvalidPricePerKgPerKm() {
    // Arrange
    List<String> arguments = new ArrayList<>();
    arguments.add("5");
    arguments.add("f");

    // Act and assert
    assertThrows(IllegalArgumentException.class, () -> {
      testCommand.execute(arguments);
    });
  }

  @Test
  public void execute_should_addNewShip_when_passedValidInput() {
    // Arrange, Act
    testCommand.execute(asList("20", "0.20"));

    // Assert
    assertEquals(1, repository.getVehicles().size());
    assertEquals("ShipImpl", repository.getVehicles().get(0).getClass().getSimpleName());
  }

}