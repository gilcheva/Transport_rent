package com.endava.commands.contracts;

import java.sql.SQLException;
import java.util.List;

public interface Command {
  String execute(List<String> parameters) throws SQLException;
}
