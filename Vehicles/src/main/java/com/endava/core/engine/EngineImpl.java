package com.endava.core.engine;

import com.endava.commands.Command;
import com.endava.core.*;
import com.endava.core.factories.CommandFactoryImpl;
import com.endava.core.factories.VehiclesFactoryImpl;
import com.endava.core.providers.CommandParserImpl;
import com.endava.core.providers.ConsoleWriterImpl;
import com.endava.core.providers.FileReaderImpl;
import com.endava.core.repositories.VehiclesRepositorySQLImpl;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class EngineImpl implements Engine {

  private final FileReader fileReader;
  private final Writer writer;
  private final VehiclesFactory vehiclesFactory;
  private final CommandFactory commandFactory;
  private final CommandParser commandParser;
  private final VehiclesRepository vehiclesRepository;
  private final File file;

  public EngineImpl()  {
    this.file = new File("src/main/java/resources/commandFile.txt");
    this.fileReader = new FileReaderImpl(file);
    this.writer = new ConsoleWriterImpl();
    this.vehiclesFactory = new VehiclesFactoryImpl();
    this.commandFactory = new CommandFactoryImpl();
    this.commandParser = new CommandParserImpl();
    this.vehiclesRepository = new VehiclesRepositorySQLImpl();
//    this.vehiclesRepository = new VehiclesRepositoryImpl();
  }

  public void start() {
    List<String> data = fileReader.readFile(file.getName());
    for (String commandAsString : data) {
      try {
        processCommand(commandAsString);
      } catch (Exception ex) {
        writer.writeLine(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage()
            : ex.toString());
      }
    }
  }

  private void processCommand(String commandAsString) throws SQLException {
    if (commandAsString == null || commandAsString.trim().equals("")) {
      throw new IllegalArgumentException("Command cannot be null or empty.");
    }

    String commandName = commandParser.parseCommand(commandAsString);
    Command command = commandFactory
        .createCommand(commandName, vehiclesFactory, vehiclesRepository);
    List<String> parameters = commandParser.parseParameters(commandAsString);
    String executionResult = command.execute(parameters);
    writer.writeLine(executionResult);
  }
}
