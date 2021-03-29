package com.endava.commands.creation;

import com.endava.commands.contracts.Command;
import com.endava.core.VehiclesRepositoryImpl;
import com.endava.core.VehiclesRepositorySQLImpl;
import com.endava.core.contracts.VehiclesFactory;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.core.factories.VehiclesFactoryImpl;
import com.endava.models.vehicles.AirplaneImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class CreateCourseCommandTest {
    private VehiclesRepository repository;
    private VehiclesFactory factory;
    private Command testCommand;

    @BeforeEach
    public void before() {
        this.factory = new VehiclesFactoryImpl();
        this.repository = new VehiclesRepositorySQLImpl();
        this.testCommand = new CreateCourseCommand(factory, repository);
    }

    @Test
    public void execute_should_throwException_when_passedFewerArgumentsThanExpected() {
        // Arrange, act, assert
        assertThrows(IllegalArgumentException.class, () -> {
            testCommand.execute(asList(new String[3]));
        });
    }

    @Test
    public void execute_should_throwException_when_passedMoreArgumentsThanExpected() {
        // Arrange,act, assert
        assertThrows(IllegalArgumentException.class, () -> {
            testCommand.execute(asList(new String[5]));
        });
    }

    @Test
    public void execute_should_throwException_when_passedShorterStartLocation() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("");
        arguments.add("destination");
        arguments.add("30");
        arguments.add("0");
        repository.addVehicle(null);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            testCommand.execute(arguments);
        });
    }

    @Test
    public void execute_should_throwException_when_passedLongerStartLocation() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("12345678901234567890123456");
        arguments.add("destination");
        arguments.add("30");
        arguments.add("0");
        repository.addVehicle(null);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            testCommand.execute(arguments);
        });
    }

    @Test
    public void execute_should_throwException_when_passedShorterDestination() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("start");
        arguments.add("");
        arguments.add("30");
        arguments.add("0");
        repository.addVehicle(null);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            testCommand.execute(arguments);
        });
    }

    @Test
    public void execute_should_throwException_when_passedLongerDestination() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("start");
        arguments.add("12345678901234567890123456");
        arguments.add("30");
        arguments.add("0");
        repository.addVehicle(null);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            testCommand.execute(arguments);
        });
    }

    @Test
    public void execute_should_throwException_when_passedInvalidDistance() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("start");
        arguments.add("destination");
        arguments.add("f");
        arguments.add("0");
        repository.addVehicle(null);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            testCommand.execute(arguments);
        });
    }

    @Test
    public void execute_should_throwException_when_passedInvalidIndex() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("start");
        arguments.add("destination");
        arguments.add("20");
        arguments.add("f");
        repository.addVehicle(null);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            testCommand.execute(arguments);
        });
    }

    @Test
    public void execute_should_throwException_when_passedNonexistingIndex() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("start");
        arguments.add("destination");
        arguments.add("20");
        arguments.add("1");
        repository.addVehicle(null);

        // Act and assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testCommand.execute(arguments);
        });
    }

    @Test
    public void execute_should_addNewCourse_when_passedValidInput() throws SQLException {
        // Arrange
        repository.addPlane(new AirplaneImpl("С7320В", 30, 20, true));

        // Act
        testCommand.execute(asList("start", "destination", "30", "0"));

        // Assert
        assertEquals(1, repository.getCourses().size());
        assertEquals("CourseImpl", repository.getCourses().get(0).getClass().getSimpleName());
    }

}