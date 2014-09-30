package com.fire;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by jonathan.alviar on 9/30/14.
 */
public class FileHandler {

  public List<String> read(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    return Files.readAllLines(path);
  }
}
