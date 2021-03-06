package com.fire;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jonathan.alviar on 9/30/14.
 */
public class FileHandler {

  public byte[] read(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    byte[] data = Files.readAllBytes(path);
    return data;
  }

  public String getFolderStructure(String currentDir) throws IOException {
    File f = null;
    File[] paths;
    String files = new String();
    f = new File(currentDir);
    paths = f.listFiles();

    String[] temp = new String[paths.length];
    for(int i = 0; i < paths.length; i++) {
      temp[i] = paths[i].getName();
    }

    Arrays.sort(temp);

    for (String path : temp) {
      files += path + "\n";
    }

    return files;
  }

  public boolean exists(String fileName) {
    Path path = Paths.get(fileName);
    return Files.exists(path) && !Files.isDirectory(path);
  }

  public String getFolderStructureUrl(String currentDir) throws IOException {
    File f = null;
    File[] paths;
    String files = new String();
    f = new File(currentDir);
    paths = f.listFiles();

    String[] temp = new String[paths.length];
    for(int i = 0; i < paths.length; i++) {
      temp[i] = paths[i].getName();
    }

    Arrays.sort(temp);

    for (String path : temp) {
      files += String.format( "<a href=\"%s\">%s</a>", path, path) + "\n";
    }

    return files;
  }
}
