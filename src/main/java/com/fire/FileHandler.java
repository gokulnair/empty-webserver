package com.fire;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by jonathan.alviar on 9/30/14.
 */
public class FileHandler {

  public String read(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    String content = "";
    List<String> contentData = Files.readAllLines(path);
    for(String line: contentData ){
      content+= line + "\n";
    }
    return content;
  }

  public String getFolderStructure(String currentDir) throws IOException {
    File f = null;
    File[] paths;
    String files = new String();
    f = new File(currentDir);
    paths = f.listFiles();

    for (File path : paths) {
      files += path.getName() + "\n";
    }

    return files;
  }
}
