package com.endava.core.providers;

import com.endava.core.Writer;

public class ConsoleWriterImpl implements Writer {

  public void write(String message) {
    System.out.print(message);
  }

  public void writeLine(String message) {
    System.out.println(message);
  }
}
