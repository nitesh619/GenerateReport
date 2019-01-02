package com.capita.core.parsers;

import com.capita.core.api.Income;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CSVParser implements InputParser {

  public List<Income> parseIncome(final File fileName) {
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName.getPath()))) {
      //read and ignore csv column header
      Spliterator<String> spliterator = reader.lines().spliterator();
      Spliterator<Income> incomeSpliterator = new IncomeSplitIterator(spliterator);
      Stream<Income> stream = StreamSupport.stream(incomeSpliterator, false);

      return stream.collect(Collectors.toList());
    } catch (IOException e) {
      System.out.println("Unable to open file: " + e.getMessage());
    }
    return new ArrayList<>();
  }
}
