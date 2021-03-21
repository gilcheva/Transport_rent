package com.endava.core.providers;

import com.endava.core.contracts.FileReader;
import com.endava.core.contracts.Reader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderImpl implements Reader, FileReader {

  private Scanner scanner;

  public FileReaderImpl(File file) {
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist.");
    }
  }

  public List<String> readFile(String fileName) {
    List<String> data = new ArrayList<>();
    while (scanner.hasNextLine()) {
      data.add(readLine());
    }
    return data;
  }


  public String readLine() {
    return scanner.nextLine();
  }
}


//  @Override
//  public String readFile(String fileName) {
//    StringBuilder content = new StringBuilder();
//    String line;
//    try (FileInputStream fis = new FileInputStream(fileName);
//        BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
//      while ((line = br.readLine()) != null) {
//          content.append(line);
//          content.append(System.lineSeparator());
//      }
//    } catch (FileNotFoundException e) {
//      System.out.println("The file " + fileName + "does not exist!");
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//      return content.toString();
//  }